package com.dev.portal.commons;

import java.rmi.RemoteException;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.Instance;
import com.dev.portal.dao.client.AwsCatalogDaoClient;
import com.dev.portal.dao.client.AwsEndpointDaoClient;
import com.dev.portal.dao.client.MachineDaoClient;
import com.dev.portal.dao.client.RequestDaoClient;
import com.dev.portal.dao.client.VsphereCatalogDaoClient;
import com.dev.portal.dao.client.VsphereEndpointDaoClient;
import com.dev.portal.helpers.aws.AwsClientHelper;
import com.dev.portal.helpers.aws.AwsResourceHelper;
import com.dev.portal.helpers.vcenter.VsphereResourceHelper;
import com.dev.portal.models.AwsCatalogModel;
import com.dev.portal.models.AwsEndpointModel;
import com.dev.portal.models.MachineModel;
import com.dev.portal.models.RequestModel;
import com.dev.portal.models.VsphereCatalogModel;
import com.dev.portal.models.VsphereEndpointModel;
import com.dev.portal.models.contants.PlatformType;
import com.dev.portal.models.contants.RequestStatus;
import com.vmware.vim25.TaskInfo;
import com.vmware.vim25.TaskInfoState;
import com.vmware.vim25.mo.Task;
import com.vmware.vim25.mo.VirtualMachine;

public class RequestRunner extends Thread {

    private MongoTemplate mongoTemplate;
    private RequestModel requestModel;

    public RequestRunner(MongoTemplate mongoTemplate, RequestModel requestModel){
        this.mongoTemplate = mongoTemplate;
        this.requestModel = requestModel;
    }

    @Override
    public void run() {
        RequestDaoClient requestDaoClient = new RequestDaoClient(mongoTemplate);
        MachineDaoClient machineDaoClient = new MachineDaoClient(mongoTemplate);
        MachineModel machineModel = null;
        if(requestModel.getPlatform().equals(PlatformType.AMAZON_AWS.toString())){
            AwsCatalogModel awsCatalogModel = new AwsCatalogDaoClient(mongoTemplate).getAwsCatalogModelByName(requestModel.getCatalog());
            AwsEndpointModel awsEndpointModel = new AwsEndpointDaoClient(mongoTemplate).getAwsEndpointModelByName(requestModel.getEndpoint());
            AmazonEC2 ec2 = new AwsClientHelper(awsEndpointModel.getAccessKey(), awsEndpointModel.getSecretKey(), Regions.fromName(awsEndpointModel.getRegion().replaceAll("_", "-").toLowerCase())).getAmazonEC2Client();
            AwsResourceHelper awsResourceHelper = new AwsResourceHelper();
            Instance instance = null;
            try{
                instance = awsResourceHelper.requestEC2(ec2, requestModel.getName(), awsCatalogModel.getAmiId(), awsCatalogModel.getInstanceType(), awsCatalogModel.getSecurityGroup());
            } catch (Exception e){
                requestModel.setStatus(RequestStatus.FAILED.toString());
                requestModel.setError(e.getMessage());
                e.printStackTrace();
            }
            requestModel = requestDaoClient.addOrUpdateRequestModel(requestModel);
            machineModel = composeMachineModel(requestModel.getName(), instance.getPublicIpAddress(), instance.getState().getName(), requestModel.getCatalog(), requestModel.getEndpoint(), requestModel.getOwner(), requestModel.getOrganization(), requestModel.getPlatform(), instance.toString());
            machineModel = machineDaoClient.addOrUpdateMachineModel(machineModel);
            for(int i=1; i<=45;i++){
                requestModel = requestDaoClient.getRequestModelById(requestModel.getId());
                if(requestModel.getStatus().equals(RequestStatus.IN_PROGRESS.toString())){
                    CommonUtils.sleepSeconds(5);
                    instance = awsResourceHelper.getEC2InstanceDetails(ec2, instance.getInstanceId());
                    if("running".equals(instance.getState().getName())){
                        requestModel.setStatus(RequestStatus.SUCCESSFUL.toString());
                    }
                    if(i == 45){
                        requestModel.setStatus(RequestStatus.NOT_AVAILABLE.toString());
                    }
                    requestModel.setLastUpdatedTime(CommonUtils.getCurrentTime());
                    requestModel = requestDaoClient.addOrUpdateRequestModel(requestModel);
                    machineModel.setIp(instance.getPublicIpAddress());
                    machineModel.setState(instance.getState().getName());
                    machineModel.setDescription(instance.toString());
                    machineModel = machineDaoClient.addOrUpdateMachineModel(machineModel);
                } else {
                   break;
                }
            }
        } else if (requestModel.getPlatform().equals(PlatformType.VMWARE_VSPHERE.toString())){
            VsphereCatalogModel vsphereCatalogModel = new VsphereCatalogDaoClient(mongoTemplate).getVsphereCatalogModelByName(requestModel.getCatalog());
            VsphereEndpointModel vsphereEndpointModel = new VsphereEndpointDaoClient(mongoTemplate).getVsphereEndpointModelByName(requestModel.getEndpoint());
            VsphereResourceHelper vsphereResourceHelper = new VsphereResourceHelper(vsphereEndpointModel.getSdkUrl(), vsphereEndpointModel.getUser(), vsphereEndpointModel.getPassword());
            Task task = vsphereResourceHelper.cloneVirtualMachine(vsphereEndpointModel.getComputes(), vsphereCatalogModel.getTemplate(), requestModel.getName());
            requestModel = requestDaoClient.addOrUpdateRequestModel(requestModel);
            String vmIP = "N/A";
            machineModel = composeMachineModel(requestModel.getName(), vmIP, vmIP, requestModel.getCatalog(), requestModel.getEndpoint(), requestModel.getOwner(), requestModel.getOrganization(), requestModel.getPlatform(), vmIP);
            machineModel = machineDaoClient.addOrUpdateMachineModel(machineModel);
            for(int i=0; i<=45; i++){
                requestModel = requestDaoClient.getRequestModelById(requestModel.getId());
                if(requestModel.getStatus().equals(RequestStatus.IN_PROGRESS.toString())){
                    CommonUtils.sleepSeconds(5);
                    TaskInfoState taskInfoState = null;
                    TaskInfo taskInfo = null;
                    try {
                        taskInfoState = task.getTaskInfo().getState();
                        taskInfo = task.getTaskInfo();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    if(TaskInfoState.success == taskInfoState){
                        requestModel.setStatus(RequestStatus.SUCCESSFUL.toString());
                    } else if (TaskInfoState.error == taskInfoState){
                        requestModel.setStatus(RequestStatus.FAILED.toString());
                        requestModel.setError(taskInfo.getError().getLocalizedMessage());
                    } else if (i==45){
                        requestModel.setStatus(RequestStatus.NOT_AVAILABLE.toString());
                        requestModel.setError(taskInfo.getError().getLocalizedMessage());
                    }
                    requestModel.setLastUpdatedTime(CommonUtils.getCurrentTime());
                    requestModel = requestDaoClient.addOrUpdateRequestModel(requestModel);
                    VirtualMachine virtualMachine = vsphereResourceHelper.getVirtualMachine(requestModel.getName());
                    if(virtualMachine.getGuest() != null){
                        vmIP = virtualMachine.getGuest().getIpAddress();
                    }
                    machineModel.setIp(vmIP);
                    machineModel.setState(virtualMachine.getSummary().runtime.powerState.toString());
                    machineModel.setDescription(virtualMachine.toString());
                    machineModel = machineDaoClient.addOrUpdateMachineModel(machineModel);
                } else {
                    break;
                }
            }
        }
    }

    private MachineModel composeMachineModel(String vmName, String ip, String state, String catalog, String endpoint, String owner, String organization, String platform, String description) {
        MachineModel machineModel = new MachineModel();
        machineModel.setName(vmName);
        machineModel.setIp(ip);
        machineModel.setState(state);
        machineModel.setCatalog(catalog);
        machineModel.setEndpoint(endpoint);
        machineModel.setOwner(owner);
        machineModel.setOrganization(organization);
        machineModel.setPlatform(platform);
        machineModel.setDescription(description);
        return machineModel;
    }

}

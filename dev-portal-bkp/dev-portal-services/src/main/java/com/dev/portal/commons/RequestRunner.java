package com.dev.portal.commons;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.Instance;
import com.dev.portal.constants.PlatformType;
import com.dev.portal.constants.RequestStatus;
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
import com.dev.portal.models.CatalogRequestModel;
import com.dev.portal.models.MachineModel;
import com.dev.portal.models.RequestModel;
import com.dev.portal.models.VsphereCatalogModel;
import com.dev.portal.models.VsphereEndpointModel;
import com.vmware.vim25.TaskInfo;
import com.vmware.vim25.TaskInfoState;
import com.vmware.vim25.mo.Task;
import com.vmware.vim25.mo.VirtualMachine;

public class RequestRunner extends Thread {

    private CatalogRequestModel catalogRequestModel;
    private String platformType;
    private MongoTemplate mongoTemplate;
    private String loggedInUser = ServiceHelper.getLoggedInUser();

    public RequestRunner(MongoTemplate mongoTemplate, CatalogRequestModel catalogRequestModel, String platformType){
        this.mongoTemplate = mongoTemplate;
        this.catalogRequestModel = catalogRequestModel;
        this.platformType = platformType;
    }

    @Override
    public void run() {
        RequestDaoClient requestDaoClient = new RequestDaoClient(mongoTemplate);
        MachineDaoClient machineDaoClient = new MachineDaoClient(mongoTemplate);
        RequestModel requestModel = null;
        MachineModel machineModel = null;
        if(platformType.equals(PlatformType.AMAZON_AWS.toString())){
            AwsEndpointModel awsEndpointModel = new AwsEndpointDaoClient(mongoTemplate).getAwsEndpointModelByName(catalogRequestModel.getEndpointName());
            AmazonEC2 ec2 = new AwsClientHelper(awsEndpointModel.getAccessKey(), awsEndpointModel.getSecretKey(), Regions.fromName(awsEndpointModel.getRegion().replaceAll("_", "-").toLowerCase())).getAmazonEC2Client();
            AwsResourceHelper awsResourceHelper = new AwsResourceHelper();
            AwsCatalogModel awsCatalogModel = new AwsCatalogDaoClient(mongoTemplate).getAwsCatalogModelByName(catalogRequestModel.getCatalogName());
            String requestError = null;
            String requestStatus = RequestStatus.IN_PROGRESS.toString();
            Instance instance = null;
            String instanceName = catalogRequestModel.getRequestName()+"-"+UUID.randomUUID();
            try{
                instance = awsResourceHelper.requestEC2(ec2, instanceName, awsCatalogModel.getImageId(), awsCatalogModel.getInstanceType(), awsCatalogModel.getSecurityGroupName());
            } catch (Exception e){
                requestError = e.getMessage();
                e.printStackTrace();
            }
            requestModel = composeRequestModel(awsCatalogModel.getCatalogName(), awsCatalogModel.getEndpointName(), getCurrentTime(), instance.getInstanceId(), PlatformType.AMAZON_AWS.toString(), loggedInUser, instance.toString(), instanceName, requestError, RequestStatus.IN_PROGRESS.toString(), getCurrentTime());
            requestModel = requestDaoClient.addOrUpdateRequestModel(requestModel);
            machineModel = composeMachineModel(PlatformType.AMAZON_AWS.toString(), awsCatalogModel.getOrganizationName(), requestModel.getEndpointName(), requestModel.getRequestedBy(), instance.getPublicIpAddress(), instance.getState().getName(), requestModel.getCatalogName(), "", "", "", instance.toString());
            machineModel = machineDaoClient.addOrUpdateMachineModel(machineModel);
            for(int i=1; i<=45;i++){
                requestModel = requestDaoClient.getRequestModelById(requestModel.getId());
                if(requestModel.getRequestStatus().equals(RequestStatus.IN_PROGRESS.toString())){
                    CommonUtils.sleepSeconds(5);
                    instance = awsResourceHelper.getEC2InstanceDetails(ec2, instance.getInstanceId());
                    if("running".equals(instance.getState().getName())){
                        requestStatus = RequestStatus.SUCCESSFUL.toString();
                    }
                    if(i == 45){
                        requestStatus = RequestStatus.NOT_AVAILABLE.toString();
                    }
                    requestModel.setLastUpdatedTime(getCurrentTime());
                    requestModel.setMoreInfo(instance.toString());
                    requestModel.setRequestError(requestError);
                    requestModel.setRequestStatus(requestStatus);
                    requestModel = requestDaoClient.addOrUpdateRequestModel(requestModel);
                    machineModel.setMachineIP(instance.getPublicIpAddress());
                    machineModel.setPowerState(instance.getState().getName());
                    machineModel.setMoreInfo(instance.toString());
                    machineModel = machineDaoClient.addOrUpdateMachineModel(machineModel);
                } else {
                   break;
                }
            }
        } else if (platformType.equals(PlatformType.VMWARE_VSPHERE.toString())){
            VsphereEndpointModel vsphereEndpointModel = new VsphereEndpointDaoClient(mongoTemplate).getVsphereEndpointModelByName(catalogRequestModel.getEndpointName());
            VsphereResourceHelper vsphereResourceHelper = new VsphereResourceHelper(vsphereEndpointModel.getVcSdkUrl(), vsphereEndpointModel.getVcenterUser(), vsphereEndpointModel.getVcenterPassword());
            VsphereCatalogModel vsphereCatalogModel = new VsphereCatalogDaoClient(mongoTemplate).getVsphereCatalogModelByName(catalogRequestModel.getCatalogName());
            String requestError = null;
            String requestStatus = RequestStatus.IN_PROGRESS.toString();
            String vmName = catalogRequestModel.getRequestName()+"-"+UUID.randomUUID();
            Task task = vsphereResourceHelper.cloneVirtualMachine(vsphereEndpointModel.getVsphereComputes(), vsphereCatalogModel.getVmTemplate(), vmName);
            requestModel = composeRequestModel(vsphereCatalogModel.getCatalogName(), vsphereCatalogModel.getEndpointName(), getCurrentTime(), vmName, PlatformType.VMWARE_VSPHERE.toString(), loggedInUser, "", vmName, "", RequestStatus.IN_PROGRESS.toString(), getCurrentTime());
            requestModel = requestDaoClient.addOrUpdateRequestModel(requestModel);
            for(int i=0; i<=45; i++){
                requestModel = requestDaoClient.getRequestModelById(requestModel.getId());
                if(requestModel.getRequestStatus().equals(RequestStatus.IN_PROGRESS.toString())){
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
                        requestStatus = RequestStatus.SUCCESSFUL.toString();
                    } else if (TaskInfoState.error == taskInfoState){
                        requestStatus = RequestStatus.FAILED.toString();
                        requestError = taskInfo.getError().getLocalizedMessage();
                    } else if (i==45){
                        requestStatus = RequestStatus.NOT_AVAILABLE.toString();
                        requestError = taskInfo.getError().getLocalizedMessage();
                    }
                    requestModel.setLastUpdatedTime(getCurrentTime());
                    requestModel.setMoreInfo(taskInfoState.toString());
                    requestModel.setRequestError(requestError);
                    requestModel.setRequestStatus(requestStatus);
                    requestModel = requestDaoClient.addOrUpdateRequestModel(requestModel);
                    VirtualMachine virtualMachine = vsphereResourceHelper.getVirtualMachine(vmName);
                    String vmIP = "NOT AVAILABLE";
                    if(virtualMachine.getGuest() != null){
                        vmIP = virtualMachine.getGuest().getIpAddress();
                    }
                    machineModel = composeMachineModel(PlatformType.VMWARE_VSPHERE.toString(), vsphereCatalogModel.getOrganizationName(), requestModel.getEndpointName(), requestModel.getRequestedBy(), vmIP, virtualMachine.getSummary().runtime.powerState.toString(), requestModel.getCatalogName(), "", "", "", virtualMachine.toString());
                    machineModel = machineDaoClient.addOrUpdateMachineModel(machineModel);
                } else {
                    break;
                }
            }
        }
    }

    private MachineModel composeMachineModel(String machinePlatformType, String organizationName, String endpointName, String machineOwner, String machineIP, String powerState, String catalogName, String cpuCount, String memoryMB, String storageGB, String moreInfo) {
        MachineModel machineModel = new MachineModel();
        machineModel.setPlatformType(machinePlatformType);
        machineModel.setOrganizationName(organizationName);
        machineModel.setEndpointName(endpointName);
        machineModel.setMachineOwner(machineOwner);
        machineModel.setMachineIP(machineIP);
        machineModel.setPowerState(powerState);
        machineModel.setCatalogName(catalogName);
        machineModel.setCpuCount(cpuCount);
        machineModel.setMemoryMB(memoryMB);
        machineModel.setStorageGB(storageGB);
        machineModel.setMoreInfo(moreInfo);
        return machineModel;
    }

    private RequestModel composeRequestModel(String catalogName, String endpointName, String lastUpdatedTime, String platformRequestId, String platformType, String requestedBy, String moreInfo, String requestName, String requestError, String requestStatus, String submittedTime){
        RequestModel requestModel = new RequestModel();
        requestModel.setCatalogName(catalogName);
        requestModel.setEndpointName(endpointName);
        requestModel.setLastUpdatedTime(lastUpdatedTime);
        requestModel.setPlatformRequestId(platformRequestId);
        requestModel.setPlatformType(platformType);
        requestModel.setRequestedBy(requestedBy);
        requestModel.setRequestError(requestError);
        requestModel.setMoreInfo(moreInfo);
        requestModel.setRequestName(requestName);
        requestModel.setRequestStatus(requestStatus);
        requestModel.setSubmittedTime(submittedTime);
        return requestModel;
    }
    
    private String getCurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        return strDate;
    }
    
}

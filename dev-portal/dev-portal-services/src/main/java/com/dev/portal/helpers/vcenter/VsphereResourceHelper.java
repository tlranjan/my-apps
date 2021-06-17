package com.dev.portal.helpers.vcenter;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.vmware.vim25.ManagedObjectReference;
import com.vmware.vim25.VirtualMachineCloneSpec;
import com.vmware.vim25.VirtualMachineRelocateSpec;
import com.vmware.vim25.mo.ClusterComputeResource;
import com.vmware.vim25.mo.Datacenter;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.Task;
import com.vmware.vim25.mo.VirtualMachine;

public class VsphereResourceHelper {

    private ServiceInstance serviceInstance;

    public VsphereResourceHelper(String url, String username, String password) {
        try {
            this.serviceInstance = new ServiceInstance(new URL(url), username, password, true);
        } catch (RemoteException | MalformedURLException e) {

        }
    }

    public List<String> getVsphereComputes() {
        List<String> computeList = new ArrayList<>();
        try {
            ManagedEntity[] managedEntityArray = new InventoryNavigator(serviceInstance.getRootFolder())
                    .searchManagedEntities("ClusterComputeResource");
            for (ManagedEntity managedEntity : managedEntityArray) {
                computeList.add(managedEntity.getName());
            }
        } catch (RemoteException e) {

        }
        return computeList;
    }

    public Task cloneVirtualMachine(String clusterComputeResourceName, String vmNameToBeCloned, String  targetVmName){
        VirtualMachineCloneSpec virtualMachineCloneSpec = new VirtualMachineCloneSpec();
        VirtualMachineRelocateSpec virtualMachineRelocateSpec = new VirtualMachineRelocateSpec();
        try {
            virtualMachineRelocateSpec.setDatastore(getVirtualMachine(vmNameToBeCloned).getDatastores()[0].getMOR());
        } catch (RemoteException e) {
            
        }
        virtualMachineRelocateSpec.setPool(getClusterComputeResource(clusterComputeResourceName).getResourcePool().getMOR());
        virtualMachineCloneSpec.setLocation(virtualMachineRelocateSpec);
        virtualMachineCloneSpec.setPowerOn(true);
        virtualMachineCloneSpec.setTemplate(false);
        try {
            return getVirtualMachine(vmNameToBeCloned).cloneVM_Task(getClusterDatacenter(clusterComputeResourceName).getVmFolder(), targetVmName, virtualMachineCloneSpec);
        } catch (RemoteException e) {
            return null;
        }
    }

    public VirtualMachine getVirtualMachine(String vmName) {
        VirtualMachine virtualMachine = null;
        try {
            virtualMachine = (VirtualMachine) new InventoryNavigator(serviceInstance.getRootFolder()).searchManagedEntity("VirtualMachine", vmName);
        } catch (RemoteException e) {
            
        }
        return virtualMachine;
    }
    
    public ClusterComputeResource getClusterComputeResource(String clusterComputeResourceName) {
        ClusterComputeResource clusterComputeResource = null;
        try {
            clusterComputeResource = (ClusterComputeResource) new InventoryNavigator(serviceInstance.getRootFolder()).searchManagedEntity("ClusterComputeResource", clusterComputeResourceName);
        } catch (RemoteException e) {
            
        }
        return clusterComputeResource;
    }
    
    public Datacenter getClusterDatacenter(String clusterComputeResourceName){
        Datacenter datacenter = null;
        try {
            ClusterComputeResource clusterComputeResource = (ClusterComputeResource) new InventoryNavigator(serviceInstance.getRootFolder()).searchManagedEntity("ClusterComputeResource", clusterComputeResourceName);
            ManagedObjectReference managedObjectReference = clusterComputeResource.getParent().getParent().getMOR();
            if(managedObjectReference.getType().equalsIgnoreCase("Datacenter")){
                datacenter = new Datacenter(serviceInstance.getServerConnection(), managedObjectReference);
            }
        } catch (RemoteException e) {
            
        }
        return datacenter;
    }
    
}

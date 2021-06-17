package com.dev.portal.helpers.vcenter;

import com.vmware.vim25.mo.Task;

public class A1 {
    public static void main(String[] args) throws Exception {
        VsphereResourceHelper vsphereResourceHelper = new VsphereResourceHelper("https://10.10.10.10/sdk", "admin", "admin");
        Task task = vsphereResourceHelper.cloneVirtualMachine("Site02-Cluster-01", "Tiny-centos-Temp", "shashi-1");
        if(task.waitForTask().equalsIgnoreCase("success")){
            //get virtual machine details and put it into db
        } else {
            System.out.println(task.getTaskInfo().getError().getLocalizedMessage());
        }
    }
}

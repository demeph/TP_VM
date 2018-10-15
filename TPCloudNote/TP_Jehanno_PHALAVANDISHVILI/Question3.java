

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import com.vmware.vim25.*;
import com.vmware.vim25.HostCpuPackage;
import com.vmware.vim25.HostHardwareInfo;
import com.vmware.vim25.InvalidProperty;
import com.vmware.vim25.RuntimeFault;
import com.vmware.vim25.mo.HostSystem;
import com.vmware.vim25.mo.VirtualMachine;
import com.vmware.vim25.VirtualMachineConfigInfo;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ServiceInstance;

public class Question3 {
	static final String SERVER_NAME = "10.1.0.4";
	static final String USER_NAME = "root";
	static final String PASSWORD = "adminroot@";

	public static void main(String[] args) {
		String url = "https://" + SERVER_NAME + "/sdk/vimService";
		// List host systems
		try {
			ServiceInstance si = new ServiceInstance(new URL(url), USER_NAME, PASSWORD, true);
			ManagedEntity[] managedEntities = new InventoryNavigator(si.getRootFolder()).searchManagedEntities("VirtualMachine");
			for (ManagedEntity managedEntity : managedEntities) {
				VirtualMachine vm = (VirtualMachine) managedEntity;
				System.out.println("VM: '" + vm.getName() + "'");
				VirtualMachineConfigInfo vmInfo = (VirtualMachineConfigInfo) vm.getConfig();
				for (VirtualDevice vd : vmInfo.getHardware().getDevice()) { 
  
			        if (vd instanceof VirtualMachineVideoCard) { 
			            VirtualMachineVideoCard vVideoCard = (VirtualMachineVideoCard) vd; 
			        } else if (vd instanceof VirtualMachineVMCIDevice) { 
			            VirtualMachineVMCIDevice vVMCI = (VirtualMachineVMCIDevice) vd; 
			        } else if (vd instanceof VirtualSCSIPassthrough) { 
			            VirtualSCSIPassthrough vSCSI = (VirtualSCSIPassthrough) vd; 
			        } else if (vd instanceof VirtualDisk) { 
			            VirtualDisk vDisk = (VirtualDisk) vd; 
			            VirtualDiskFlatVer2BackingInfo vbi = (VirtualDiskFlatVer2BackingInfo) vd.getBacking(); 
			            System.out.println("--VirtualDisk : \n---Capacity : "+vDisk.capacityInKB + " KB");
			        } else if (vd instanceof VirtualCdrom) { 
			            VirtualCdrom vCDrom = (VirtualCdrom) vd; 
			            if (vCDrom.getBacking() instanceof VirtualCdromRemoteAtapiBackingInfo) { 
			            } else if (vCDrom.getBacking() instanceof VirtualCdromAtapiBackingInfo) { 
			                 VirtualCdromAtapiBackingInfo vabi = (VirtualCdromAtapiBackingInfo) vCDrom.getBacking(); 
			            } else if (vCDrom.getBacking() instanceof VirtualCdromIsoBackingInfo) { 
			                 VirtualCdromIsoBackingInfo vibi = (VirtualCdromIsoBackingInfo) vCDrom.getBacking(); 
			            } else if (vCDrom.getBacking() instanceof VirtualCdromRemotePassthroughBackingInfo) { 
			                 VirtualCdromRemotePassthroughBackingInfo vpbi = (VirtualCdromRemotePassthroughBackingInfo) vCDrom.getBacking(); 
			            } 
			        } else if (vd instanceof VirtualEthernetCard) { 
			            VirtualEthernetCard vEth = (VirtualEthernetCard) vd; 
	 				     if (vEth instanceof VirtualE1000) {  
	 				         System.out.println("---Adapter type: E1000");  
	 				     } else if (vEth instanceof VirtualPCNet32) {  
	 				         System.out.println("---Adapter type: PCnet32");  
	 				     } else if (vEth instanceof VirtualVmxnet) {  
	 				         System.out.println("---Adapter type: Vmxnet");  
	 				     } else if (vEth instanceof VirtualVmxnet2) {  
	 				         System.out.println("---Adapter type: Vmxnet2");  
	 				     } else if (vEth instanceof VirtualVmxnet3) {  
	 				         System.out.println("---Adapter type: Vmxnet3");  
	 				     }    
	 				     System.out.println("---Manual "+vEth.addressType);  
	 				     System.out.println("---MAC address: "+vEth.macAddress);
	 				 }
	 				}
			}
			si.getServerConnection().logout();
		} catch (InvalidProperty e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RuntimeFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


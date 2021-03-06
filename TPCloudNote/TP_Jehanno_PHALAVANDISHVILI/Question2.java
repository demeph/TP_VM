
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

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

public class Question2 {
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
				System.out.println("-- Config :");
//				System.out.println("---Fullname : " +vmInfo.guestFullName());
				System.out.println("---Version : "+ vmInfo.version);
				System.out.println("---CPU :  "+vmInfo.getHardware().numCPU+" vCPU \n---Memory : " +vmInfo.getHardware().memoryMB +" MB"); 
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


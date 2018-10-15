

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import com.vmware.vim25.*;

public class Question4 {
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
				VirtualMachineRuntimeInfo vmri = (VirtualMachineRuntimeInfo) vm.getRuntime();
 	            if(vmri.getPowerState() == VirtualMachinePowerState.poweredOn)
                {
                   	Task task = vm.powerOffVM_Task();
                    task.waitForMe();
                    System.out.println("vm:" + vm.getName() + " powered off.");
				} else if(vmri.getPowerState() == VirtualMachinePowerState.poweredOff) {
                    Task task = vm.powerOnVM_Task();
                    task.waitForMe();
                    System.out.println("vm:" + vm.getName() + " powered on.");
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


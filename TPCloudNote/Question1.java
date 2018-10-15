
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import com.vmware.vim25.HostCpuPackage;
import com.vmware.vim25.HostHardwareInfo;
import com.vmware.vim25.InvalidProperty;
import com.vmware.vim25.RuntimeFault;
import com.vmware.vim25.mo.HostSystem;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ServiceInstance;

public class ListHosts {
	static final String SERVER_NAME = "10.1.0.4";
	static final String USER_NAME = "root";
	static final String PASSWORD = "adminroot@";

	public static void main(String[] args) {
		String url = "https://" + SERVER_NAME + "/sdk/vimService";
		// List host systems
		try {
			ServiceInstance si = new ServiceInstance(new URL(url), USER_NAME, PASSWORD, true);
			ManagedEntity[] managedEntities = new InventoryNavigator(si.getRootFolder()).searchManagedEntities("HostSystem");
			for (ManagedEntity managedEntity : managedEntities) {
				HostSystem host = (HostSystem) managedEntity;
				System.out.println("Host: '" + host.getName() + "'");
				HostHardwareInfo hw = host.getHardware();
				System.out.println("Model: " + hw.getSystemInfo().getModel());
				System.out.println("Memory in Bytes: " + hw.getMemorySize());
				System.out.println("# of CPU Cores: " + hw.getCpuInfo().getNumCpuCores());
				HostCpuPackage[] cpuPkg = hw.getCpuPkg();
				for (int i = 0; i < cpuPkg.length; i++) {
					HostCpuPackage pkg = cpuPkg[i];
					System.out.println(pkg.getIndex() + " : " + pkg.getDescription() + " (vendor: " + pkg.getVendor() + ")");
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


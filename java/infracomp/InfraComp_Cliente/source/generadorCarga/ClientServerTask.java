package generadorCarga;

import clienteSP.ClienteSPInseguro;
import clienteSP.ClienteSPSeguro;
import uniandes.gload.core.Task;

public class ClientServerTask extends Task {

	@Override
	public void fail() {
		System.out.println(Task.MENSAJE_FAIL);
	}

	@Override
	public void success() {
		System.out.println(Task.OK_MESSAGE);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		try {
			@SuppressWarnings("unused")
			ClienteSPSeguro cliente = new ClienteSPSeguro("./data/clienteSP.properties");
//			ClienteSPInseguro cliente = new ClienteSPInseguro("./data/clienteSP.properties");
			this.success();
		} catch (Exception e) {
			this.fail();
			e.printStackTrace();
		}
	}

}

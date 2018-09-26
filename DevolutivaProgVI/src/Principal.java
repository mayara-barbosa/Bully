import java.io.IOException;
import java.net.DatagramPacket;

public class Principal 
{
	public static void main(String[] args) throws InterruptedException, IOException
	{
		Usuarios usuarios = new Usuarios();
		Communication communication = new Communication();
		
		ThreadAYA tAYA = new ThreadAYA(usuarios);
		ThreadIAA tIAA = new ThreadIAA(communication,usuarios);
		tIAA.start();
		tAYA.start();

		System.out.println("Processo: " + communication.getID());
		communication.sendMulticast("JOIN");
		
		while(true)
		{
			try 
			{
				DatagramPacket pct = communication.receiveMulticast();
				if( pct.getPort() != communication.getID() )
				{
					if( !usuarios.Contains(pct.getPort()) )
					{
						usuarios.Add(pct.getPort());
						communication.sendUnicast("WELCOME", pct.getAddress(), pct.getPort());
					}
				}
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

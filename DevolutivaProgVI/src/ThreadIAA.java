import java.io.IOException;
import java.net.DatagramPacket;

public class ThreadIAA extends Thread
{
	Communication c;
	Usuarios usuarios;
	public ThreadIAA(Communication c, Usuarios u)
	{
		this.c = c;
		this.usuarios = u;
	}
	
	public void run()
	{
		while(true)
		{
			try 
			{
				DatagramPacket pct = c.receiveUnicast(false);
				String conteudo = c.getData(pct);
				if( conteudo.equals("WELCOME") )
				{
					usuarios.Add(pct.getPort());
				}
				else if( conteudo.equals("AYA") )
				{
					c.sendUnicast("IAA", pct.getAddress(), pct.getPort());
				}
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}

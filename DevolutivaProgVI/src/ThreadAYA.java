import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ThreadAYA extends Thread
{
	Usuarios usuarios;
	public ThreadAYA(Usuarios u)
	{
		this.usuarios = u;
	}
	
	public void run()
	{
		try 
		{
			DatagramSocket socket = new DatagramSocket();
			socket.setSoTimeout(5 * 1000);
			
			String msg = "AYA";
			while(true)
			{
				synchronized (usuarios) 
				{
					for(int indice = usuarios.getListaUsuarios().size() -1; indice >= 0; indice-- )
					{
						try 
						{
							int porta = usuarios.getListaUsuarios().get(indice);
							DatagramPacket pacote = new DatagramPacket(msg.getBytes(), msg.length(), InetAddress.getLocalHost(), porta);
							socket.send(pacote);
							pacote = new DatagramPacket(new byte[1024], 1024);
							socket.receive(pacote);
						} 
						catch (Exception e) {
							// TODO Auto-generated catch block
							System.out.println("Removerrrrrrrrr: " + indice);
							usuarios.Remove(indice);
						}
					}
					
				}
				try {
					sleep(3 * 1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} 
		catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
	}	
	
}

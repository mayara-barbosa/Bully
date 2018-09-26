import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Comunicacao 
{
	private DatagramSocket unicastSocket;
	private MulticastSocket multicastSocket;
	private InetAddress multicastAddress;
	private int multicastPort;
	
	public Comunicacao() throws IOException
	{
		unicastSocket = new DatagramSocket();
		multicastPort = 8888;
		multicastAddress = InetAddress.getByName("224.0.0.1");
		multicastSocket = new MulticastSocket(multicastPort);
		multicastSocket.joinGroup(multicastAddress);
	}
		
	public void enviarMensagemUnicast(String mensagem, int porta) throws IOException
	{
		DatagramPacket p = new DatagramPacket(mensagem.getBytes(), mensagem.length(), InetAddress.getLocalHost(), porta);
		unicastSocket.send(p);
	}
	public void enviarMensagemMulticast(String mensagem) throws IOException
	{
		DatagramPacket p = new DatagramPacket(mensagem.getBytes(), mensagem.length(), multicastAddress, multicastPort);
		unicastSocket.send(p);
	}
	
	public DatagramPacket receberMensagemUnicast(int tempoMaximo) throws IOException
	{
		DatagramPacket p = new DatagramPacket(new byte[1024], 1024);
		unicastSocket.setSoTimeout(tempoMaximo);
		unicastSocket.receive(p);
		return p;
	}
	
	public DatagramPacket receberMensagemMulticast(int tempoMaximo) throws IOException
	{
		DatagramPacket p = new DatagramPacket(new byte[1024], 1024);
		multicastSocket.setSoTimeout(tempoMaximo);
		multicastSocket.receive(p);
		return p;
	}
	
	public static String getConteudo(DatagramPacket p)
	{
		return new String(p.getData(),p.getOffset(),p.getLength());
	}
}

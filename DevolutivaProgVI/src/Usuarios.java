import java.util.ArrayList;
import java.util.List;

public class Usuarios 
{
	List<Integer> listaUsuarios;
	
	public Usuarios()
	{
		listaUsuarios = new ArrayList<Integer>();
	}
	
	public synchronized void Add(int usuario)
	{
		if( !this.listaUsuarios.contains(usuario) )
		{
			this.listaUsuarios.add(usuario);
			Imprimir();
		}
	}
	
	public synchronized void Remove(int indice)
	{
		this.listaUsuarios.remove(indice);
		Imprimir();
	}	

	public synchronized boolean Contains(int p)
	{
		return this.listaUsuarios.contains(p);
	}
	
	public synchronized void Imprimir()
	{
		System.out.println("----------- Usuarios conectados -----------");
		for(int p : listaUsuarios )
		{
			System.out.println(p);
		}
	}
	
	public List<Integer> getListaUsuarios()
	{
		return listaUsuarios;
	}
}

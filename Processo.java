import java.io.IOException;
import java.net.DatagramPacket;

public class Processo {

    private static boolean isCoordinator= false;
    private static int portCordinator;
    public static void main(String args[]) throws IOException {

        Comunicacao communication = new Comunicacao();
        communication.enviarMensagemMulticast("WHO");

            try{

                communication.receberMensagemUnicast(2000);

            } catch (IOException e) {
                isCoordinator = true;
                communication.enviarMensagemMulticast("IAC");
            }

        while(true){
            DatagramPacket p,q;
            p = communication.receberMensagemUnicast(5000);
            String mensagem = (Comunicacao.getConteudo(p));

            if ( mensagem.equals("IAC")){
                portCordinator = p.getPort();
                communication.enviarMensagemUnicast("AYA", portCordinator);
                try{
                    q = communication.receberMensagemUnicast(5000);
                }catch(IOException e ){
                    communication.enviarMensagemMulticast("ELECTION");
                }


            }else if (mensagem.equals("AYA")){
                communication.enviarMensagemUnicast("IAA", p.getPort());

            }

        }


    }
}

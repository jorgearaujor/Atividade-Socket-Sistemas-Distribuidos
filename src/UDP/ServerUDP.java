package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.zone.ZoneRulesException;

/**
 *
 * O servidor fica em execução contínua aguardando requisições de clientes.
 * Cada cliente envia o nome de uma região (ex: America/Sao_Paulo) e o
 * servidor responde com o horário atual dessa região.
 *
 * @author Jorge
 * @version 1.0
 * @since 20/03/2026
 */
public class ServerUDP {

    public static void main(String[] args) {

        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket(6789);
            byte[] buffer = new byte[1024];

            System.out.println("Servidor UDP rodando");

            while (true) {

                // recebe requisição do cliente
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                // pega a região enviada
                String regiao = new String(
                        request.getData(),
                        0,
                        request.getLength()
                );

                String callbackString;

                try {
                    ZonedDateTime data = ZonedDateTime.now(ZoneId.of(regiao));

                    callbackString = "Na região de " + data.getZone() +
                            " é " + data.getHour() + ":" + data.getMinute();

                } catch (ZoneRulesException e) {
                    callbackString = "Região invalida";
                }

                // monta resposta
                byte[] dadosResposta = callbackString.getBytes();

                DatagramPacket callback = new DatagramPacket(
                        dadosResposta,
                        dadosResposta.length,
                        request.getAddress(),
                        request.getPort()
                );

                // envia resposta
                socket.send(callback);
            }

        } catch (SocketException e) {
            System.out.println("Erro no socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
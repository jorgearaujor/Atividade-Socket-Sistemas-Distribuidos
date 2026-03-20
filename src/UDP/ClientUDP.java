package UDP;

import java.net.*;
import java.util.Scanner;

/**
 * O programa envia uma string com o nome da região (ex: America/Sao_Paulo)
 * para um servidor UDP e recebe como resposta a data/hora formatada.
 *
 * @author Jorge
 * @version 1.0
 * @since 20/03/2026
 *
 */
public class ClientUDP {

    public static void main(String[] args) {

        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket();
            socket.setSoTimeout(5000);

            Scanner sc = new Scanner(System.in);

            System.out.print("Digite a região (ex: America/Sao_Paulo): ");
            String regiao = sc.nextLine();

            byte[] dados = regiao.getBytes();

            InetAddress ipServidor = InetAddress.getByName("localhost");
            int porta = 6789;

            // envia pro servidor
            DatagramPacket packet = new DatagramPacket(
                    dados,
                    dados.length,
                    ipServidor,
                    porta
            );

            socket.send(packet);

            // espera resposta
            byte[] buffer = new byte[1024];
            DatagramPacket packetCallback = new DatagramPacket(buffer, buffer.length);

            socket.receive(packetCallback);

            String callback = new String(
                    packetCallback.getData(),
                    0,
                    packetCallback.getLength()
            );

            System.out.println("Resposta do servidor: " + callback);

        } catch (SocketTimeoutException e) {
            System.out.println("Servidor ocupado ou offline");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
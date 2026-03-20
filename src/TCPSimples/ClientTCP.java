package TCPSimples;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Cliente TCP para consulta de horário por região.
 *
 * O cliente se conecta ao servidor, envia uma string com o nome
 * da região (ex: Asia/Tokyo) e recebe como resposta o horário
 * atual dessa região.
 *
 * A comunicação é feita utilizando streams (entrada e saída).
 *
 * @author Jorge
 * @version 1.0
 * @since 20/03/2026
 */
public class ClientTCP {

    public static void main(String[] args) {

        Socket socket = null;

        try {
            int porta = 1224;
            String server = "localhost";

            socket = new Socket(server, porta);

            Scanner scanner = new Scanner(System.in);

            System.out.print("Digite sua região: ");
            String mensagem = scanner.nextLine();

            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            BufferedWriter output = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));

            // envia para o servidor
            output.write(mensagem);
            output.newLine();
            output.flush();

            // recebe callback
            String callback = input.readLine();

            System.out.println("Resposta: " + callback);

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Erro ao fechar socket: " + e.getMessage());
                }
            }
        }
    }
}

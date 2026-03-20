package TCPMultithread;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Cliente TCP para consultar horário por região.
 *
 * O usuário digita uma região (ex: America/Sao_Paulo),
 * o cliente envia para o servidor e recebe o horário como resposta.
 *
 * @author Jorge
 * @version 1.0
 * @since 20/03/2026
 */
public class ClientTCP {

    public static void main(String[] args) {

        Socket socket = null;

        try {
            String host = "localhost";
            int porta = 1223;

            socket = new Socket(host, porta);

            Scanner sc = new Scanner(System.in);

            System.out.print("Digite a região: ");
            String regiao = sc.nextLine();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));

            // envia para o servidor
            out.write(regiao);
            out.newLine();
            out.flush();

            // recebe resposta
            String callback = in.readLine();

            System.out.println("Resposta: " + callback);

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Erro ao fechar conexão");
                }
            }
        }
    }
}

package TCPSimples;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.zone.ZoneRulesException;

/**
 * Servidor TCP simples para consulta de horário por região.
 *
 * O servidor fica aguardando conexões de clientes. Quando um cliente conecta,
 * ele envia uma string com a região (ex: Europe/London) e o servidor responde
 * com o horário atual dessa região.
 *
 * O atendimento é sequencial, ou seja, um cliente por vez.
 *
 * Caso a região seja inválida, retorna uma mensagem de erro.
 *
 * @author Jorge
 * @version 1.0
 * @since 20/03/2026
 */
public class ServerTCP {

    public static void main(String[] args) {

        int porta = 1224;
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(porta);
            System.out.println("Servidor esperando conexão " + porta);

            while (true) {

                try (Socket clientSocket = serverSocket.accept()) {

                    BufferedReader input = new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream()));

                    BufferedWriter output = new BufferedWriter(
                            new OutputStreamWriter(clientSocket.getOutputStream()));

                    System.out.println("Cliente conectado: " +
                            clientSocket.getInetAddress());

                    String regiao = input.readLine();
                    String callback;

                    if (regiao != null) {
                        try {
                            ZonedDateTime data = ZonedDateTime.now(ZoneId.of(regiao));

                            callback = "Na região de " + data.getZone() +
                                    " são " + data.getHour() + ":" + data.getMinute();

                        } catch (ZoneRulesException e) {
                            callback = "Região invalida";
                        }

                        output.write(callback);
                        output.newLine();
                        output.flush();
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Erro de IO: " + e.getMessage());
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    System.out.println("Erro ao fechar servidor: " + e.getMessage());
                }
            }
        }
    }
}
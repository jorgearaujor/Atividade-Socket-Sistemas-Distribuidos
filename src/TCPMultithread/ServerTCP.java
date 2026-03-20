package TCPMultithread;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Diferente da versão simples, este servidor cria uma nova Thread
 * para cada cliente conectado, permitindo atender vários clientes
 * ao mesmo tempo.
 *
 * Cada conexão é tratada separadamente pela classe AtendeCliente.
 *
 * @author Jorge
 * @version 1.0
 * @since 20/03/2026
 */
public class ServerTCP {

    public static void main(String[] args) {

        int porta = 1223;

        try (ServerSocket servweSocket = new ServerSocket(porta)) {

            System.out.println("Servidor concorrente esperando conexão na porta " + porta);

            while (true) {

                Socket clientSocket = servweSocket.accept();

                // cria uma nova thread para cada cliente
                Thread thread = new Thread(new Callback(clientSocket));
                thread.start();
            }

        } catch (IOException e) {
            System.out.println("Erro de IO: " + e.getMessage());
        }
    }
}
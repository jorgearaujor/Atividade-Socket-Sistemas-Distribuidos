package TCPMultithread;

import java.io.*;
import java.net.Socket;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.zone.ZoneRulesException;

/**
 * Classe responsável por atender cada cliente em uma thread separada.
 *
 * Recebe a região enviada pelo cliente e retorna o horário correspondente.
 *
 * @author Jorge
 * @version 1.0
 * @since 20/03/2026
 */
public class Callback implements Runnable {

    private Socket socket;

    public Callback(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        System.out.println(
                "Thread " + Thread.currentThread().getId() +
                        " " +
                        socket.getInetAddress() + ":" + socket.getPort()
        );

        try (
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                BufferedWriter output = new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream()))
        ) {

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

                // só pra simular processamento (opcional)
                Thread.sleep(2000);

                output.write(callback);
                output.newLine();
                output.flush();
            }

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Thread interrompida");
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                System.out.println("Erro ao fechar conexão");
            }
        }
    }
}

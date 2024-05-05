package s1riys.lab7.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import s1riys.lab7.client.console.StandardConsole;
import s1riys.lab7.client.managers.RuntimeManager;
import s1riys.lab7.client.network.UDPClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static s1riys.lab7.common.constants.Network.PORT;

public class Main {
    public static Logger logger = LogManager.getLogger("ServerLogger");

    public static void main(String[] args) {
        var console = new StandardConsole();

        try {
            var client = new UDPClient(InetAddress.getLocalHost(), PORT);
            var cli = new RuntimeManager(client, console);

            cli.interactiveMode();
        } catch (UnknownHostException e) {
            System.out.println("Неизвестный хост");
            logger.error("Неизвестный хост", e);
        } catch (IOException e) {
            System.out.println("Невозможно подключиться к серверу!");
            logger.error("Невозможно подключиться к серверу!", e);
        }
    }
}

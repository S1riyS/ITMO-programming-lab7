package s1riys.lab7.client.commands;

import s1riys.lab7.client.commands.utils.ValidationHelper;
import s1riys.lab7.client.console.IConsole;
import s1riys.lab7.client.managers.SessionManager;
import s1riys.lab7.client.network.UDPClient;
import s1riys.lab7.common.constants.Commands;
import s1riys.lab7.common.exceptions.APIException;
import s1riys.lab7.common.network.requests.PrintDescendingRequest;
import s1riys.lab7.common.network.responses.PrintDescendingResponse;

import java.io.IOException;

import static s1riys.lab7.client.commands.utils.SignatureHelper.defineSignature;

public class PrintDescending extends ServersideCommand {
    public PrintDescending(IConsole console, UDPClient client) {
        super(
                defineSignature(Commands.PRINT_DESCENDING),
                "Выводит элементы коллекции в порядке убывания",
                console,
                client
        );
    }

    @Override
    public Boolean executeImpl(String[] data) {
        try {
            PrintDescendingRequest request = new PrintDescendingRequest(SessionManager.getCurrentUser());
            PrintDescendingResponse response = (PrintDescendingResponse) client.sendAndReceiveCommand(request);
            ValidationHelper.validateResponse(response);

            if (response.products.isEmpty()) console.println("Коллекция пуста");
            else response.products.forEach(console::println);
            return true;

        } catch (IOException e) {
            console.printError("Ошибка взаимодействия с сервером");
        } catch (APIException e) {
            console.printError(e.getMessage());
        }
        return false;
    }
}

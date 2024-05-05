package s1riys.lab7.client.commands;

import static s1riys.lab7.client.commands.utils.SignatureHelper.defineSignature;

import s1riys.lab7.client.commands.utils.ValidationHelper;
import s1riys.lab7.client.console.IConsole;
import s1riys.lab7.client.forms.ProductForm;
import s1riys.lab7.client.managers.SessionManager;
import s1riys.lab7.client.network.UDPClient;
import s1riys.lab7.common.constants.Commands;
import s1riys.lab7.common.exceptions.APIException;
import s1riys.lab7.common.exceptions.InvalidFormException;
import s1riys.lab7.common.exceptions.WrongAmountOfElementsException;
import s1riys.lab7.common.models.Product;
import s1riys.lab7.common.network.requests.UpdateRequest;
import s1riys.lab7.common.network.responses.UpdateResponse;

import java.io.IOException;

public class Update extends ServersideCommand {
    public Update(IConsole console, UDPClient client) {
        super(
                defineSignature(Commands.UPDATE, "<id> {product}"),
                "Обновляет значение элемента коллекции, id которого равен заданному",
                console,
                client
        );
    }

    @Override
    public Boolean executeImpl(String[] data) {
        try {
            ValidationHelper.validateArgsLength(data, 1);
            long id = Long.parseLong(data[0]);

            Product product = new ProductForm(console).build();

            UpdateRequest request = new UpdateRequest(id, product, SessionManager.getCurrentUser());
            UpdateResponse response = (UpdateResponse) client.sendAndReceiveCommand(request);
            ValidationHelper.validateResponse(response);

            console.println("Продукт успешно обновлен.");
            return true;

        } catch (InvalidFormException e) {
            console.printError("Поля продукта не валидны");
        } catch (WrongAmountOfElementsException e) {
            console.printError("Неверное количество аргументов");
        } catch (NumberFormatException e) {
            console.printError("id должен быть представлен числом");
        } catch (IOException e) {
            console.printError("Ошибка взаимодействия с сервером");
        } catch (APIException e) {
            console.printError(e.getMessage());
        }
        return false;
    }
}

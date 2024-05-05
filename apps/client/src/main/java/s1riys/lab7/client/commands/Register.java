package s1riys.lab7.client.commands;

import s1riys.lab7.client.commands.utils.ValidationHelper;
import s1riys.lab7.client.console.IConsole;
import s1riys.lab7.client.forms.RegisterForm;
import s1riys.lab7.client.managers.SessionManager;
import s1riys.lab7.client.network.UDPClient;
import s1riys.lab7.common.constants.Commands;
import s1riys.lab7.common.exceptions.APIException;
import s1riys.lab7.common.exceptions.InvalidFormException;
import s1riys.lab7.common.models.User;
import s1riys.lab7.common.network.requests.RegisterRequest;
import s1riys.lab7.common.network.responses.RegisterResponse;

import java.io.IOException;

import static s1riys.lab7.client.commands.utils.SignatureHelper.defineSignature;

public class Register extends AuthCommand {
    public Register(IConsole console, UDPClient client) {
        super(
                defineSignature(Commands.REGISTER),
                "Регистрация нового пользователя",
                console,
                client
        );
    }

    @Override
    public Boolean execute(String[] data) {
        try {
            if (SessionManager.getCurrentUser() != null) {
                console.printError("Вы уже вошли в аккаунт");
                return false;
            }

            User newUser = new RegisterForm(console).build();

            RegisterRequest request = new RegisterRequest(newUser);
            RegisterResponse response = (RegisterResponse) client.sendAndReceiveCommand(request);
            ValidationHelper.validateResponse(response);

            SessionManager.setCurrentUser(response.user);

            console.println("Новый аккаунт успешно создан (id=%s)".formatted(response.user));
            return true;

        } catch (InvalidFormException e) {
            console.printError("Поля пользователя содержат невалидные данные");
        } catch (IOException e) {
            console.printError("Ошибка взаимодействия с сервером");
        } catch (APIException e) {
            console.printError(e.getMessage());
        }

        return false;
    }
}

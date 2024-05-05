package s1riys.lab7.client.commands.utils;

import s1riys.lab7.common.exceptions.APIException;
import s1riys.lab7.common.exceptions.WrongAmountOfElementsException;
import s1riys.lab7.common.network.responses.Response;

public class ValidationHelper {
    public static void validateArgsLength(String[] args, int targetLength) throws WrongAmountOfElementsException {
        if (args.length != targetLength) throw new WrongAmountOfElementsException();
    }

    public static void validateResponse(Response response) throws APIException {
        if (response.getError() != null && !response.getError().isEmpty()) {
            throw new APIException(response.getError());
        }
    }
}

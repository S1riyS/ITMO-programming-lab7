package s1riys.lab7.common.network.responses;

import s1riys.lab7.common.constants.Commands;

public class RemoveKeyResponse extends Response {
    public RemoveKeyResponse(String error) {
        super(Commands.REMOVE_KEY, error);
    }
}

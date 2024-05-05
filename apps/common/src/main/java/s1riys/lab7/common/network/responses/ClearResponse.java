package s1riys.lab7.common.network.responses;

import s1riys.lab7.common.constants.Commands;

public class ClearResponse extends Response {
    public ClearResponse(String error) {
        super(Commands.CLEAR, error);
    }
}

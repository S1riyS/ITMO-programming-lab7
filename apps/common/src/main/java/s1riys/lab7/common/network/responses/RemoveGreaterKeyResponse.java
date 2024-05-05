package s1riys.lab7.common.network.responses;

import s1riys.lab7.common.constants.Commands;

public class RemoveGreaterKeyResponse extends Response {
    public final int count;

    public RemoveGreaterKeyResponse(int count, String error) {
        super(Commands.REMOVE_GREATER, error);
        this.count = count;
    }
}

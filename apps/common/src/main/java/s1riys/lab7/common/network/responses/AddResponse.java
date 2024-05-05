package s1riys.lab7.common.network.responses;

import s1riys.lab7.common.constants.Commands;

public class AddResponse extends Response {
    public final long newId;

    public AddResponse(long newId, String error) {
        super(Commands.INSERT, error);
        this.newId = newId;
    }
}

package s1riys.lab7.common.network.responses;

import s1riys.lab7.common.constants.Commands;

public class UpdateResponse extends Response {
    public UpdateResponse(String error) {
        super(Commands.UPDATE, error);
    }
}

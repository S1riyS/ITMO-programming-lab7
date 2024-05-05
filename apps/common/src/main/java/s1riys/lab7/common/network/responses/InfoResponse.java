package s1riys.lab7.common.network.responses;

import s1riys.lab7.common.constants.Commands;

public class InfoResponse extends Response {
    public final String infoMessage;

    public InfoResponse(String infoMessage, String error) {
        super(Commands.INFO, error);
        this.infoMessage = infoMessage;
    }
}

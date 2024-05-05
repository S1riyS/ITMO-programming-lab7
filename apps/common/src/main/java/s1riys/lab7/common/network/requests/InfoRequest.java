package s1riys.lab7.common.network.requests;

import s1riys.lab7.common.constants.Commands;
import s1riys.lab7.common.models.User;

public class InfoRequest extends Request {
    public InfoRequest(User user) {
        super(Commands.INFO, user);
    }
}

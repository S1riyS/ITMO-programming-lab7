package s1riys.lab7.common.network.requests;

import s1riys.lab7.common.constants.Commands;
import s1riys.lab7.common.models.User;

public class ShowRequest extends Request {
    public ShowRequest(User user) {
        super(Commands.SHOW, user);
    }
}

package s1riys.lab7.common.network.requests;

import s1riys.lab7.common.constants.Commands;
import s1riys.lab7.common.models.User;

public class LoginRequest extends Request {
    public LoginRequest(User user) {
        super(Commands.LOGIN, user);
    }
}

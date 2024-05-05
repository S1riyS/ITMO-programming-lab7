package s1riys.lab7.common.network.requests;

import s1riys.lab7.common.constants.Commands;
import s1riys.lab7.common.models.User;

public class RegisterRequest extends Request {
    public RegisterRequest(User user) {
        super(Commands.REGISTER, user);
    }
}

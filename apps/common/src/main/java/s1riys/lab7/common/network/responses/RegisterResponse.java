package s1riys.lab7.common.network.responses;

import s1riys.lab7.common.constants.Commands;
import s1riys.lab7.common.models.User;

public class RegisterResponse extends Response {
    public final User user;

    public RegisterResponse(User user, String error) {
        super(Commands.REGISTER, error);
        this.user = user;
    }
}

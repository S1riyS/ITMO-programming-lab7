package s1riys.lab7.common.network.requests;

import s1riys.lab7.common.constants.Commands;
import s1riys.lab7.common.models.User;

public class PrintDescendingRequest extends Request {
    public PrintDescendingRequest(User user) {
        super(Commands.PRINT_DESCENDING, user);
    }
}

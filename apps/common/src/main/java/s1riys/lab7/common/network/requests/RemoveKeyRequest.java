package s1riys.lab7.common.network.requests;

import s1riys.lab7.common.constants.Commands;
import s1riys.lab7.common.models.User;

public class RemoveKeyRequest extends Request {
    public final long id;
    public RemoveKeyRequest(long id, User user) {
        super(Commands.REMOVE_KEY, user);
        this.id = id;
    }
}

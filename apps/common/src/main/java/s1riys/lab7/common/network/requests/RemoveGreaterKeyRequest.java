package s1riys.lab7.common.network.requests;

import s1riys.lab7.common.constants.Commands;
import s1riys.lab7.common.models.User;

public class RemoveGreaterKeyRequest extends Request {
    public final Long id;
    public RemoveGreaterKeyRequest(Long id, User user) {
        super(Commands.REMOVE_GREATER_KEY, user);
        this.id = id;
    }
}

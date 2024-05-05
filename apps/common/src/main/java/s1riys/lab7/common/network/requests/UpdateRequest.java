package s1riys.lab7.common.network.requests;

import s1riys.lab7.common.constants.Commands;
import s1riys.lab7.common.models.Product;
import s1riys.lab7.common.models.User;

public class UpdateRequest extends Request {
    public final long id;
    public final Product product;
    public UpdateRequest(long id, Product product, User user) {
        super(Commands.UPDATE, user);
        this.id = id;
        this.product = product;
    }
}

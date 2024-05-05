package s1riys.lab7.common.network.requests;

import s1riys.lab7.common.models.Product;
import s1riys.lab7.common.constants.Commands;
import s1riys.lab7.common.models.User;

public class AddRequest extends Request {
    public final Product product;

    public AddRequest(Product product, User user) {
        super(Commands.INSERT, user);
        this.product = product;
    }
}

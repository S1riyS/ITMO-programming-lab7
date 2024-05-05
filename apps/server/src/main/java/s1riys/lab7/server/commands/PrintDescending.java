package s1riys.lab7.server.commands;

import s1riys.lab7.common.constants.Commands;
import s1riys.lab7.common.models.Product;
import s1riys.lab7.common.network.requests.Request;
import s1riys.lab7.common.network.responses.PrintDescendingResponse;
import s1riys.lab7.common.network.responses.Response;
import s1riys.lab7.server.managers.CollectionManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NavigableSet;

public class PrintDescending extends RepositoryCommand {
    public PrintDescending(CollectionManager collectionManager) {
        super(Commands.PRINT_DESCENDING, collectionManager);
    }

    @Override
    public Response execute(Request data) {
        try {
            List<Product> products = collectionManager.getSortedCollection();

            List<Product> reversedProducts = new ArrayList<>(products);
            Collections.reverse(reversedProducts);
            return new PrintDescendingResponse(reversedProducts, null);

        } catch (Exception e) {
            return new PrintDescendingResponse(null, e.toString());
        }
    }
}

package s1riys.lab7.server.commands;

import s1riys.lab7.common.constants.Commands;
import s1riys.lab7.common.models.Product;
import s1riys.lab7.common.network.requests.FilterByManufacturerRequest;
import s1riys.lab7.common.network.requests.Request;
import s1riys.lab7.common.network.responses.FilterByManufacturerResponse;
import s1riys.lab7.common.network.responses.Response;
import s1riys.lab7.server.managers.CollectionManager;

import java.util.List;
import java.util.Objects;

public class FilterByManufacturer extends RepositoryCommand {
    public FilterByManufacturer(CollectionManager collectionManager) {
        super(Commands.FILTER_BY_MANUFACTURER, collectionManager);
    }

    @Override
    public Response execute(Request data) {
        try {
            FilterByManufacturerRequest request = (FilterByManufacturerRequest) data;

            List<Product> products = collectionManager.getCollection().values().stream()
                    .filter(product -> Objects.equals(product.getManufacturer(), request.organization))
                    .toList();

            return new FilterByManufacturerResponse(products, null);
        } catch (Exception e) {
            return new FilterByManufacturerResponse(null, "Ошибка при поиске продуктов");
        }
    }
}

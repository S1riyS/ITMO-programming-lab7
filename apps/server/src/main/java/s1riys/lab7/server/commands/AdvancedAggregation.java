package s1riys.lab7.server.commands;

import s1riys.lab7.common.constants.Commands;
import s1riys.lab7.common.models.Product;
import s1riys.lab7.common.network.requests.AdvancedAggregationRequest;
import s1riys.lab7.common.network.requests.Request;
import s1riys.lab7.common.network.responses.AdvancedAggregationResponse;
import s1riys.lab7.common.network.responses.Response;
import s1riys.lab7.server.managers.CollectionManager;

public class AdvancedAggregation extends RepositoryCommand {
    public AdvancedAggregation(CollectionManager collectionManager) {
        super(Commands.ADVANCED_AGGREGATION, collectionManager);
    }

    @Override
    public Response execute(Request data) {
        try {
            AdvancedAggregationRequest request = (AdvancedAggregationRequest) data;

            Double averageDistance = collectionManager.getCollection().values().stream()
                    .parallel()
                    .filter(product -> product.getPrice() > request.minPrice)
                    .filter(product -> product.getManufacturer() != null)
                    .filter(product -> request.minAnnualTurnover < product.getManufacturer().getAnnualTurnover())
                    .filter(product -> request.maxAnnualTurnover > product.getManufacturer().getAnnualTurnover())
                    .map(Product::getCoordinates)
                    .mapToDouble(coordinates -> Math.sqrt(Math.pow(coordinates.getX(), 2) + Math.pow(coordinates.getY(), 2)))
                    .average()
                    .orElse(-1);

            return new AdvancedAggregationResponse(averageDistance, null);
        } catch (Exception e) {
            return new AdvancedAggregationResponse((double) -1, "Не удалось подсчитать среднее расстояние");
        }
    }
}

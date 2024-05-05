package s1riys.lab7.common.network.requests;

import s1riys.lab7.common.constants.Commands;
import s1riys.lab7.common.models.User;

public class AdvancedAggregationRequest extends Request {
    public final long minPrice;
    public final Double minAnnualTurnover;
    public final Double maxAnnualTurnover;
    public AdvancedAggregationRequest(long minPrice, Double minAnnualTurnover, Double maxAnnualTurnover, User user) {
        super(Commands.ADVANCED_AGGREGATION, user);
        this.minPrice = minPrice;
        this.minAnnualTurnover = minAnnualTurnover;
        this.maxAnnualTurnover = maxAnnualTurnover;
    }
}

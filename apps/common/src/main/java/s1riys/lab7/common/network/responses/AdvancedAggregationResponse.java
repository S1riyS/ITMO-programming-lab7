package s1riys.lab7.common.network.responses;

import s1riys.lab7.common.constants.Commands;

public class AdvancedAggregationResponse extends Response {
    public final Double averageDistance;

    public AdvancedAggregationResponse(Double averageDistance, String error) {
        super(Commands.ADVANCED_AGGREGATION, error);
        this.averageDistance = averageDistance;
    }
}

package s1riys.lab7.common.network.requests;

import s1riys.lab7.common.constants.Commands;
import s1riys.lab7.common.models.Organization;
import s1riys.lab7.common.models.User;

public class FilterByManufacturerRequest extends Request {
    public final Organization organization;
    public FilterByManufacturerRequest(Organization organization, User user) {
        super(Commands.FILTER_BY_MANUFACTURER, user);
        this.organization = organization;
    }
}

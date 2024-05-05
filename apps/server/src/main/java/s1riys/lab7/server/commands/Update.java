package s1riys.lab7.server.commands;

import s1riys.lab7.common.constants.Commands;
import s1riys.lab7.common.network.requests.Request;
import s1riys.lab7.common.network.requests.UpdateRequest;
import s1riys.lab7.common.network.responses.Response;
import s1riys.lab7.common.network.responses.UpdateResponse;
import s1riys.lab7.server.managers.CollectionManager;

public class Update extends RepositoryCommand {
    public Update(CollectionManager collectionManager) {
        super(Commands.UPDATE, collectionManager);
    }

    @Override
    public Response execute(Request data) {
        UpdateRequest request = (UpdateRequest) data;
        try {
            if (!collectionManager.getCollection().containsKey(request.id)) {
                return new UpdateResponse("Не существует продукта с таким id");
            }
            collectionManager.update(request.id, request.product, request.getUser());
            return new UpdateResponse(null);

        } catch (Exception e) {
            return new UpdateResponse("Не удалось обновить продукт");
        }
    }
}

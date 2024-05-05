package s1riys.lab7.server.managers;

import org.apache.logging.log4j.Logger;
import s1riys.lab7.common.exceptions.MustBeUniqueException;
import s1riys.lab7.common.models.User;
import s1riys.lab7.server.Main;
import s1riys.lab7.server.Services.UserService;
import s1riys.lab7.server.Services.utils.ServiceLocator;
import s1riys.lab7.server.entities.UserEntity;

public class AuthManager {
    private final int SALT_LENGTH = 10;
    private final Logger logger = Main.logger;
    private final UserService userService = (UserService) ServiceLocator.getService("UserService");

    public AuthManager() {

    }

    public long register(User user) {
        try {
            return userService.add(user);
        } catch (MustBeUniqueException e) {
            return -1;
        }
    }

    public long login(User user) {
        UserEntity userEntity = userService.findByName(user.getName());

        boolean passwordMatches;
        if (userEntity != null) {
            String expectedPassword = user.getPassword();
            String salt = userEntity.getSalt();
            String realHashedPassword = userEntity.getHashedPassword();
            passwordMatches = userService.comparePasswords(expectedPassword, salt, realHashedPassword);
        } else {
            passwordMatches = false;
        }

        if (userEntity == null || !passwordMatches) return -1;
        return userEntity.getId();
    }
}

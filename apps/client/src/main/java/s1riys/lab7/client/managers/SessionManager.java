package s1riys.lab7.client.managers;

import s1riys.lab7.common.models.User;

public class SessionManager {
    private static User currentUser = null;

    public static User getCurrentUser() {
        return SessionManager.currentUser;
    }

    public static void setCurrentUser(User user) {
        SessionManager.currentUser = user;
    }
}

package org.novak.java.model.user;

import java.util.List;

public class UserRepository {

    private UserRepository instance;

    private List<User> users;
    private User activeUser;

    public void setActiveUser(User user) {
        activeUser = user;
    }
}

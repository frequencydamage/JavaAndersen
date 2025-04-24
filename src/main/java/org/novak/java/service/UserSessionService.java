package org.novak.java.service;

import lombok.Data;
import org.novak.java.model.user.User;
import org.springframework.stereotype.Service;

@Service
@Data
public class UserSessionService {

    private User activeUser;
}

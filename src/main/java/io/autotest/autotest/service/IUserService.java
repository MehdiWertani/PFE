package io.autotest.autotest.service;

import io.autotest.autotest.entities.Role;
import io.autotest.autotest.entities.User;

import java.util.List;

public interface IUserService {
    User save(User user);
    void delete(User user);
    User update(User user );
User updateRole(User user,Role role);
List<User> getAll();

}

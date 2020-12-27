package io.autotest.autotest.service;

import io.autotest.autotest.common.MailMessage;
import io.autotest.autotest.entities.Role;
import io.autotest.autotest.entities.User;

import java.util.List;
import java.util.Set;

public interface IUserService {

    User save(User user);

    void delete(User user);

    User update(User user);

    User updateRole(User user, Role role);

    List<User> getAll();

    Set<String> findUserEmails();

    void notifyUsersByMail(MailMessage message);

}

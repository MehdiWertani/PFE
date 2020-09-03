package io.autotest.autotest.security.services;
import org.springframework.security.core.userdetails.UserDetails;
import io.autotest.autotest.dao.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.autotest.autotest.entities.User;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    IUserDao iUserDao ;
@Autowired
    public UserDetailsServiceImpl(IUserDao iUserDao) {
        this.iUserDao = iUserDao;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = iUserDao.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }
}

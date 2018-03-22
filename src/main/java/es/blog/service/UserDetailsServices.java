package es.blog.service;

import es.blog.model.SystemUser;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.QueryException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Quini
 */
@Repository
public class UserDetailsServices implements UserDetailsService {

    private final UserService userService;

    /**
     *
     * @param userService
     */
    public UserDetailsServices(UserService userService) {
        this.userService = userService;
    }

    /**
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     * @throws InternalAuthenticationServiceException
     */
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, InternalAuthenticationServiceException {
        try {
            SystemUser user = userService.find(username);//userService.getUserByNick(username);
            if (user == null) {
                throw new UsernameNotFoundException("User '" + username + "' not found.");
            }
            List<GrantedAuthority> auth = new ArrayList<>();
            auth.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return new User(user.getUsername(), user.getPassword(), auth);
        } catch (SQLException | QueryException | UsernameNotFoundException ex) {
            throw new UsernameNotFoundException("User '" + username + "' not found.");
        }
    }
}

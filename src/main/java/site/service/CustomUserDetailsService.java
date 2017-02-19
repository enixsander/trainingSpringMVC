package site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String login)  {
        try {
            User user = userService.loadUserByUsername(login);
            return new org.springframework.security.core.userdetails.User(user.getLogin(),
                    user.getPassword(), true, true,
                    true, true, getGrantedAuthorities(user));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserRole()));

        return authorities;
    }
}

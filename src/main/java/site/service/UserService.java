package site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.model.Marker;
import site.model.User;
import site.repository.MarkerRepository;
import site.repository.UserRepository;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final MarkerRepository markerRepository;

    @Autowired
    public UserService(UserRepository userRepository, MarkerRepository markerRepository) {
        this.userRepository = userRepository;
        this.markerRepository = markerRepository;
    }

    public void saveUser(User user) {
        try {
            userRepository.persist(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try {
            userRepository.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(long id) {
        try {
            User user = userRepository.findByUserID(id);
            Set<Marker> markers =user.getMarker();

            // Delete associated markers
            for (Marker marker : markers) {
                markerRepository.delete(marker);
            }
            userRepository.delete(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List getAllUsers() {
        try {
            return userRepository.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    User loadUserByUsername(String username) throws UsernameNotFoundException, SQLException {
        try {
            return userRepository.findByUserName(username);
        } catch (NoResultException noResult) {
            throw new UsernameNotFoundException(username + " user not found");
        }
    }

    User getCurrentUser() throws SQLException {
        return loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}

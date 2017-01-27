package site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.model.User;
import site.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
            User user = new User();
            user.set_id(id);
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
}

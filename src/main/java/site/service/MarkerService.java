package site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.model.Marker;
import site.model.User;
import site.repository.MarkerRepository;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class MarkerService {
    private final MarkerRepository markerRepository;
    private final UserService userService;

    @Autowired
    public MarkerService(MarkerRepository markerRepository, UserService userService) {
        this.markerRepository = markerRepository;
        this.userService = userService;
    }

    public void saveMarker(Marker marker) {
        try {
            // TODO может заменить merge?!
            marker.setUser(userService.getCurrentUser());
            markerRepository.persist(marker);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMarker(Marker marker) {
        try {
            marker.setUser(userService.getCurrentUser());
            markerRepository.update(marker);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMarker(long id) {
        try {
            Marker marker = markerRepository.findByMarkerID(id);
            User user = userService.getCurrentUser();
            user.getMarker().remove(marker);
            userService.saveUser(user);
            //вместо этого - orphanRemoval=true
            //markerRepository.delete(marker);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List getAllMarkers() {
        try {
            return markerRepository.getAllMarkers();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List getMarkersByUser() {
        try {
            User user = userService.getCurrentUser();
            return markerRepository.getMarkersByUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

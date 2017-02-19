package site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.model.Marker;
import site.service.MarkerService;

import java.util.List;

@Controller
@RequestMapping("/marker")
public class MarkerController {
    private final MarkerService markerService;

    @Autowired
    public MarkerController(MarkerService markerService) {
        this.markerService = markerService;
    }

    //Content-Type: application/json
    /**
     * get all markers http://localhost:8088/marker
     */
    @RequestMapping(method = RequestMethod.GET, produces = {"application/json"})
    //@ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List getMarkerList() {
        return markerService.getAllMarkers();
    }

    /**
     * Add new marker http://localhost:8088/marker
     *  { "firstName": "New_name",
     *    "lastName": "New_country" }
     */
    @RequestMapping(method = RequestMethod.POST, produces = {"application/json"})
    //@ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void addMarker(@RequestBody Marker marker) {
        markerService.saveMarker(marker);
    }

    /**
     * Change data in user http://localhost:8088/user
     *  { "_id": 13,
     *    "firstName": "New_name",
     *    "lastName": "New_country" }
     */
    @RequestMapping(/*value = "{id}", */method = RequestMethod.PUT, produces = {"application/json"})
    @ResponseBody
    public void updateMarker(@RequestBody Marker marker) {
        markerService.updateMarker(marker);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE, produces = {"application/json"})
    @ResponseBody
    public void deleteMarker(@PathVariable("id") long id) {
        markerService.deleteMarker(id);
    }
}

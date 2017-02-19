package site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.model.User;
import site.service.UserService;

import java.util.List;

@Controller
//@ComponentScan("site")
@RequestMapping("/user")
public class UserController {

    //Content-Type: application/json
    private final UserService userService;
    //private final MarkerService markerService;

    @Autowired
    public UserController(UserService userService/*, MarkerService markerService*/) {
        this.userService = userService;
      //  this.markerService = markerService;
    }

    /**
     * get all users http://localhost:8088/user
     */
    @RequestMapping(method = RequestMethod.GET, produces = {"application/json"})
    //@ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List getUserList() {
        return userService.getAllUsers();
    }

    /**
     * Add new user http://localhost:8088/user
     *  { "firstName": "New_name",
     *    "lastName": "New_country" }
     */
    @RequestMapping(method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public void addUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    /**
     * Change data in user http://localhost:8088/user
     *  { "_id": 13,
     *    "firstName": "New_name",
     *    "lastName": "New_country" }
     *    value id not used, it's костыль
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = {"application/json"})
    @ResponseBody
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    /**
     * Delete http://localhost:8088/user/7 (id = 7)
     */
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE, produces = {"application/json"})
    @ResponseBody
    public void deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
    }

    /**
     * http://localhost:8088/user/greet?name=NewName
     */
    @RequestMapping("/greet")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("message", name);
        return "user.jsp";
    }
}

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

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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
     *  { "name": "New_name",
     *    "country": "New_country" }
     */
    @RequestMapping(method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public void addUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    /**
     * Delete http://localhost:8088/user/7 (id = 7)
     */
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE, produces = {"application/json"})
    @ResponseBody
    public void deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
    }

    @RequestMapping("/greet")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("message", name);
        return "user";
    }
}

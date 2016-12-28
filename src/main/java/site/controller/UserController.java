package site.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@ComponentScan("site")
public class UserController {
    @RequestMapping("/user")
    @ResponseBody
    public String user(Model model) {
        model.addAttribute("name", "Aslan");
        return "greeting";
    }
    @RequestMapping("/greet")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("message", name);
        return "user";
    }
}

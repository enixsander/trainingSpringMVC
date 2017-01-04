package site.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import site.model.User;

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

    @Autowired
    private SessionFactory sessionFactory;

    //This method will provide the medium to add a new user
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public String newUser(ModelMap model){
        User user = new User("Aslan", 0,"Narnia");
        sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        if (session.isOpen())
            session.close();

        return "add element";
    }
}

package com.springpack.SpringBootWebMVC;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MyController {

    @RequestMapping("/")
    public String home(){
        System.out.println("In home method...");
//        return "homePage.jsp";
        return "homePage";
    }

//    @RequestMapping("add")
//    public String add(HttpServletRequest req, HttpSession session)
//    {
//        int num1 = Integer.parseInt(req.getParameter("num1"));
//        int num2 = Integer.parseInt(req.getParameter("num2"));
//        int result = num1+num2;
//        System.out.println(result);
//        session.setAttribute("result",result);
//        return "resultPage";
//    }

    //Alternately -
//    @RequestMapping("add")
//    public String add(int num1,int num2, HttpSession session)
//    {
//        int result = num1+num2;
//        session.setAttribute("result",result);
//        return "resultPage";
//    }

    //Alternately -
//    @RequestMapping("add")
//    public String add(@RequestParam("num1") int n1, int num2, HttpSession session)
//    {
//        int result = n1+num2;
//        session.setAttribute("result",result);
//        return "resultPage";
//    }

    // Alternate - Used Model from MVC. Model is just an interface to transfer data between view (JSP/Html) and Java
//    @RequestMapping("add")
//    public String add(@RequestParam("num1") int n1, int num2, Model model)
//    {
//        int result = n1+num2;
//        model.addAttribute("result",result);
//        return "resultPage";
//    }

    // Alternate - Used ModelAndView from MVC. Just a class that creates object of both model and view together
    @RequestMapping("add")
    public ModelAndView add(@RequestParam("num1") int n1, int num2, ModelAndView mv)
    {
        int result = n1+num2;
        mv.addObject("result",result);
        mv.setViewName("resultPage");
        return mv;
    }

//    @RequestMapping("addAlien")
//    public ModelAndView addAlien(@ModelAttribute("alien") Alien myAlien, ModelAndView mv)
//    {
//        mv.addObject("alien",myAlien);
//        mv.setViewName("resultPage");
//        return mv;
//    }

    // Alternately
    @RequestMapping("addAlien")
    public String addAlien(Alien myAlien)
    {
        return "resultPage";
    }

    @ModelAttribute("course")
    public String getCourse()
    {
        return "Java";
    }
}

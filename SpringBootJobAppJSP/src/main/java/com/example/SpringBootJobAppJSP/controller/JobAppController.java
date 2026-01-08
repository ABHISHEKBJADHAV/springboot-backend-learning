package com.example.SpringBootJobAppJSP.controller;

import com.example.SpringBootJobAppJSP.Model.JobPost;
import com.example.SpringBootJobAppJSP.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class JobAppController {

    @Autowired
    JobService service;

    @GetMapping({"/","home"})
    public String homePage()
    {
        return "home";
    }

    @GetMapping("addjob")
    public String addJobPage()
    {
        return "addjob";
    }

    @PostMapping("handleForm")
    public String successPage(JobPost post)
    {
        service.addJob(post);
        return "success";
    }

    @GetMapping("viewalljobs")
    public String viewAllJobsPage(Model model)
    {
        List<JobPost> jobs = service.getAllJobs();
        model.addAttribute("jobPosts",jobs);
        return "viewalljobs";
    }

}

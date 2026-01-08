package com.example.JobAppBackEnd.controller;

import com.example.JobAppBackEnd.Model.JobPost;
import com.example.JobAppBackEnd.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
public class JobAppController {

    @Autowired
    JobService service;

    @GetMapping("jobPosts")
    public List<JobPost> getJobPosts()
    {
        return service.getJobPosts();
    }


    @GetMapping("load")
    public void load()
    {
        service.load();
    }

    /* If you want to produce data (send data) only in xml format, then follow below. By default, spring sends in JSON
    */
//    @GetMapping(path = "jobPosts", produces = {"application/xml"})
//    public List<JobPost> getJobPosts()
//    {
//        return service.getJobPosts();
//    }

    @GetMapping("jobPost/{postId}")
    public JobPost getJobPost(@PathVariable("postId") int postId1)
    {
        return service.getJobPost(postId1);
    }

    @PostMapping("jobPost")
    public JobPost addJobPost(@RequestBody JobPost jobPost)
    {
        service.addJobPost(jobPost);
        return service.getJobPost(jobPost.getPostId());
    }

    @GetMapping("jobPost/search/{keyword}")
    public List<JobPost> searchByKeyword(@PathVariable("keyword") String keyword)
    {
        return service.searchByKeyword(keyword);
    }

    /*
    By default, Spring accepts data in JSON format and with help of Jackson lib (Inbuilt in Spring) it converts into
    java obj and vce versa. If you want explicitly data should be in other format like xml then you have consume it in
    xml and not that when you say it explicitly then it only accepts xml.
    */
//    @PostMapping(path = "jobPost", consumes = {"application/xml"})
//    public JobPost addJobPost(@RequestBody JobPost jobPost)
//    {
//        service.addJobPost(jobPost);
//        return service.getJobPost(jobPost.getPostId());
//    }

    @PutMapping("jobPost")
    public JobPost updateJobPost(@RequestBody JobPost jobPost)
    {
        service.updateJobPost(jobPost);
        return service.getJobPost(jobPost.getPostId());
    }

    @DeleteMapping("jobPost/{postId}")
    public boolean deletePost(@PathVariable int postId)
    {
        service.deletePost(postId);
        return true;
    }

}

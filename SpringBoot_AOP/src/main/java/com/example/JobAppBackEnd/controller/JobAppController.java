package com.example.JobAppBackEnd.controller;

import com.example.JobAppBackEnd.Model.JobPost;
import com.example.JobAppBackEnd.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
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

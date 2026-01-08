package com.example.Aws_JobApp.controller;

import com.example.Aws_JobApp.Model.JobPost;
import com.example.Aws_JobApp.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class JobAppController {

    @Autowired
    JobService service;

    @GetMapping("jobPosts")
    public List<JobPost> getJobPosts()
    {
        return service.getJobPosts();
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

    @GetMapping({"/","/info"})
    public String getPcInfo()
    {
        String osName = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        String osArch = System.getProperty("os.arch");
        String userName = System.getProperty("user.name");
        return "OsName=" + osName + ", OsVersion=" +
                osVersion + ", OsArch=" + osArch + ", UserName=" + userName;
    }

}

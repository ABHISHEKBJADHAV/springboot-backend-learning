package com.example.SpringBootJobAppJSP.Service;

import com.example.SpringBootJobAppJSP.DAO.JobRepo;
import com.example.SpringBootJobAppJSP.Model.JobPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    JobRepo repo;

    public void addJob(JobPost post) {
        repo.addJob(post);
    }

    public List<JobPost> getAllJobs() {
        return repo.getAllJobs();
    }
}

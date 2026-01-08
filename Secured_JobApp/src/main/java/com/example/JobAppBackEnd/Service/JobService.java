package com.example.JobAppBackEnd.Service;

import com.example.JobAppBackEnd.DAO.JobRepo;
import com.example.JobAppBackEnd.Model.JobPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JobService {

    @Autowired
    JobRepo repo;

    public List<JobPost> getJobPosts() {
        return repo.findAll();
    }

    public JobPost getJobPost(int postId) {
        return repo.findById(postId).orElse(new JobPost());
    }

    public void addJobPost(JobPost jobPost) {
        repo.save(jobPost);
    }

    public void updateJobPost(JobPost jobPost) {
        repo.save(jobPost);
    }

    public void deletePost(int postId) {
        repo.deleteById(postId);
    }

    public List<JobPost> searchByKeyword(String keyword) {
        return repo.searchByKeyword(keyword);
    }
}

package com.example.JobAppBackEnd.Service;

import com.example.JobAppBackEnd.DAO.JobRepo;
import com.example.JobAppBackEnd.Model.JobPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class JobService {

    List<JobPost> jobs = new ArrayList<>(Arrays.asList(
            new JobPost(1, "Java Developer", "Must have good experience in core Java and advanced Java", 2,
                    List.of("Core Java", "J2EE", "Spring Boot", "Hibernate")),
            new JobPost(2, "Frontend Developer", "Experience in building responsive web applications using React", 3,
                    List.of("HTML", "CSS", "JavaScript", "React")),
            new JobPost(3, "Data Scientist", "Strong background in machine learning and data analysis", 4,
                    List.of("Python", "Machine Learning", "Data Analysis")),
            new JobPost(4, "Network Engineer", "Design and implement computer networks for efficient data communication", 5,
                    List.of("Networking", "Cisco", "Routing", "Switching")),
            new JobPost(5, "Mobile App Developer", "Experience in mobile app development for iOS and Android", 3,
                    List.of("iOS Development", "Android Development", "Mobile App"))
    ));

    @Autowired
    JobRepo repo;

    public List<JobPost> getJobPosts() {
        return repo.findAll();
    }

    public JobPost getJobPost(int postId) {
//        int res=10/0;
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

    public void load() {
        repo.saveAll(jobs);
    }

    public List<JobPost> searchByKeyword(String keyword) {
        return repo.searchByKeyword(keyword);
    }
}

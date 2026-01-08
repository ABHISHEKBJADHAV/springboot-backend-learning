package com.example.JobAppBackEnd.DAO;

import com.example.JobAppBackEnd.Model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<JobPost,Integer> {

    List<JobPost> findByPostProfileContainingIgnoreCaseOrPostDescContainingIgnoreCase(String profile, String desc);

    List<JobPost> findByPostProfileContainingOrPostDescContaining(String profile, String desc);

    // For above two methods you need to pass 2 arguments for post profile and post desc
    @Query("SELECT j FROM JobPost j WHERE LOWER(j.postProfile) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(j.postDesc) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<JobPost> searchByKeyword(@Param("keyword") String keyword);
}



//@Repository
//public class JobRepo {
//
//    List<JobPost> jobs = new ArrayList<>(Arrays.asList(
//            new JobPost(1, "Java Developer", "Must have good experience in core Java and advanced Java", 2,
//                    List.of("Core Java", "J2EE", "Spring Boot", "Hibernate")),
//            new JobPost(2, "Frontend Developer", "Experience in building responsive web applications using React", 3,
//                    List.of("HTML", "CSS", "JavaScript", "React")),
//            new JobPost(3, "Data Scientist", "Strong background in machine learning and data analysis", 4,
//                    List.of("Python", "Machine Learning", "Data Analysis")),
//            new JobPost(4, "Network Engineer", "Design and implement computer networks for efficient data communication", 5,
//                    List.of("Networking", "Cisco", "Routing", "Switching")),
//            new JobPost(5, "Mobile App Developer", "Experience in mobile app development for iOS and Android", 3,
//                    List.of("iOS Development", "Android Development", "Mobile App"))
//    ));
//
//    public List<JobPost> getJobPosts() {
//        return jobs;
//    }
//
//    public JobPost getJobPost(int postId) {
//        for(JobPost post:jobs)
//        {
//            if(post.getPostId()==postId) return post;
//        }
//        return null;
//    }
//
//    public void addJobPost(JobPost jobPost) {
//        jobs.add(jobPost);
//    }
//
//    public void updateJobPost(JobPost jobPost) {
//        for(JobPost post:jobs)
//        {
//            if(post.getPostId()==jobPost.getPostId()){
//                post.setPostDesc(jobPost.getPostDesc());
//                post.setReqExperience(jobPost.getReqExperience());
//                post.setPostProfile(jobPost.getPostProfile());
//                post.setPostTechStack(jobPost.getPostTechStack());
//                break;
//            }
//        }
//    }
//
//    public void deletePost(int postId) {
//        Iterator<JobPost> it = jobs.iterator();
//        while(it.hasNext())
//        {
//            JobPost post = it.next();
//            if(post.getPostId()==postId)
//            {
//                it.remove();
//                break;
//            }
//        }
//    }
//}

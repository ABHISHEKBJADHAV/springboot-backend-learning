package com.example.JobAppBackEnd.DAO;

import com.example.JobAppBackEnd.Model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<JobPost,Integer> {
    @Query("SELECT j FROM JobPost j WHERE LOWER(j.postProfile) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(j.postDesc) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<JobPost> searchByKeyword(@Param("keyword") String keyword);
}
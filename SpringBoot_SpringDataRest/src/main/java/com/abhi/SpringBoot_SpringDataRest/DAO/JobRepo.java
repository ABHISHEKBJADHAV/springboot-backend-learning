package com.abhi.SpringBoot_SpringDataRest.DAO;

import com.abhi.SpringBoot_SpringDataRest.Model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JobRepo extends JpaRepository<JobPost,Integer> {
}

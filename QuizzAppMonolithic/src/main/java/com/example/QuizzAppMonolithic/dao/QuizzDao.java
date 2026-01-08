package com.example.QuizzAppMonolithic.dao;

import com.example.QuizzAppMonolithic.model.Quizz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizzDao extends JpaRepository<Quizz,Integer> {
}

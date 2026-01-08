package com.quizzApp.quizz_service.dao;

import com.quizzApp.quizz_service.model.Quizz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizzDao extends JpaRepository<Quizz,Integer> {
}

package com.example.web_analyzer.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.web_analyzer.model.LinkAnalysis;

public interface LinkAnalysisRepository extends JpaRepository<LinkAnalysis, Long> {
}

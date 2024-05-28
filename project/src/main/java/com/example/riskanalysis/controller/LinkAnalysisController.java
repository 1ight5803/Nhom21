package com.example.riskanalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.riskanalysis.service.LinkAnalysisService;

@RestController
public class LinkAnalysisController {

    @Autowired
    private LinkAnalysisService linkAnalysisService;

    @GetMapping("/analyze")
    public String analyzeLink(@RequestParam String url) {
        return linkAnalysisService.analyzeRisk(url);
    }
}
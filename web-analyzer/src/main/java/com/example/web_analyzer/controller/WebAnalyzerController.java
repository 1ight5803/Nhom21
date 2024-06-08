package com.example.web_analyzer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WebAnalyzerController {

    @PostMapping("/analyze")
    public String analyzeLink(@RequestParam String url) {
        // Logic phân tích rủi ro sẽ được viết ở đây
        return "Analysis result for: " + url;
    }
}
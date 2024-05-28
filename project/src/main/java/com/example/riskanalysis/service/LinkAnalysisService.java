package com.example.riskanalysis.service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LinkAnalysisService {

    private static final Logger logger = LoggerFactory.getLogger(LinkAnalysisService.class);

    @Value("${virustotal.api.key}")
    private String apiKey;

    public String analyzeRisk(String url) {
        String riskLevel = callRiskAnalysisAPI(url);
        return "Mức độ rủi ro của " + url + " là: " + riskLevel;
    }

    private String callRiskAnalysisAPI(String url) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString());
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.virustotal.com/api/v3/urls"))
                    .header("x-apikey", apiKey)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString("url=" + encodedUrl))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject jsonObject = new JSONObject(response.body());
            JSONObject data = jsonObject.getJSONObject("data");
            JSONObject attributes = data.getJSONObject("attributes");
            JSONObject lastAnalysisStats = attributes.getJSONObject("last_analysis_stats");

            int malicious = lastAnalysisStats.getInt("malicious");
            int suspicious = lastAnalysisStats.getInt("suspicious");
            int harmless = lastAnalysisStats.getInt("harmless");

            return String.format("Malicious: %d, Suspicious: %d, Harmless: %d", malicious, suspicious, harmless);
        } catch (IOException | InterruptedException e) {
            logger.error("Error occurred while analyzing URL risk: {}", e.getMessage(), e);
            return "Không xác định";
        }
    }
}
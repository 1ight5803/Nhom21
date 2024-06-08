package com.example.web_analyzer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;

@Service
public class WebAnalyzerService {
    private static final Logger logger = LoggerFactory.getLogger(WebAnalyzerService.class);

    private static final String ZAP_ADDRESS = "localhost";
    private static final int ZAP_PORT = 8080;
    private static final String ZAP_API_KEY = "your_api_key";

    private final ClientApi api;

    public WebAnalyzerService() {
        this.api = new ClientApi(ZAP_ADDRESS, ZAP_PORT, ZAP_API_KEY);
    }

    public String analyze(String url) {
        try {
            api.spider.scan(url, null, null, null, null);
            Thread.sleep(10000); // Chờ cho đến khi spider quét xong

            String spiderResults = new String(api.core.htmlreport());
            return spiderResults;
        } catch (ClientApiException | InterruptedException e) {
            logger.error("An error occurred while analyzing the URL: " + url, e);
            return "Error";
        }
    }
}
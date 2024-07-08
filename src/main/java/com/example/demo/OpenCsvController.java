package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@RestController
public class OpenCsvController {


    private final OpenCsvService openCsvService;
    @Autowired
    public OpenCsvController(OpenCsvService openCsvService) {
        this.openCsvService = openCsvService;
    }
    @GetMapping("/csv-data")
    public List<String[]> getCsvData() {
        Path filePath = Path.of("src/main/resources/sample.csv");
        try {
            return openCsvService.readAllLines(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read CSV file", e);
        }
    }

    @GetMapping("/total-sales-per-date")
    public Map<String, Integer> getTotalSalesPerDate() {
        Path filePath = Path.of("src/main/resources/sample.csv");
        try {
            List<String[]> csvData = openCsvService.readAllLines(filePath);
            return openCsvService.calculateTotalSalesPerDate(csvData);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read CSV file", e);
        }
    }
}

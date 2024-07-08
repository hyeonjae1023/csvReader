package com.example.demo;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OpenCsvService {
    public List<String[]> readAllLines(Path filePath) throws IOException {
        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
            } catch (CsvException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Map<String, Integer> calculateTotalSalesPerDate(List<String[]> csvData) {
        Map<String, Integer> salesPerDate = new HashMap<>();
        for(String[] row : csvData) {
            if(row[0].equals("date")) continue;
            String date = row[0];
            int sales = Integer.parseInt(row[1]);

            salesPerDate.put(date, salesPerDate.getOrDefault(date, 0) + sales);
        }
        return salesPerDate;
    }
}

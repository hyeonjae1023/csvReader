package com.example.demo;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OpenCsv {
    public static void main(String[] args) throws CsvValidationException, IOException {
        CSVReader csvReader = new CSVReader(new FileReader("src/main/resources/sample.csv"));
        String[] line;
        while ((line = csvReader.readNext()) != null) {
            System.out.println(String.join(",", line));
        }
    }
}

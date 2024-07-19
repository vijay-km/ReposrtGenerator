package com.example.accounts.FileProcessorFactory;

import com.example.accounts.entity.InputRecords;
import com.example.accounts.service.FileProcessor;
import com.example.accounts.service.impl.CsvFileParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Factory {

//    private String fileType;
//
//    @Autowired
//    public Factory(String fileType) {
//        this.fileType = fileType;
//    }

    public <T> FileProcessor<T> getFileProcessor(String fileType, Class<T> type) {
        if (fileType.equalsIgnoreCase("csv")) {
            return new CsvFileParser<>(type);
        }
        // Handle other file types or throw an exception
        throw new IllegalArgumentException("Unsupported file type: " + fileType);

    }
}
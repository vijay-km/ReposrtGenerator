//package com.example.accounts.config;
//
//import com.example.accounts.service.FileProcessor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//import java.util.Map;
//import com.example.accounts.service.impl.CsvFileParser;
//@Configuration
//public class FileProcessorConfig {
//
//    @Bean
//    public Map<String, FileProcessor<?>> fileProcessors(CsvFileParser csvFileProcessor) {
//        Map<String, FileProcessor<?>> map = new HashMap<>();
//        map.put("csv", csvFileProcessor);
//        //map.put("json", jsonFileProcessor);
//        return map;
//    }
//}
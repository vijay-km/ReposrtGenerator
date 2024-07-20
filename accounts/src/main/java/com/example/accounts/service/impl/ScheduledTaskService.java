package com.example.accounts.service.impl;

import com.example.accounts.FileProcessorFactory.Factory;
import com.example.accounts.entity.InputRecords;
import com.example.accounts.entity.OutputRecord;
import com.example.accounts.entity.ReferenceRecord;
import com.example.accounts.repository.InputRecordsRepository;
import com.example.accounts.repository.ReferenceRecordRepository;
import com.example.accounts.service.FileProcessor;
import com.example.accounts.service.IGetFileServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduledTaskService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTaskService.class);

    @Value("${file.input-path}")
    private String inputPath;

    @Value("${file.reference-path}")
    private String referencePath;

    @Autowired
    private Factory fileProcessorFactory;

    @Autowired
    private InputRecordsRepository inputRecordsRepository;

    @Autowired
    public IGetFileServices iGetFileServices;

    @Autowired
    private ReferenceRecordRepository referenceRecordRepository;

    public void processFiles() {
        logger.info("Scheduled task started.");

        File inputFile = new File(inputPath);
        File referenceFile = new File(referencePath);

        if (!inputFile.exists() && !referenceFile.exists()) {
            logger.error("Neither input file nor reference file exists.");
            return;
        }

        if (!inputFile.exists()) {
            logger.error("Input file does not exist: {}", inputPath);
            return;
        }

        if (!referenceFile.exists()) {
            logger.error("Reference file does not exist: {}", referencePath);
            return;
        }

        try {
            logger.info("Processing files: {} and {}", inputFile.getName(), referenceFile.getName());

            String inputFileType = iGetFileServices.getFileExtension(inputFile.getName());
            String referenceFileType = iGetFileServices.getFileExtension(referenceFile.getName());

            FileProcessor<InputRecords> inputProcessor = (FileProcessor<InputRecords>) fileProcessorFactory.getFileProcessor(inputFileType, InputRecords.class);
            FileProcessor<ReferenceRecord> referenceProcessor = (FileProcessor<ReferenceRecord>) fileProcessorFactory.getFileProcessor(referenceFileType, ReferenceRecord.class);

            List<InputRecords> inputRecords = inputProcessor.processFile(inputFile);
            List<ReferenceRecord> referenceRecords = referenceProcessor.processFile(referenceFile);

            inputRecordsRepository.saveAll(inputRecords);
            referenceRecordRepository.saveAll(referenceRecords);

            iGetFileServices.generateOutputFile(inputRecords, referenceRecords);

            logger.info("Files processed successfully.");
        } catch (IOException e) {
            logger.error("Error processing files: {}", e.getMessage(), e);
        }
    }
}

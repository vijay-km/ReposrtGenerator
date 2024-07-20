package com.example.accounts.service.impl;

import com.example.accounts.entity.InputRecords;
import com.example.accounts.entity.ReferenceRecord;
import com.example.accounts.repository.InputRecordsRepository;
import com.example.accounts.repository.ReferenceRecordRepository;
import com.example.accounts.service.FileProcessor;
import com.example.accounts.service.IGetFileServices;
import com.example.accounts.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import  com.example.accounts.FileProcessorFactory.Factory;
import com.example.accounts.util.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ReportServiceImpl implements IReportService {

    @Autowired
    private InputRecordsRepository inputRecordRepository;

    @Autowired
    public IGetFileServices iGetFileServices;

    @Autowired
    private ReferenceRecordRepository referenceRecordRepository;

    @Autowired
    private Factory fileProcessorFactory;

    /**
     * @param inputFile
     * @param referenceFile
     */
    @Override
    public void uploadFiles(MultipartFile inputFile, MultipartFile referenceFile) {
        try {

            String inputFileType = iGetFileServices.getFileExtension(inputFile.getOriginalFilename());
            String referenceFileType =  iGetFileServices.getFileExtension(referenceFile.getOriginalFilename());

            FileProcessor<InputRecords> inputProcessor = (FileProcessor<InputRecords>) fileProcessorFactory.getFileProcessor(inputFileType,InputRecords.class);
            FileProcessor<ReferenceRecord> referenceProcessor = (FileProcessor<ReferenceRecord>) fileProcessorFactory.getFileProcessor(referenceFileType,ReferenceRecord.class);

            List<InputRecords> inputRecords = inputProcessor.processFile(inputFile);
            List<ReferenceRecord> referenceRecords = referenceProcessor.processFile(referenceFile);

            inputRecordRepository.saveAll(inputRecords);
            referenceRecordRepository.saveAll(referenceRecords);
        } catch (IOException e) {

            e.printStackTrace();

            throw new RuntimeException("Failed to process files", e);
        }
    }


}

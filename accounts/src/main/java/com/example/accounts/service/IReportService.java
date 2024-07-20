package com.example.accounts.service;

import com.example.accounts.dto.ReportDto;
import com.example.accounts.entity.OutputRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface IReportService {
   // void uploadFiles(File inputFile, File referenceFile) ;
    void uploadFiles(MultipartFile inputFile, MultipartFile referenceFile);
    List<OutputRecord> generateReport() throws IOException;

}

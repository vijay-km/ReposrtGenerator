package com.example.accounts.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface IReportService {
   // void uploadFiles(File inputFile, File referenceFile) ;
    void uploadFiles(MultipartFile inputFile, MultipartFile referenceFile);

}

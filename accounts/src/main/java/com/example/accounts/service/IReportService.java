package com.example.accounts.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IReportService {
    void uploadFiles(MultipartFile inputFile, MultipartFile referenceFile) ;
}

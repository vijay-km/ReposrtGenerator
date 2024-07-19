package com.example.accounts.service;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileProcessor<T> {
    List<T> processFile(MultipartFile file) throws IOException;
}
package com.example.accounts.service.impl;


import com.example.accounts.service.FileProcessor;
import com.example.accounts.util.CsvUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CsvFileParser<T> implements FileProcessor<T> {
    private final Class<T> type;

    public CsvFileParser(Class<T> type) {
        this.type = type;
    }

    /**
     * @param file
     * @return
     * @throws IOException
     */
    @Override
    public List<T> processFile(File file) throws IOException {
        return CsvUtils.parseCsvFile(file, type);
    }

    @Override
    public List<T> processFile(MultipartFile file) throws IOException {
        return CsvUtils.parseCsvFile(file, type);
    }
}

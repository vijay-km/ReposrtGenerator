package com.example.accounts.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import com.opencsv.bean.CsvToBeanBuilder;

public class CsvUtils {

    public static <T> List<T> parseCsvFile(File file, Class<T> type) throws IOException {
        try (Reader reader = new FileReader(file)) {
            return new CsvToBeanBuilder<T>(reader)
                    .withType(type)
                    .build()
                    .parse();
        }
    }

    public static <T> List<T> parseCsvFile(MultipartFile file, Class<T> type) throws IOException {
        try (Reader reader = new InputStreamReader(file.getInputStream())) {
            return new CsvToBeanBuilder<T>(reader)
                    .withType(type)
                    .build()
                    .parse();
        }
    }


}

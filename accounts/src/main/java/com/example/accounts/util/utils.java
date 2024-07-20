package com.example.accounts.util;

import com.example.accounts.entity.InputRecords;
import com.example.accounts.entity.OutputRecord;
import com.example.accounts.entity.ReferenceRecord;
import com.example.accounts.service.IGetFileServices;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

@Component
public class utils implements IGetFileServices {

    private static final Logger logger = LoggerFactory.getLogger(utils.class);

    @Value("${file.output-path}")
    private String outputPath;

    @Override
    public String getFileExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("Invalid file name");
        }
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }

    public List<OutputRecord> generateOutputFile(List<InputRecords> inputRecords, List<ReferenceRecord> referenceRecords) throws IOException {
        List<OutputRecord> outputRecords = new ArrayList<>();

        for (InputRecords input : inputRecords) {
            ReferenceRecord reference = referenceRecords.stream()
                    .filter(ref -> ref.getRefkey1().equals(input.getRefkey1()) && ref.getRefkey2().equals(input.getRefkey2()))
                    .findFirst()
                    .orElse(null);

            if (reference != null) {
                OutputRecord output = new OutputRecord();
                output.setOutfield1(input.getField1() + input.getField2());
                output.setOutfield2(reference.getRefdata1());
                output.setOutfield3(reference.getRefdata2() + reference.getRefdata3());
                output.setOutfield4(new BigDecimal(String.valueOf(input.getField3())).multiply(input.getField5().max(reference.getRefdata4())));
                output.setOutfield5(input.getField5().max(reference.getRefdata4()));

                outputRecords.add(output);
            }
        }

        // Write records to file (optional)
        File file = new File(outputPath);
        boolean fileExists = file.exists();

        // Ensure output directory exists
        file.getParentFile().mkdirs();

        try (Writer writer = new FileWriter(outputPath, true)) {
            StatefulBeanToCsv<OutputRecord> beanToCsv = new StatefulBeanToCsvBuilder<OutputRecord>(writer)
                    .withApplyQuotesToAll(false)
                    .build();

            if (!fileExists || file.length() == 0) {
                writer.write("ID,OUTFIELD1,OUTFIELD2,OUTFIELD3,OUTFIELD4,OUTFIELD5\n");
            }

            // Write records
            beanToCsv.write(outputRecords);
        } catch (Exception e) {
            logger.error("Error writing CSV file: {}", e.getMessage(), e);
            throw new IOException("Error writing CSV file: " + e.getMessage(), e);
        }

        return outputRecords;
    }}

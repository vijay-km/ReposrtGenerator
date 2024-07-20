package com.example.accounts.dto;

import com.example.accounts.entity.OutputRecord;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReportDto {
    private String statusCode;
    private String statusMessage;
    private List<OutputRecord> outputRecords;
}

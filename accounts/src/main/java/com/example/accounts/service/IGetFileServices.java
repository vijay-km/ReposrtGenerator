package com.example.accounts.service;

import com.example.accounts.entity.InputRecords;
import com.example.accounts.entity.OutputRecord;
import com.example.accounts.entity.ReferenceRecord;

import java.io.IOException;
import java.util.List;

public interface IGetFileServices {
    public String getFileExtension(String fileName);

    public List<OutputRecord> generateOutputFile(List<InputRecords> inputRecords, List<ReferenceRecord> referenceRecords) throws IOException;

    }

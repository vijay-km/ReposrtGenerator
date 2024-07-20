package com.example.accounts.contoller;

import com.example.accounts.constants.FileUploadConstant;
import com.example.accounts.dto.ErrorResponseDto;
import com.example.accounts.dto.ReportDto;
import com.example.accounts.dto.ResponseDto;
import java.util.ArrayList;
import com.example.accounts.entity.OutputRecord;
import com.example.accounts.service.IReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Tag(
        name = "CRUD REST APIs for RportGenerator",
        description = "Service to generate Report"
)
@RestController
@RequestMapping(path = "/api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {

    private IReportService iReportService;

    @Operation(
            summary = "Upload file REST API",
            description = "REST API to upload file"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status uploaded"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )

    @PostMapping(value = "/uploadInputFiles", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto> UploadReport(
            @RequestParam("input") MultipartFile inputFile,
            @RequestParam("reference") MultipartFile referenceFile) {

        try {
            iReportService.uploadFiles(inputFile, referenceFile);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ResponseDto(FileUploadConstant.STATUS_201, FileUploadConstant.MESSAGE_201));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(FileUploadConstant.STATUS_500, "Failed to generate report"));
        }
    }

    @Operation(
            summary = "Generate report via REST API",
            description = "Generate report via REST API"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status generated"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping(value = "/generateReport", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReportDto> generateReport() {
        try {
            List<OutputRecord> outputRecords = iReportService.generateReport();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ReportDto(FileUploadConstant.STATUS_200, "Report generated successfully", outputRecords));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ReportDto("400", "Failed to generate report: " + e.getMessage(), new ArrayList<>()));
        }
    }

}

package com.example.controller;


import com.company.dto.report.ReportCreateDTO;
import com.company.dto.report.ReportResponseDTO;
import com.company.service.ReportService;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/report")
public class AnswerController {
    private final ReportService reportService;

    public AnswerController(ReportService reportService) {
        this.reportService = reportService;
    }


    @PostMapping("/user/create")
    public ResponseEntity<String> create(@RequestBody @Valid ReportCreateDTO dto) {

        String response = reportService.create(dto);
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/adm/reportList")
    public ResponseEntity<?> reportList(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "5") int size) {

        PageImpl<ReportResponseDTO> list = reportService.pagination(page, size);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/adm/deleteById/{reportId}")
    public ResponseEntity<?> deleteById(@PathVariable("reportId") Integer reportId) {

        String response = reportService.deleteById(reportId);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/adm/list/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable("userId") Integer userId) {

      List<ReportResponseDTO> list = reportService.getByUserId(userId);
        return ResponseEntity.ok().body(list);
    }

}
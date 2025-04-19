package com.example.company_service.controller;

import com.example.company_service.dto.CompanyRequestDto;
import com.example.company_service.dto.CompanyResponseDto;
import com.example.company_service.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyResponseDto> save(@RequestBody CompanyRequestDto dto) {
        return ResponseEntity.ok(companyService.save(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponseDto>> getAll() {
        return ResponseEntity.ok(companyService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> update(@PathVariable Long id, @RequestBody CompanyRequestDto dto) {
        return ResponseEntity.ok(companyService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        companyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

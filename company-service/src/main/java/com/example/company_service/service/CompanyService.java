package com.example.company_service.service;

import com.example.company_service.dto.CompanyRequestDto;
import com.example.company_service.dto.CompanyResponseDto;
import com.example.company_service.model.Company;
import com.example.company_service.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public CompanyResponseDto save(CompanyRequestDto dto) {
        Company company = mapToEntity(dto);
        return mapToDto(repository.save(company));
    }

    public CompanyResponseDto findById(Long id) {
        return repository.findById(id)
                .map(this::mapToDto)
                .orElse(null);
    }

    public List<CompanyResponseDto> findAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public CompanyResponseDto update(Long id, CompanyRequestDto dto) {
        Company company = repository.findById(id).orElseThrow();
        company.setName(dto.getName());
        company.setBudget(dto.getBudget());
        company.setEmployeeIds(dto.getEmployeeIds());
        return mapToDto(repository.save(company));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private Company mapToEntity(CompanyRequestDto dto) {
        return Company.builder()
                .name(dto.getName())
                .budget(dto.getBudget())
                .employeeIds(dto.getEmployeeIds())
                .build();
    }

    private CompanyResponseDto mapToDto(Company company) {
        return CompanyResponseDto.builder()
                .id(company.getId())
                .name(company.getName())
                .budget(company.getBudget())
                .employeeIds(company.getEmployeeIds())
                .build();
    }
}

package com.sapient.sms.controller;

import com.sapient.sms.ExcludeFromJacocoGeneratedReport;
import com.sapient.sms.dto.CompanyDTO;
import com.sapient.sms.entity.Company;
import com.sapient.sms.service.ICompanyService;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    ICompanyService service;

    private ModelMapper modelMapper = new ModelMapper();

    public CompanyController() {
        customMapperConvertor();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/{id}")
    public Company getCompany(@PathVariable Integer id) {
        return service.get(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/searchByName/{name}")
    public List<Company> getByNameCompany(@PathVariable String name) {
        return service.getByName(name);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/")
    public List<Company> getAllCompany() {
        return service.getAll();
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/")
    public Company createCompany(@Valid @RequestBody CompanyDTO companyDTO) {
        return service.create(modelMapper.map(companyDTO, Company.class));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable int id, @RequestBody CompanyDTO companyDTO) {
        return service.update(id, modelMapper.map(companyDTO, Company.class));
    }

    @DeleteMapping("/{id}")
    public Company deleteCompany(@PathVariable int id) {
        return service.delete(id);
    }

    @ExcludeFromJacocoGeneratedReport
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error ->
                errors.put(((FieldError) error).getField(), error.getDefaultMessage()));
        return errors;
    }

    @ExcludeFromJacocoGeneratedReport
    public void customMapperConvertor() {
        Converter<String, String> myConverter = new AbstractConverter<>() {
            protected String convert(String source) {
                return source == null ? null : source.trim();
            }
        };
        modelMapper.addConverter(myConverter);
    }
}

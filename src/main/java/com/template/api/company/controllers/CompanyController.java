package com.template.api.company.controllers;

import com.template.api.company.apis.CompanyApi;
import com.template.api.company.entities.Cnpj;
import com.template.api.company.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController implements CompanyApi {

    @Autowired
    private CompanyService companyService;

    @Override
    public ResponseEntity<?> getAllCompanies() {
        return null;
    }

    @Override
    public ResponseEntity<?> createCompany(String cnpj) {
        var result = companyService.create(new Cnpj(cnpj));

        //todo: create handle to mange success and error response
        if (result.hasErros())
            return ResponseEntity.badRequest().body(result.getErros());

        return ResponseEntity.ok(result.getValue());
    }

    @Override
    public ResponseEntity<?> deleteCompany(String id) {
        return null;
    }
}

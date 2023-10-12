package com.template.api.company.apis;

import com.template.api.shared.constants.ApiMediaTypes;
import com.template.api.shared.exceptions.global.ApiError;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Api(tags = "Companies API")
public interface CompanyApi {

    @ApiOperation("List all companies")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 500, message = "An unxpected error ocurred", response = ApiError.class)
    })
    @GetMapping(value = "/v1/companies", produces = ApiMediaTypes.ApplicationJson)
    ResponseEntity<?> getAllCompanies();

    @ApiOperation("Create a new company")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 500, message = "An unxpected error ocurred", response = ApiError.class)
    })
    @PostMapping(value = "/v1/companies/cnpj/{cnpj}")
    ResponseEntity<?> createCompany(@PathVariable("cnpj") String cnpj);

    @ApiOperation("Delete an existing company")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 500, message = "An unxpected error ocurred", response = ApiError.class)
    })
    @DeleteMapping(value = "v1/companies/{id}")
    ResponseEntity<?> deleteCompany(@PathVariable("id") String id);
}

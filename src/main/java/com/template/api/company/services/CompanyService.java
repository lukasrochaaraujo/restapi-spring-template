package com.template.api.company.services;

import com.template.api.company.clients.speedio.GetRegistrationDetailsQueryParams;
import com.template.api.company.clients.speedio.SpeedioClient;
import com.template.api.company.entities.Cnpj;
import com.template.api.company.entities.Company;
import com.template.api.shared.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private SpeedioClient speedioClient;

    public Result<Company> create(Cnpj cnpj) {

        if (!cnpj.isValid())
            return new Result<>("Invalid CNPJ");

        var companyDetails = speedioClient.getRegistrationDetails(new GetRegistrationDetailsQueryParams(cnpj.value()));

        var company = new Company(
            companyDetails.nomeFantasia(),
            companyDetails.razaoSocial(),
            companyDetails.cnpj());

        company.setActive(companyDetails.status());

        if (!company.isActive())
            return new Result<>("Company is not active");

        return new Result<>(company);
    }
}

package com.template.api.company.entities;

import lombok.Getter;

@Getter
public class Company {
    private String name;
    private String legalName;
    private String cnpj;
    private boolean active;

    public Company(String name, String legalName, String cnpj) {
        this.name = name;
        this.legalName = legalName;
        this.cnpj = cnpj;
    }

    public void setActive(String statusDescription) {
        active = statusDescription == "ATIVA";
    }
}

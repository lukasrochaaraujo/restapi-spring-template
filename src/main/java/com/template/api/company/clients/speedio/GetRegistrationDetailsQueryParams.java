package com.template.api.company.clients.speedio;

import lombok.Getter;

@Getter
public class GetRegistrationDetailsQueryParams {
    private String cnpj;

    public GetRegistrationDetailsQueryParams(String cnpj) {
        this.cnpj = cnpj;
    }
}

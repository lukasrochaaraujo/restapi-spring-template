package com.template.api.company.clients.speedio;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GetRegistrationDetailsResponse(

    @JsonAlias("NOME FANTASIA")
    String nomeFantasia,

    @JsonAlias("RAZAO SOCIAL")
    String razaoSocial,

    @JsonAlias("CNPJ")
    String cnpj,

    @JsonAlias("STATUS")
    String status,

    @JsonAlias("CNAE PRINCIPAL DESCRICAO")
    String cnaePrincipalDescricao,

    @JsonAlias("CNAE PRINCIPAL CODIGO")
    String cnaePrincipalCodigo,

    @JsonAlias("CEP")
    String cep,

    @JsonAlias("DATA ABERTURA")
    String dataAbertura,

    @JsonAlias("DDD")
    String ddd,

    @JsonAlias("TELEFONE")
    String telefone,

    @JsonAlias("EMAIL")
    String email
) {
}

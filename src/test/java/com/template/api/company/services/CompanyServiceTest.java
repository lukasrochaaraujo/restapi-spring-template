package com.template.api.company.services;

import com.template.api.company.clients.speedio.GetRegistrationDetailsResponse;
import com.template.api.company.clients.speedio.SpeedioClient;
import com.template.api.company.entities.Cnpj;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.instancio.Select.field;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @Mock
    private SpeedioClient speedioClient;

    @InjectMocks
    private CompanyService companyService;

    @Test
    public void create_givenAnInvalidCnpj_mustReturnError() {
        //arrange
        var cnpj = new Cnpj("51.741.308/0001-71");

        //act
        var result = companyService.create(cnpj);

        //assert
        assertThat(result).isNotNull();
        assertThat(result.hasErros()).isTrue();
        verify(speedioClient, times(0)).getRegistrationDetails(any());
    }

    @Test
    public void create_givenAnCnpjOfActiveCompany_mustCreate() {
        //arrange
        var responseMock = Instancio.of(GetRegistrationDetailsResponse.class)
                .set(field(GetRegistrationDetailsResponse::status), "ATIVA")
                .create();
        when(speedioClient.getRegistrationDetails(any()))
            .thenReturn(responseMock);

        //act
        var cnpj = new Cnpj("51.749.308/0001-75");
        var result = companyService.create(cnpj);

        //assert
        assertThat(result).isNotNull();
        assertThat(result.hasErros()).isFalse();
        assertThat(result.getValue()).isNotNull();
        verify(speedioClient, times(1)).getRegistrationDetails(any());
    }

    @Test
    public void create_givenAnCnpjOfInactiveCompany_mustReturnError() {
        //arrange
        var responseMock = Instancio.of(GetRegistrationDetailsResponse.class)
                .set(field(GetRegistrationDetailsResponse::status), "INATIVA")
                .create();
        when(speedioClient.getRegistrationDetails(any()))
                .thenReturn(responseMock);

        //act
        var cnpj = new Cnpj("51.749.308/0001-75");
        var result = companyService.create(cnpj);

        //assert
        assertThat(result).isNotNull();
        assertThat(result.hasErros()).isTrue();
        verify(speedioClient, times(1)).getRegistrationDetails(any());
    }
}

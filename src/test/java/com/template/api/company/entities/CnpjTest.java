package com.template.api.company.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class CnpjTest {

    @Test
    public void isValid_givenAnValidCnpj_mustReturnTrue() {
        //arrange
        String cnpjString = "51.749.308/0001-75";

        //act
        var cnpj = new Cnpj(cnpjString);

        //assert
        assertTrue(cnpj.isValid());
    }

    @Test
    public void isValid_givenAnInvalidCnpjScenario1_mustReturnFalse() {
        //arrange
        String cnpjString = "51.749.308/8901-75";

        //act
        var cnpj = new Cnpj(cnpjString);

        //assert
        assertFalse(cnpj.isValid());
    }

    @Test
    public void isValid_givenAnInvalidCnpjScenario2_mustReturnFalse() {
        //arrange
        String cnpjString = "00.000.000/0000-00";

        //act
        var cnpj = new Cnpj(cnpjString);

        //assert
        assertFalse(cnpj.isValid());
    }

    @Test
    public void isValid_givenAnEmptyCnpj_mustReturnFalse() {
        //arrange
        String cnpjString = "";

        //act
        var cnpj = new Cnpj(cnpjString);

        //assert
        assertFalse(cnpj.isValid());
    }
}

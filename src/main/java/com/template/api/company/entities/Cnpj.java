package com.template.api.company.entities;

public class Cnpj {

    private String value;

    public Cnpj(String cnpj) {
        value = cnpj;
    }

    public String value() {
        return value;
    }

    public boolean isValid() {
        if (value == null)
            return false;

        value = removeSpecialChars(value);

        if (value.length() != 14 || value.matches("^([0-9])\\1*$"))
            return false;

        char dig13, dig14;
        int sm, i, r, num, wigth;

        try {
            sm = 0;
            wigth = 2;
            for (i = 11; i >= 0; i--) {
                num = (int) (value.charAt(i) - 48);
                sm = sm + (num * wigth);
                wigth = wigth + 1;

                if (wigth == 10)
                    wigth = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig13 = '0';
            else
                dig13 = (char) ((11 - r) + 48);

            sm = 0;
            wigth = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (value.charAt(i) - 48);
                sm = sm + (num * wigth);
                wigth = wigth + 1;
                if (wigth == 10)
                    wigth = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig14 = '0';
            else
                dig14 = (char) ((11 - r) + 48);

            if ((dig13 == value.charAt(12)) && (dig14 == value.charAt(13)))
                return true;

            return false;
        } catch (Exception erro) {
            return false;
        }
    }

    private String removeSpecialChars(String cnpj) {
        if (cnpj.contains("."))
            cnpj = cnpj.replace(".", "");

        if (cnpj.contains("-"))
            cnpj = cnpj.replace("-", "");

        if (cnpj.contains("/"))
            cnpj = cnpj.replace("/", "");

        return cnpj;
    }
}

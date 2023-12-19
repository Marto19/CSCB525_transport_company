package org.example.DTO;

import java.math.BigDecimal;

public class TransportCompanyDTO {
    private long idTransporCompany;
    private String name;
    private BigDecimal income;

    public TransportCompanyDTO(long idTransporCompany, String name, BigDecimal income) {
        this.idTransporCompany = idTransporCompany;
        this.name = name;
        this.income = income;
    }

    public long getIdTransportCompany() {
        return idTransporCompany;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getIncome() {
        return income;
    }

    @Override
    public String toString() {
        return "TransportCompanyDTO{" +
                "idTransporCompany=" + idTransporCompany +
                ", name='" + name + '\'' +
                ", income=" + income +
                '}';
    }
}

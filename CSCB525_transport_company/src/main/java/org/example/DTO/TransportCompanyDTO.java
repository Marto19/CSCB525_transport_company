package org.example.DTO;

public class TransportCompanyDTO {
    private long idTransporCompany;
    private String name;

    public TransportCompanyDTO(long idTransporCompany, String name) {
        this.idTransporCompany = idTransporCompany;
        this.name = name;
    }

    public long getIdTransporCompany() {
        return idTransporCompany;
    }

    public void setIdTransporCompany(long idTransporCompany) {
        this.idTransporCompany = idTransporCompany;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TransportCompanyDTO{" +
                "idTransporCompany=" + idTransporCompany +
                ", name='" + name + '\'' +
                '}';
    }
}

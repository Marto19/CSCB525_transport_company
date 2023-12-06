package org.example.entity;

import java.util.UUID;

public class TransportCompany {
    private UUID idTransportCompany;
    private String name;

    public TransportCompany(UUID idTransportCompany, String name) {
        this.idTransportCompany = idTransportCompany;
        this.name = name;
    }

    public UUID getIdTransportCompany() {
        return idTransportCompany;
    }

    public void setIdTransportCompany(UUID idTransportCompany) {
        this.idTransportCompany = idTransportCompany;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TransportCompany{" +
                "idTransportCompany=" + idTransportCompany +
                ", name='" + name + '\'' +
                '}';
    }
}

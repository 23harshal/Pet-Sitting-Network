package com.petSitting.petSitting.dto;

import java.math.BigDecimal;

public class ServiceRequest {
    private String serviceName;
    private BigDecimal servicePrice;

    // Getters and Setters
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
    }
}

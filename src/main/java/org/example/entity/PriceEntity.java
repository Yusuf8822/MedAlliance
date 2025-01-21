package org.example.entity;

public class PriceEntity {

    private Long id;

    private int price;

    private String serviceName;

    private String type;

    public PriceEntity() {
    }

    public PriceEntity(Long id, int price, String serviceName, String type) {
        this.id = id;
        this.price = price;
        this.serviceName = serviceName;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

package com.ttbmp.cinehub.core.entity;

/**
 * @author Palmieri Ivan
 */
public class Payment {

    private String id;
    private long price;
    private final String status;

    public Payment(String id, long l, String status) {
        this.id = id;
        this.price = l;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


}

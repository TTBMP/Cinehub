package com.ttbmp.cinehub.domain;

/**
 * @author Palmieri Ivan
 */
public class Payment {

    private String id;
    private long price;

    public Payment(String id, long l) {
        this.id = id;
        this.price = l;
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

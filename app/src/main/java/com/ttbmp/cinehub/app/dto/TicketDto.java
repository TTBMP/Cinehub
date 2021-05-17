package com.ttbmp.cinehub.app.dto;

/**
 * @author Ivan Palmieri
 */
public class TicketDto {

    private int id;
    private long price;
    private CustomerDto owner;
    private SeatDto seatDto;
    private ProjectionDto projectionDto;

    public TicketDto(int id, long price, CustomerDto owner, SeatDto seatDto, ProjectionDto projectionDto) {
        this.id = id;
        this.owner = owner;
        this.price = price;
        this.seatDto = seatDto;
        this.projectionDto = projectionDto;
    }

    public SeatDto getSeatDto() {
        return seatDto;
    }

    public void setSeatDto(SeatDto seatDto) {
        this.seatDto = seatDto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CustomerDto getOwner() {
        return owner;
    }

    public void setOwner(CustomerDto owner) {
        this.owner = owner;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public ProjectionDto getProjectionDto() {
        return projectionDto;
    }

    public void setProjectionDto(ProjectionDto projectionDto) {
        this.projectionDto = projectionDto;
    }

}

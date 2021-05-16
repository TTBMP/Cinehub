package com.ttbmp.cinehub.app.dto;

import java.util.List;

public class UserDto {

    private String id;
    private String name;
    private String surname;
    private String email;
    private List<TicketDto> ownedTicketDtoList;

    public UserDto(String id, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TicketDto> getOwnedTicketDtoList() {
        return ownedTicketDtoList;
    }

    public void setOwnedTicketDtoList(List<TicketDto> ownedTicketDtoList) {
        this.ownedTicketDtoList = ownedTicketDtoList;
    }

}
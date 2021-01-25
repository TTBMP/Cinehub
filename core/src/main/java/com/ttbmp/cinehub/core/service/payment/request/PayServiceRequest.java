package com.ttbmp.cinehub.core.service.payment.request;

import com.ttbmp.cinehub.core.dto.UserDto;
import com.ttbmp.cinehub.core.usecase.Request;

public class PayServiceRequest extends Request {

    private final UserDto userDto;
    private final long ticketPrice;

    public PayServiceRequest(UserDto userDto, long ticketPrice) {
        this.userDto = userDto;
        this.ticketPrice = ticketPrice;
    }

    public UserDto getUserDto() {
        return userDto;
    }


    public long getTicketPrice() {
        return ticketPrice;
    }


    @Override
    public void onValidate() {

    }
}

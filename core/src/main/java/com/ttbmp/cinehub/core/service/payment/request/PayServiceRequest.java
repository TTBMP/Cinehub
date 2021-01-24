package com.ttbmp.cinehub.core.service.payment.request;

import com.ttbmp.cinehub.core.dto.TicketDto;
import com.ttbmp.cinehub.core.dto.UserDto;
import com.ttbmp.cinehub.core.usecase.Request;

public class PayServiceRequest extends Request {

    private final UserDto userDto;
    private final TicketDto ticketDto;

    public PayServiceRequest(UserDto userDto, TicketDto ticketDto) {
        this.userDto = userDto;
        this.ticketDto = ticketDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }


    public TicketDto getTicketDto() {
        return ticketDto;
    }


    @Override
    public void onValidate() {

    }
}

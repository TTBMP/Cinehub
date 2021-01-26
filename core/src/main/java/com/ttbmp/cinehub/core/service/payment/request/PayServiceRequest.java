package com.ttbmp.cinehub.core.service.payment.request;

import com.ttbmp.cinehub.core.dto.UserDto;
import com.ttbmp.cinehub.core.usecase.Request;
/**
 * @author Palmieri Ivan
 */
public class PayServiceRequest extends Request {

    public static final Request.Error MISSING_USER_ERROR = new Request.Error("User can't be null");
    public static final Request.Error MISSING_PRICE_ERROR = new Request.Error("TicketPrice can't be < 0");

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
        if(userDto==null){
            addError(MISSING_USER_ERROR);
        }
        if(ticketPrice==0){
            addError(MISSING_PRICE_ERROR);
        }
    }
}

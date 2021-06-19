package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.ui.web.domain.Cinema;
import com.ttbmp.cinehub.ui.web.domain.Movie;
import com.ttbmp.cinehub.ui.web.domain.Projection;
import com.ttbmp.cinehub.ui.web.domain.Seat;
import lombok.Data;

@Data
public class PaymentForm {

    private String date;
    private Movie movie;
    private Cinema cinema;
    private Projection projection;
    private Seat seat;
    private Boolean skipLineOption;
    private Boolean magicBoxOption;
    private Boolean openBarOption;
    private String email;
    private String cvv;
    private String expirationDate;
    private String numberCard;

}

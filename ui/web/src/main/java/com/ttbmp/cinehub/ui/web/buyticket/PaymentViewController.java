package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.dto.*;
import com.ttbmp.cinehub.app.usecase.buyticket.Handler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.ui.web.domain.Projection;
import com.ttbmp.cinehub.ui.web.domain.Seat;
import com.ttbmp.cinehub.ui.web.domain.Ticket;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author Palmieri Ivan
 */
@Controller
public class PaymentViewController {


    @PostMapping("/payment/{optionOne}/{optionTwo}/{optionThree}")
    public String payment(
            @ModelAttribute("seat") Seat seat,
            @PathVariable("optionOne") boolean optionOne,
            @PathVariable("optionTwo") boolean optionTwo,
            @PathVariable("optionThree") boolean optionThree,
            Model model) {
        model.addAttribute("option1", optionOne);
        model.addAttribute("option2", optionTwo);
        model.addAttribute("option3", optionThree);
        model.addAttribute("ticket", new Ticket());
        return "payment";
    }



    @PostMapping("/payment")
    public String payment(@ModelAttribute("ticket") Ticket ticket, Model model) {
        BuyTicketUseCase buyTicketUseCase = new Handler(new BuyTicketPresenterWeb(model));
        model.addAttribute("paymentError","");
        buyTicketUseCase.getProjectionList(new GetProjectionRequest(ticket.getMovieId(), ticket.getCinemaId(), LocalDate.parse(ticket.getDate()) ,null, ticket.getHallId()));
        ProjectionDto projection = ((ArrayList<ProjectionDto>)model.getAttribute("projectionList")).get(0);
        buyTicketUseCase.createTicket(new GetTicketBySeatsRequest(
                projection.getHallDto().getSeatList(),
                ticket.getPosition(),
                ticket.getNumber(),
                ticket.getOption1(),
                ticket.getOption2(),
                ticket.getOption3()
        ));
        TicketDto ticketDto = (TicketDto)model.getAttribute("selectedTicket");
        buyTicketUseCase.pay(new PaymentRequest(
                ticketDto,
                projection,
                ticket.getNumber(),
                projection.getCinemaDto(),
                projection.getMovieDto(),
                ticket.getDate()
        ));
        if (((String)model.getAttribute("paymentError")).isEmpty()) {
            return "confirm_email";
       }
        model.addAttribute("errorPayment", "There are an error with the payment, try to late...");
        return "home";
    }


}

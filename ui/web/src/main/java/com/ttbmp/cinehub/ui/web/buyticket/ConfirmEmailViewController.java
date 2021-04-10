package com.ttbmp.cinehub.ui.web.buyticket;


import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.app.dto.TicketDto;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.Handler;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetProjectionRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetTicketBySeatsRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.PaymentRequest;
import com.ttbmp.cinehub.ui.web.domain.Ticket;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Palmieri Ivan
 */
@Controller
public class ConfirmEmailViewController {

    @PostMapping("/confirm_email")
    public String confirmEmail(@ModelAttribute("ticket") Ticket ticket, Model model) {
        BuyTicketUseCase buyTicketUseCase = new Handler(new BuyTicketPresenterWeb(model));
        model.addAttribute("paymentError","");
        buyTicketUseCase.getProjectionList(new GetProjectionRequest(ticket.getMovieId(), ticket.getCinemaId(), LocalDate.parse(ticket.getDate()) ,null, ticket.getHallId()));
        ProjectionDto projection = ((ArrayList<ProjectionDto>) model.getAttribute("projectionList")).get(0);
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

        model.addAttribute("ticketId",ticket.getPosition());
        model.addAttribute("cinemaName",projection.getCinemaDto().getName());
        model.addAttribute("movieName",projection.getMovieDto().getName());
        model.addAttribute("date",projection.getDate());
        model.addAttribute("screeningTime",projection.getStartTime());
        assert ticketDto != null;
        model.addAttribute("price",ticketDto.getPrice());
        if (!((String) Objects.requireNonNull(model.getAttribute("paymentError"))).isEmpty()) {
            return "choose_movie";
        }
        return "confirm_email";
    }

}

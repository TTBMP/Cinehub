package com.ttbmp.cinehub.ui.web.buyticket;


import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.app.dto.TicketDto;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketHandler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetCinemaRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetProjectionListRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetTicketBySeatsRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.PaymentRequest;
import com.ttbmp.cinehub.ui.web.domain.Ticket;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * @author Palmieri Ivan
 */
@Controller
public class ConfirmEmailViewController {

    @PostMapping("/confirm_email")
    public String confirmEmail(@ModelAttribute("ticket") Ticket ticket, Model model) {
        BuyTicketUseCase buyTicketUseCase = new BuyTicketHandler(new BuyTicketPresenterWeb(model));
        model.addAttribute("paymentError", "");
        buyTicketUseCase.getProjectionList(new GetProjectionListRequest(
                ticket.getMovieId(),
                ticket.getCinemaId(),
                LocalDate.parse(ticket.getDate()),
                ticket.getHallId()
        ));
        ProjectionDto projection = ((List<ProjectionDto>) model.getAttribute("projectionList")).get(0);
        buyTicketUseCase.createTicket(new GetTicketBySeatsRequest(
                projection.getHallDto().getSeatList(),
                ticket.getPosition(),
                ticket.getNumber(),
                ticket.getOption1(),
                ticket.getOption2(),
                ticket.getOption3()
        ));
        buyTicketUseCase.getCinema(new GetCinemaRequest(projection));
        CinemaDto cinemaDto = (CinemaDto) model.getAttribute("cinema");
        TicketDto ticketDto = (TicketDto) model.getAttribute("selectedTicket");
        buyTicketUseCase.pay(new PaymentRequest(
                ticketDto,
                projection,
                ticket.getNumber(),
                cinemaDto,
                projection.getMovieDto(),
                ticket.getDate()
        ));


        model.addAttribute("ticketId", ticket.getPosition());
        model.addAttribute("cinemaName", cinemaDto.getName());
        model.addAttribute("movieName", projection.getMovieDto().getName());
        model.addAttribute("date", projection.getDate());
        model.addAttribute("screeningTime", projection.getStartTime());
        assert ticketDto != null;
        model.addAttribute("price", ticketDto.getPrice());
        if (!((String) Objects.requireNonNull(model.getAttribute("paymentError"))).isEmpty()) {
            return "choose_movie";
        }
        return "confirm_email";
    }

}

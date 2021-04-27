package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketHandler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetNumberOfSeatsRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetProjectionRequest;
import com.ttbmp.cinehub.ui.web.domain.Projection;
import com.ttbmp.cinehub.ui.web.domain.Seat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

/**
 * @author Palmieri Ivan
 */
@Controller
public class ChooseSeatsViewController {

    @PostMapping("/choose_seat")
    public String chooseSeats(@ModelAttribute("projection") Projection projection,
                              Model model) {

        BuyTicketUseCase buyTicketUseCase = new BuyTicketHandler(new BuyTicketPresenterWeb(model));
        buyTicketUseCase.getProjection(new GetProjectionRequest(
                LocalDate.parse(projection.getDate()),
                projection.getStartTime(),
                projection.getHallId()
        ));
        buyTicketUseCase.getListOfSeat(new GetNumberOfSeatsRequest(((ProjectionDto) model.getAttribute("projection"))));
        model.addAttribute("boolean1", false);
        model.addAttribute("boolean2", false);
        model.addAttribute("boolean3", false);
        model.addAttribute("color", "color:" + "white");
        model.addAttribute("classValue", "material-icons");
        model.addAttribute("selectedDate", projection.getDate());
        model.addAttribute("movieId", projection.getMovieId());
        model.addAttribute("cinemaId", projection.getCinemaId());
        model.addAttribute("hallId", projection.getHallId());
        model.addAttribute("seat", new Seat());
        return "choose_seats";
    }


}

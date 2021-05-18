package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketHandler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.CinemaInformationRequest;
import com.ttbmp.cinehub.ui.web.buyticket.form.ProjectionForm;
import com.ttbmp.cinehub.ui.web.buyticket.form.SeatForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Palmieri Ivan
 */
@Controller
public class ChooseSeatsViewController {

    @PostMapping("/choose_seat")
    public String chooseSeats(
            @ModelAttribute("projection_form") ProjectionForm projectionForm,
            Model model) {
        BuyTicketUseCase useCase = new BuyTicketHandler(new BuyTicketPresenterWeb(model));
        model.addAttribute("color", "color:" + "white");
        model.addAttribute("classValue", "material-icons");
        var seatForm = new SeatForm();
        seatForm.setDate(projectionForm.getDate());
        seatForm.setMovie(projectionForm.getMovie());
        seatForm.setCinema(projectionForm.getCinema());
        seatForm.setProjection(projectionForm.getProjection());
        model.addAttribute("seat_form", seatForm);
        useCase.getSeatList(new CinemaInformationRequest(seatForm.getProjection().getId()));
        return "buy_ticket/choose_seats";
    }

}

package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketHandler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.CinemaListRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.ProjectionListRequest;
import com.ttbmp.cinehub.ui.web.buyticket.form.CinemaForm;
import com.ttbmp.cinehub.ui.web.buyticket.form.MovieForm;
import com.ttbmp.cinehub.ui.web.buyticket.form.ProjectionForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

/**
 * @author Palmieri Ivan
 */
@Controller
public class ChooseCinemaViewController {

    @PostMapping("/choose_cinema")
    public String chooseCinema(
            @ModelAttribute("movie_form") MovieForm movieForm,
            Model model) {
        BuyTicketUseCase useCase = new BuyTicketHandler(new BuyTicketPresenterWeb(model));
        var cinemaForm = new CinemaForm();
        cinemaForm.setDate(movieForm.getDate());
        cinemaForm.setMovie(movieForm.getMovie());
        model.addAttribute("cinema_form", cinemaForm);
        useCase.getCinemaList(new CinemaListRequest(movieForm.getMovie().getId(), movieForm.getDate()));
        return "buy_ticket/choose_cinema";
    }

    @PostMapping("/choose_projection")
    public String chooseProjection(
            @ModelAttribute("cinema_form") CinemaForm cinemaForm,
            Model model) {
        BuyTicketUseCase useCase = new BuyTicketHandler(new BuyTicketPresenterWeb(model));
        var projectionForm = new ProjectionForm();
        projectionForm.setDate(cinemaForm.getDate());
        projectionForm.setMovie(cinemaForm.getMovie());
        projectionForm.setCinema(cinemaForm.getCinema());
        model.addAttribute("projection_form", projectionForm);
        useCase.getProjectionList(new ProjectionListRequest(
                cinemaForm.getMovie().getId(),
                cinemaForm.getCinema().getId(),
                LocalDate.parse(cinemaForm.getDate())
        ));
        return "buy_ticket/choose_cinema";
    }
}

package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketHandler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.CinemaListRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.MovieListRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.ProjectionListRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

/**
 * @author Palmieri Ivan
 */
@Controller
public class ChooseProjectionViewController {

    private static final String PAYMENT_FORM_ATTRIBUTE = "payment_form";

    @GetMapping("/choose_movie")
    public String chooseMovie(
            @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, Model model) {
        if (date == null) {
            date = LocalDate.now();
        }
        BuyTicketUseCase buyTicketUseCase = new BuyTicketHandler(new BuyTicketPresenterWeb(model));
        buyTicketUseCase.getMovieList(new MovieListRequest(date));
        model.addAttribute(PAYMENT_FORM_ATTRIBUTE, new PaymentForm());
        model.addAttribute("selected_date", date);
        return "buy_ticket/choose_movie";
    }

    @PostMapping("/choose_cinema")
    public String chooseCinema(
            @ModelAttribute("payment_form") PaymentForm paymentForm,
            Model model) {
        model.addAttribute(PAYMENT_FORM_ATTRIBUTE, paymentForm);
        BuyTicketUseCase useCase = new BuyTicketHandler(new BuyTicketPresenterWeb(model));
        useCase.getCinemaList(new CinemaListRequest(
                paymentForm.getMovie().getId(),
                paymentForm.getDate()
        ));
        var errorMessage = model.getAttribute("messageError");
        if (errorMessage != null) {
            return "buy_ticket/choose_movie";
        }
        return "buy_ticket/choose_cinema";
    }

    @PostMapping("/choose_projection")
    public String chooseProjection(
            @ModelAttribute("payment_form") PaymentForm paymentForm,
            Model model) {
        model.addAttribute(PAYMENT_FORM_ATTRIBUTE, paymentForm);
        BuyTicketUseCase useCase = new BuyTicketHandler(new BuyTicketPresenterWeb(model));
        useCase.getProjectionList(new ProjectionListRequest(
                paymentForm.getMovie().getId(),
                paymentForm.getCinema().getId(),
                LocalDate.parse(paymentForm.getDate())
        ));
        var errorMessage = model.getAttribute("messageError");
        if (errorMessage != null) {
            return "login";
        }
        return "buy_ticket/choose_cinema";
    }

}

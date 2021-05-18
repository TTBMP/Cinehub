package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketHandler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.CinemaListRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.ProjectionListRequest;
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
            @ModelAttribute("payment_form") PaymentForm paymentForm,
            Model model) {
        model.addAttribute("payment_form", paymentForm);
        BuyTicketUseCase useCase = new BuyTicketHandler(new BuyTicketPresenterWeb(model));
        useCase.getCinemaList(new CinemaListRequest(paymentForm.getMovie().getId(), paymentForm.getDate()));
        return "buy_ticket/choose_cinema";
    }

    @PostMapping("/choose_projection")
    public String chooseProjection(
            @ModelAttribute("payment_form") PaymentForm paymentForm,
            Model model) {
        model.addAttribute("payment_form", paymentForm);
        BuyTicketUseCase useCase = new BuyTicketHandler(new BuyTicketPresenterWeb(model));
        useCase.getProjectionList(new ProjectionListRequest(
                paymentForm.getMovie().getId(),
                paymentForm.getCinema().getId(),
                LocalDate.parse(paymentForm.getDate())
        ));
        return "buy_ticket/choose_cinema";
    }
}

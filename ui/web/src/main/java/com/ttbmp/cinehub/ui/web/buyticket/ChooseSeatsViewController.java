package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketHandler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.SeatListRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Palmieri Ivan
 */
@Controller
public class ChooseSeatsViewController {

    @PostMapping("/choose_seat")
    public String chooseSeats(
            @CookieValue(value = "session") String sessionToken,
            @ModelAttribute("payment_form") PaymentForm paymentForm,
            Model model) {
        model.addAttribute("payment_form", paymentForm);
        model.addAttribute("color", "color:" + "white");
        model.addAttribute("classValue", "material-icons");
        BuyTicketUseCase useCase = new BuyTicketHandler(new BuyTicketPresenterWeb(model));
        useCase.getSeatList(new SeatListRequest(sessionToken, paymentForm.getProjection().getId()));
        return "buy_ticket/choose_seats";
    }

}

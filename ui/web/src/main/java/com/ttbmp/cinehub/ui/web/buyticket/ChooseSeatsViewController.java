package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.dto.SeatDto;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketHandler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.SeatListRequest;
import com.ttbmp.cinehub.ui.web.utilities.ErrorHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
@Controller
public class ChooseSeatsViewController {

    @PostMapping("/choose_seat")
    public String chooseSeats(
            HttpServletResponse response,
            @CookieValue(value = "session", required = false) String sessionToken,
            @ModelAttribute("payment_form") PaymentForm paymentForm,
            Model model) {
        model.addAttribute("payment_form", paymentForm);
        BuyTicketUseCase useCase = new BuyTicketHandler(new BuyTicketPresenterWeb(model));
        useCase.getSeatList(new SeatListRequest(sessionToken, paymentForm.getProjection().getId()));
        return ErrorHelper.returnView(response, model, "buy_ticket/choose_seats");
    }

}

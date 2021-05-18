package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketHandler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.PaymentRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

/**
 * @author Palmieri Ivan
 */
@Controller
public class ConfirmEmailViewController {

    @PostMapping("/confirm_email")
    public String confirmEmail(
            @ModelAttribute("payment_form") PaymentForm paymentForm,
            Model model) {
        BuyTicketUseCase buyTicketUseCase = new BuyTicketHandler(new BuyTicketPresenterWeb(model));
        model.addAttribute("payment_form", paymentForm);
        buyTicketUseCase.pay(new PaymentRequest(
                paymentForm.getProjection().getId(),
                paymentForm.getSeat().getId(),
                0,
                paymentForm.getNumberCard(),
                paymentForm.getCvv(),
                paymentForm.getDate(),
                paymentForm.getEmail()
        ));
        model.addAttribute("paymentError", "");
        if (!((String) Objects.requireNonNull(model.getAttribute("paymentError"))).isEmpty()) {
            return "buy_ticket/choose_movie";
        }
        return "buy_ticket/confirm_email";
    }

}

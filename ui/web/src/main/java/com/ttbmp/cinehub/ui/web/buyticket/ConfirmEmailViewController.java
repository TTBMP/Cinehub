package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketHandler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.PaymentRequest;
import com.ttbmp.cinehub.ui.web.utilities.ErrorHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Palmieri Ivan
 */
@Controller
public class ConfirmEmailViewController {

    @PostMapping("/confirm_email")
    public String confirmEmail(
            HttpServletResponse response,
            @CookieValue(value = "session") String sessionToken,
            @ModelAttribute("payment_form") PaymentForm paymentForm,
            Model model) {
        BuyTicketUseCase buyTicketUseCase = new BuyTicketHandler(new BuyTicketPresenterWeb(model));
        model.addAttribute("payment_form", paymentForm);
        buyTicketUseCase.pay(new PaymentRequest(
                sessionToken,
                paymentForm.getProjection().getId(),
                paymentForm.getSeat().getId(),
                paymentForm.getEmail(),
                paymentForm.getNumberCard(),
                paymentForm.getCvv(),
                paymentForm.getExpirationDate(),
                paymentForm.getOption2(),//magicBox
                paymentForm.getOption3(),//openBar
                paymentForm.getOption1()//skipLine
        ));
        return ErrorHelper.returnView(response, model, "buy_ticket/confirm_email");
    }

}

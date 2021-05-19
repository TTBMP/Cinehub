package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketHandler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Palmieri Ivan
 */
@Controller
public class PaymentViewController {

    @PostMapping("/payment/{optionOne}/{optionTwo}/{optionThree}")
    public String payment(
            @ModelAttribute("payment_form") PaymentForm paymentForm,
            @PathVariable("optionOne") boolean optionOne,
            @PathVariable("optionTwo") boolean optionTwo,
            @PathVariable("optionThree") boolean optionThree,
            Model model) {
        paymentForm.setOption1(optionOne);
        paymentForm.setOption2(optionTwo);
        paymentForm.setOption3(optionThree);
        model.addAttribute("payment_form", paymentForm);
        return "buy_ticket/payment";
    }

}

package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.ui.web.utilities.ErrorHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Palmieri Ivan
 */
@Controller
public class PaymentViewController {

    @PostMapping("/payment/{optionOne}/{optionTwo}/{optionThree}")
    public String payment(
            HttpServletResponse response,
            @ModelAttribute("payment_form") PaymentForm paymentForm,
            @PathVariable("optionOne") boolean optionOne,
            @PathVariable("optionTwo") boolean optionTwo,
            @PathVariable("optionThree") boolean optionThree,
            Model model) {
        paymentForm.setOption1(optionOne);
        paymentForm.setOption2(optionTwo);
        paymentForm.setOption3(optionThree);
        model.addAttribute("payment_form", paymentForm);
        return ErrorHelper.returnView(response, model, "buy_ticket/payment");
    }

}

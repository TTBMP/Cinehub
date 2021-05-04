package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.ui.web.domain.PaymentForm;
import com.ttbmp.cinehub.ui.web.domain.Ticket;
import com.ttbmp.cinehub.ui.web.domain.Seat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
            @ModelAttribute("seat") Seat seat,
            @PathVariable("optionOne") boolean optionOne,
            @PathVariable("optionTwo") boolean optionTwo,
            @PathVariable("optionThree") boolean optionThree,
            Model model) {
        model.addAttribute("option1", optionOne);
        model.addAttribute("option2", optionTwo);
        model.addAttribute("option3", optionThree);
        model.addAttribute("paymentForm", new PaymentForm());
        return "payment";
    }


}

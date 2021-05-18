package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketHandler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.TicketRequest;
import com.ttbmp.cinehub.ui.web.buyticket.form.SeatForm;
import com.ttbmp.cinehub.ui.web.buyticket.form.PaymentForm;
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
            @ModelAttribute("seat_form") SeatForm seatForm,
            @PathVariable("optionOne") boolean optionOne,
            @PathVariable("optionTwo") boolean optionTwo,
            @PathVariable("optionThree") boolean optionThree,
            Model model) {
        BuyTicketUseCase buyTicketUseCase = new BuyTicketHandler(new BuyTicketPresenterWeb(model));
        model.addAttribute("option1", optionOne);
        model.addAttribute("option2", optionTwo);
        model.addAttribute("option3", optionThree);
        var paymentForm = new PaymentForm();
        paymentForm.setDate(seatForm.getDate());
        paymentForm.setMovie(seatForm.getMovie());
        paymentForm.setCinema(seatForm.getCinema());
        paymentForm.setProjection(seatForm.getProjection());
        paymentForm.setSeat(seatForm.getSeat());
        model.addAttribute("payment_form", paymentForm);
        buyTicketUseCase.createTicket(new TicketRequest(
                seatForm.getSeat().getId(),
                optionOne,
                optionTwo,
                optionThree,
                seatForm.getProjection().getId()
        ));
        return "buy_ticket/payment";
    }

}

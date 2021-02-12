package com.ttbmp.cinehub.ui.web.buyticket.controller;

import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetTicketBySeatsRequest;
import com.ttbmp.cinehub.ui.web.buyticket.BuyTicketViewModel;
import com.ttbmp.cinehub.ui.web.buyticket.UseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Palmieri Ivan
 */
@Controller
public class PaymentController {

    private static final BuyTicketUseCase USE_CASE = UseCase.buyTicketUseCase;
    private final BuyTicketViewModel viewModel = UseCase.getViewModel();

    @GetMapping("/payment/{position}/{index}/{optionOne}/{optionTwo}/{optionThree}")
    public String payment(
            @PathVariable("position") String position,
            @PathVariable("index") Integer number,
            @PathVariable("optionOne") Boolean optionOne,
            @PathVariable("optionTwo") Boolean optionTwo,
            @PathVariable("optionThree") Boolean optionThree,
            Model model) {
        viewModel.setSelectedPosition(number);
        USE_CASE.createTicket(new GetTicketBySeatsRequest(viewModel.getSeatDtoList(), position, number, optionOne, optionTwo, optionThree));
        model.addAttribute("projection", viewModel.getSelectedProjection());
        model.addAttribute("ticket", viewModel.getSelectedTicket());
        return "payment";
    }

}

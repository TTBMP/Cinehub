package com.ttbmp.cinehub.ui.web.buyticket.controller;

import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetTicketBySeatsRequest;
import com.ttbmp.cinehub.ui.web.buyticket.BuyTicketViewModel;
import com.ttbmp.cinehub.ui.web.buyticket.UseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PaymentController {

    private static final BuyTicketUseCase USE_CASE = UseCase.buyTicketUseCase;
    private final BuyTicketViewModel viewModel = UseCase.getViewModel();

    @GetMapping("/payment/{position}/{index}/{val1}/{val2}/{val3}")
    public String payment(
            @PathVariable("position")String position,
            @PathVariable("index")Integer pos,
            @PathVariable("val1")Boolean val1,
            @PathVariable("val2")Boolean val2,
            @PathVariable("val3")Boolean val3,
            Model model) {
        viewModel.setSelectedPosition(pos);
        USE_CASE.createTicket(new GetTicketBySeatsRequest(viewModel.getSeatDtoList(),position,pos,val1,val2,val3));
        model.addAttribute("projection", viewModel.getSelectedProjection());
        model.addAttribute("ticket", viewModel.getSelectedTicket());
        return "payment";
    }
}

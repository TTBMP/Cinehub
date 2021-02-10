package com.ttbmp.cinehub.ui.web.buyticket.controller;


import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.PayRequest;
import com.ttbmp.cinehub.ui.web.buyticket.BuyTicketViewModel;
import com.ttbmp.cinehub.ui.web.buyticket.UseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ConfirmEmailController {
    private static final BuyTicketUseCase USE_CASE = UseCase.buyTicketUseCase;
    private final BuyTicketViewModel viewModel = UseCase.getViewModel();

    @GetMapping("/confirm_email")
    public String confirmEmail(Model model) {
        if(!USE_CASE.pay(new PayRequest(viewModel.getSelectedTicket(), viewModel.getSelectedProjection(), viewModel.getSelectedPosition()))){
            model.addAttribute("result","Error with the Payment");
            model.addAttribute("boolean_result",false);
        }
        else {
            model.addAttribute("result", "Payment with success!");
            model.addAttribute("ticket", viewModel.getSelectedTicket().getPosition());
            model.addAttribute("cinema", viewModel.getSelectedProjection().getCinemaDto().getName());
            model.addAttribute("movie", viewModel.getSelectedProjection().getMovieDto().getName());
            model.addAttribute("date", viewModel.getSelectedProjection().getDate());
            model.addAttribute("screening_time",viewModel.getSelectedProjection().getStartTime());
            model.addAttribute("price",viewModel.getSelectedTicket().getPrice());
            model.addAttribute("boolean_result",true);
        }
        return "confirm_email";
    }
}

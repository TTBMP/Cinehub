package com.ttbmp.cinehub.ui.web.access;


import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.ui.web.buyticket.BuyTicketViewModel;
import com.ttbmp.cinehub.ui.web.buyticket.UseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Palmieri Ivan
 */
@Controller
public class Register {

    private static final BuyTicketUseCase USE_CASE = UseCase.buyTicketUseCase;
    private final BuyTicketViewModel viewModel = UseCase.getViewModel();

    @GetMapping("/register")
    public String Register(Model model) {
        return "register";
    }
}

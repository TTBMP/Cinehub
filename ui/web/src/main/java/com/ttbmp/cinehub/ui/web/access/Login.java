package com.ttbmp.cinehub.ui.web.access;


import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.ui.web.buyticket.BuyTicketViewModel;
import com.ttbmp.cinehub.ui.web.buyticket.UseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
public class Login {

    private static final BuyTicketUseCase USE_CASE = UseCase.buyTicketUseCase;
    private final BuyTicketViewModel viewModel = UseCase.getViewModel();

    @GetMapping("/login")
    public String Login(Model model) {
        return "login";
    }


    @GetMapping("/login/{email}/{password}")
    public String Register(@PathVariable("email") String email, @PathVariable("password")  String password , Model model) {
        //TODO: Login with Firebase
        return "/login";
    }

}

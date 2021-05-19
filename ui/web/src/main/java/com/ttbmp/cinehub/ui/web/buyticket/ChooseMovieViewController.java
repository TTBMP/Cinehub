package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketHandler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.MovieListRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

/**
 * @author Palmieri Ivan
 */
@Controller
public class ChooseMovieViewController {

    @GetMapping("/choose_movie")
    public String getMoviePost(
            @CookieValue(value = "session") String sessionToken,
            @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, Model model) {
        if (date == null) {
            date = LocalDate.now();
        }
        BuyTicketUseCase buyTicketUseCase = new BuyTicketHandler(new BuyTicketPresenterWeb(model));
        buyTicketUseCase.getMovieList(new MovieListRequest( sessionToken,date));
        model.addAttribute("payment_form", new PaymentForm());
        model.addAttribute("selected_date", date);
        return "buy_ticket/choose_movie";
    }


}

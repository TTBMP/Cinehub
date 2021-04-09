package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.usecase.buyticket.Handler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetListMovieRequest;
import com.ttbmp.cinehub.ui.web.domain.Projection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Palmieri Ivan
 */
@Controller
public class ChooseMovieViewController {

    private static final String CHOOSE_DATE = "choose_date";
    private static final String CHOOSE_MOVIE = "choose_movie";


    @GetMapping("/choose_movie")
    public String chooseMovie(Model model) {
        BuyTicketUseCase buyTicketUseCase = new Handler(new BuyTicketPresenterWeb(model));
        model.addAttribute(CHOOSE_DATE, LocalDate.now());
        buyTicketUseCase.getListMovie(new GetListMovieRequest(LocalDate.now()));
        model.addAttribute("projection", new Projection());
        return CHOOSE_MOVIE;
    }



    @PostMapping("/choose_movie")//USARE UNA DATA
    public String getMoviePost(@ModelAttribute("projection") Projection projection,Model model) {
        BuyTicketUseCase buyTicketUseCase = new Handler(new BuyTicketPresenterWeb(model));
        buyTicketUseCase.getListMovie(new GetListMovieRequest(LocalDate.parse(projection.getDate())));
        model.addAttribute(CHOOSE_DATE, projection.getDate());
        model.addAttribute("projection", projection);
        return CHOOSE_MOVIE;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text != null)
                    setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
        });
    }


}

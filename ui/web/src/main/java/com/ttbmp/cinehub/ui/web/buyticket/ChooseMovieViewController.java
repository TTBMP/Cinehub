package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.usecase.buyticket.Handler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetListMovieRequest;
import com.ttbmp.cinehub.ui.web.domain.Projection;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

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
    public String getMoviePost(@RequestParam(value="date",required = false) @DateTimeFormat (iso=DateTimeFormat.ISO.DATE) LocalDate date, Model model) {
        BuyTicketUseCase buyTicketUseCase = new Handler(new BuyTicketPresenterWeb(model));
        if(date == null){
            date = LocalDate.now();
        }
        buyTicketUseCase.getListMovie(new GetListMovieRequest(date));
        model.addAttribute(CHOOSE_DATE, date);
        model.addAttribute("projection", new Projection());
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

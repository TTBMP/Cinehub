package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketHandler;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetListMovieRequest;
import com.ttbmp.cinehub.ui.web.domain.Projection;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Palmieri Ivan
 */
@Controller
public class ChooseMovieViewController {


    @GetMapping("/choose_movie")
    public String getMoviePost(@RequestParam(value="date",required = false) @DateTimeFormat (iso=DateTimeFormat.ISO.DATE) LocalDate date, Model model) {
        if(date == null ){
            date = LocalDate.now();
        }
        BuyTicketUseCase buyTicketUseCase = new BuyTicketHandler(new BuyTicketPresenterWeb(model));
        buyTicketUseCase.getListMovie(new GetListMovieRequest(date));
        model.addAttribute("choose_date", date);
        model.addAttribute("projection", new Projection());
        return "choose_movie";
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

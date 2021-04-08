package com.ttbmp.cinehub.ui.web.buyticket;


import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.app.usecase.buyticket.Handler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetListCinemaRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetListMovieRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetProjectionRequest;
import com.ttbmp.cinehub.ui.web.domain.Cinema;
import com.ttbmp.cinehub.ui.web.domain.Projection;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;



/**
 * @author Palmieri Ivan
 */
@Controller
public class ChooseCinemaViewController {


    private BuyTicketUseCase useCase;


    @PostMapping("/choose_cinema")
    public String chooseTimeOfProjectionPost(@ModelAttribute("projection") Projection projection,
            Model model) {

        useCase = new Handler(new BuyTicketPresenterWeb(model));
        useCase.getListMovie(new GetListMovieRequest(LocalDate.parse(projection.getDate())));
        useCase.getListCinema(new GetListCinemaRequest(projection.getMovieId(),projection.getDate()));
        model.addAttribute("idMovie",projection.getMovieId());
        model.addAttribute("dateProjection",projection.getDate());
        model.addAttribute("projection",projection);
        model.addAttribute("cinema",new Cinema());
        return "choose_cinema";
    }


    @PostMapping("/choose_projection")
    public String getListSeat(
            @ModelAttribute("projection") Projection projection,
            Model model) {

        useCase = new Handler(new BuyTicketPresenterWeb(model));
        useCase.getProjectionList(new GetProjectionRequest(
                projection.getMovieId(),
                projection.getCinemaId(),
                LocalDate.parse(projection.getDate())
        ));

        model.addAttribute("projection", new Projection());
        return "/choose_cinema";
    }
}

package com.ttbmp.cinehub.ui.web.buyticket;


import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketHandler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetListCinemaRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetListMovieRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetProjectionListRequest;
import com.ttbmp.cinehub.ui.web.domain.Projection;
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

    @PostMapping("/choose_cinema")
    public String chooseTimeOfProjectionPost(
            @ModelAttribute("projection") Projection projection,
            Model model) {
        BuyTicketUseCase useCase = new BuyTicketHandler(new BuyTicketPresenterWeb(model));
        useCase.getListMovie(new GetListMovieRequest(LocalDate.parse(projection.getDate())));
        useCase.getListCinema(new GetListCinemaRequest(projection.getMovieId(), projection.getDate()));
        model.addAttribute("idMovie", projection.getMovieId());
        model.addAttribute("dateProjection", projection.getDate());
        model.addAttribute("projection", projection);
        return "choose_cinema";
    }

    @PostMapping("/choose_projection")
    public String getListSeat(
            @ModelAttribute("projection") Projection projection,
            Model model) {
        BuyTicketUseCase useCase = new BuyTicketHandler(new BuyTicketPresenterWeb(model));
        useCase.getProjectionList(new GetProjectionListRequest(
                projection.getMovieId(),
                projection.getCinemaId(),
                LocalDate.parse(projection.getDate()),
                0
        ));
        model.addAttribute("cinemaDtoId", projection.getCinemaId());
        model.addAttribute("projection", new Projection());
        return "/choose_cinema";
    }
}

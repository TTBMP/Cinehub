package com.ttbmp.cinehub.ui.web.buyticket;


import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketHandler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetListCinemaRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetListMovieRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetProjectionRequest;
import com.ttbmp.cinehub.ui.web.domain.Cinema;
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

    private BuyTicketViewModel viewModel;
    private BuyTicketUseCase useCase;

    private MovieDto selectedMovieDto;
    private CinemaDto selectedCinemaDto;

    @PostMapping("/choose_cinema")
    public String chooseTimeOfProjectionPost(
            @ModelAttribute("projection") Projection projection,
            Model model) {
        viewModel = new BuyTicketViewModel();
        useCase = new BuyTicketHandler(new BuyTicketPresenterWeb(viewModel));
        useCase.getListMovie(new GetListMovieRequest(LocalDate.parse(projection.getDate())));


        viewModel.getMovieDtoList().forEach(movieDto -> {
            if (movieDto.getId() == projection.getMovieId()) {
                selectedMovieDto = movieDto;
            }
        });

        viewModel.setSelectedMovie(selectedMovieDto);
        viewModel.setSelectedDate(projection.getDate());
        useCase.getListCinema(new GetListCinemaRequest(projection.getMovieId(),projection.getDate()) );
        model.addAttribute("projection",projection);
        model.addAttribute("cinema",new Cinema());
        model.addAttribute("cinemaList", viewModel.getCinemaDtoList());
        return "choose_cinema";
    }


    @PostMapping("/choose_projection")
    public String getListSeat(
            @ModelAttribute("cinema") Cinema cinema,
            Model model) {
        useCase.getListCinema(new GetListCinemaRequest(viewModel.getSelectedMovie().getId(), viewModel.getSelectedDate()));
        viewModel.getCinemaDtoList().forEach(cinemaDto -> {
            if (cinemaDto.getId() == cinema.getId()) {
                selectedCinemaDto = cinemaDto;
            }
        });
        viewModel.setSelectedCinema(selectedCinemaDto);
        useCase.getProjectionList(new GetProjectionRequest(
                viewModel.getSelectedMovie(),
                viewModel.getSelectedCinema(),
                LocalDate.parse(viewModel.getSelectedDate())
        ));
        model.addAttribute("projection", new Projection());
        model.addAttribute("projectionList", viewModel.getProjectionList());
        model.addAttribute("selectedDate", viewModel.getSelectedDate());
        model.addAttribute("movieId", viewModel.getSelectedMovie().getId());
        model.addAttribute("cinemaId", viewModel.getSelectedCinema().getId());
        return "/choose_cinema";
    }
}

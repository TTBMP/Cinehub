package com.ttbmp.cinehub.ui.web.buyticket;


import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketHandler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetListCinemaRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetListMovieRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetProjectionRequest;
import com.ttbmp.cinehub.ui.web.buyticket.BuyTicketPresenterWeb;
import com.ttbmp.cinehub.ui.web.buyticket.BuyTicketViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;


/**
 * @author Palmieri Ivan
 */
@Controller
public class ChooseCinemaController {

    private BuyTicketViewModel viewModel;
    private BuyTicketUseCase useCase;

    private MovieDto selectedMovieDto;
    private CinemaDto selectedCinemaDto;

    @GetMapping("/choose_cinema/{selected_date}/{movie_id}")
    public String chooseTimeOfProjection(
            @PathVariable("selected_date") String selectedDate,
            @PathVariable("movie_id") int id,
            Model model) {
        viewModel = new BuyTicketViewModel();
        useCase = new BuyTicketHandler(new BuyTicketPresenterWeb(viewModel));
        useCase.getListMovie(new GetListMovieRequest(LocalDate.parse(selectedDate)));
        viewModel.getMovieDtoList().forEach(x -> {
            if (x.getId() == id) {
                selectedMovieDto = x;
            }
        });

        viewModel.setSelectedMovie(selectedMovieDto);
        viewModel.setSelectedDate(selectedDate);


        useCase.getListCinema(new GetListCinemaRequest(viewModel.getSelectedMovie(), viewModel.getSelectedDate()));
        model.addAttribute("cinemaList", viewModel.getCinemaDtoList());
        return "choose_cinema";
    }

    @GetMapping("/choose_cinema/{cinema_id}")
    public String getListSeat(
            @PathVariable("cinema_id") int id,
            Model model) {
        useCase.getListCinema(new GetListCinemaRequest(viewModel.getSelectedMovie(), viewModel.getSelectedDate()));
        viewModel.getCinemaDtoList().forEach(x -> {
            if (x.getId() == id) {
                selectedCinemaDto = x;
            }
        });
        viewModel.setSelectedCinema(selectedCinemaDto);
        useCase.getProjectionList(new GetProjectionRequest(
                viewModel.getSelectedMovie(),
                viewModel.getSelectedCinema(),
                LocalDate.parse(viewModel.getSelectedDate())
        ));
        model.addAttribute("projectionList", viewModel.getProjectionList());
        model.addAttribute("selectedDate", viewModel.getSelectedDate());
        model.addAttribute("movieId", viewModel.getSelectedMovie().getId());
        model.addAttribute("cinemaId", viewModel.getSelectedCinema().getId());
        return "/choose_cinema";
    }

}

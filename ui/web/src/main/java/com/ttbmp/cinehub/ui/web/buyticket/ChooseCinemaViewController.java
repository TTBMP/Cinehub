package com.ttbmp.cinehub.ui.web.buyticket;


import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketHandler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
            @ModelAttribute("getListCinemaRequest") GetListCinemaRequest request,
            Model model) {
        viewModel = new BuyTicketViewModel();
        useCase = new BuyTicketHandler(new BuyTicketPresenterWeb(viewModel));
        useCase.getListMovie(new GetListMovieRequest(LocalDate.parse(request.getData())));
        viewModel.getMovieDtoList().forEach(x -> {
            if (x.getId() == request.getMovieId()) {
                selectedMovieDto = x;
            }
        });
        viewModel.setSelectedMovie(selectedMovieDto);
        viewModel.setSelectedDate(request.getData());
        useCase.getListCinema(request);
        model.addAttribute("getListCinemaRequest",request);
        model.addAttribute("getCinemaRequest",new GetCinemaRequest(0));
        model.addAttribute("cinemaList", viewModel.getCinemaDtoList());
        return "choose_cinema";
    }



    @PostMapping("/choose_projection")
    public String getListSeat(
            @ModelAttribute("getCinemaRequest") GetCinemaRequest getCinemaRequest,
            Model model) {
        useCase.getListCinema(new GetListCinemaRequest(viewModel.getSelectedMovie().getId(), viewModel.getSelectedDate()));
        viewModel.getCinemaDtoList().forEach(x -> {
            if (x.getId() == getCinemaRequest.getIdCinema()) {
                selectedCinemaDto = x;
            }
        });
        viewModel.setSelectedCinema(selectedCinemaDto);
        useCase.getProjectionList(new GetProjectionRequest(
                viewModel.getSelectedMovie(),
                viewModel.getSelectedCinema(),
                LocalDate.parse(viewModel.getSelectedDate())
        ));
        model.addAttribute("getListSeatRequest", new GetListSeatRequest(0,"",0,0));
        model.addAttribute("projectionList", viewModel.getProjectionList());
        model.addAttribute("selectedDate", viewModel.getSelectedDate());
        model.addAttribute("movieId", viewModel.getSelectedMovie().getId());
        model.addAttribute("cinemaId", viewModel.getSelectedCinema().getId());
        return "/choose_cinema";
    }
}

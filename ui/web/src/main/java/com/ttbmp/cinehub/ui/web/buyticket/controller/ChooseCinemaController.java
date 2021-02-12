package com.ttbmp.cinehub.ui.web.buyticket.controller;


import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetListCinemaRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetListMovieRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetProjectionRequest;
import com.ttbmp.cinehub.ui.web.buyticket.BuyTicketViewModel;
import com.ttbmp.cinehub.ui.web.buyticket.UseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;


@Controller
public class ChooseCinemaController {

    private static final BuyTicketUseCase USE_CASE = UseCase.buyTicketUseCase;
    private final BuyTicketViewModel viewModel = UseCase.getViewModel();
    private MovieDto selectedMovieDto;
    private CinemaDto selectedCinemaDto;

    @GetMapping("/choose_cinema/{selected_date}/{movie_id}")
    public String chooseTimeOfProjection(
            @PathVariable("selected_date") String selectedDate,
            @PathVariable("movie_id") int id,
            Model model) {
        USE_CASE.getListMovie(new GetListMovieRequest(LocalDate.parse(selectedDate)));
        viewModel.getMovieDtoList().forEach(x->{
                    if(x.getId() == id){
                        selectedMovieDto =x;
                    }
                });
        viewModel.setSelectedMovie(selectedMovieDto);
        viewModel.setSelectedDate(selectedDate);
        USE_CASE.getListCinema(new GetListCinemaRequest(viewModel.getSelectedMovie(), viewModel.getSelectedDate()));
        model.addAttribute("cinemaList", viewModel.getCinemaDtoList());
        return "choose_cinema";
    }

    @GetMapping("/choose_cinema/{cinema_id}")
    public String getListSeat(
            @PathVariable("cinema_id") int id,
            Model model) {
        USE_CASE.getListCinema(new GetListCinemaRequest(viewModel.getSelectedMovie(),viewModel.getSelectedDate()));
        viewModel.getCinemaDtoList().forEach(x->{
            if(x.getId()==id){
                selectedCinemaDto = x;
            }
        });
        viewModel.setSelectedCinema(selectedCinemaDto);
        USE_CASE.getProjectionList(new GetProjectionRequest(
                viewModel.getSelectedMovie(),
                viewModel.getSelectedCinema(),
                LocalDate.parse(viewModel.getSelectedDate())
        ));
        model.addAttribute("projectionList", viewModel.getProjectionList());
        return "/choose_cinema";
    }

}

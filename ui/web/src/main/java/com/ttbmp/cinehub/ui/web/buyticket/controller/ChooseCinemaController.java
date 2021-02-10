package com.ttbmp.cinehub.ui.web.buyticket.controller;


import com.ttbmp.cinehub.app.datamapper.HallDataMapper;
import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.HallDto;
import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.app.repository.mock.MockHallRepository;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetListCinemaRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetTimeOfProjectionRequest;
import com.ttbmp.cinehub.ui.web.buyticket.BuyTicketViewModel;
import com.ttbmp.cinehub.ui.web.buyticket.UseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Controller
public class ChooseCinemaController {
    private static final BuyTicketUseCase USE_CASE = UseCase.buyTicketUseCase;
    private final BuyTicketViewModel viewModel = UseCase.getViewModel();



    @GetMapping("/choose_cinema/{movie_name}/{selected_date}/{movie_id}/{movie_releases}/{movie_vote}")
    public String chooseTimeOfProjection(
            @PathVariable("movie_name")String name,
            @PathVariable("selected_date")String selectedDate,
            @PathVariable("movie_id")int id,
            @PathVariable("movie_releases")String releases,
            @PathVariable("movie_vote")String vote,
            Model model) {
        MovieDto movieDto = new MovieDto(name);
        movieDto.setId(id);
        movieDto.setOverview("overview");
        movieDto.setReleases(releases);
        movieDto.setVote(vote);
        movieDto.setMovieUrl("imageUrl");
        viewModel.setSelectedMovie(movieDto);
        viewModel.setSelectedDate(selectedDate);
        USE_CASE.getListCinema(new GetListCinemaRequest(viewModel.getSelectedMovie(), viewModel.getSelectedDate()));
        model.addAttribute("cinemaList",viewModel.getCinemaDtoList());
        return "choose_cinema";
    }

    @GetMapping("/choose_cinema/{cinema_id}/{cinema_name}/{cinema_address}/{cinema_city}")
    public String getListSeat(
            @PathVariable("cinema_id") int id,
            @PathVariable("cinema_name") String name,
            @PathVariable("cinema_address") String address,
            @PathVariable("cinema_city") String city,
            Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        MockHallRepository hallRepository = new  MockHallRepository();
        List<HallDto> hallList = HallDataMapper.mapToDtoList(hallRepository.getHallList(id));
        LocalDate localDate = LocalDate.parse(viewModel.getSelectedDate(), formatter);
        viewModel.setSelectedCinema(new CinemaDto(
                id,
                name,
                address,
                city,
                hallList));
        USE_CASE.getProjectionList(new GetTimeOfProjectionRequest(
                viewModel.getSelectedMovie(),
                viewModel.getSelectedCinema(),
                localDate
        ));
        model.addAttribute("projectionList",viewModel.getProjectionList());
        return "/choose_cinema";
    }







}

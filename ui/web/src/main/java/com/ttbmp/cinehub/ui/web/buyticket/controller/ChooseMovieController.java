package com.ttbmp.cinehub.ui.web.buyticket.controller;



import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetListMovieRequest;
import com.ttbmp.cinehub.ui.web.buyticket.BuyTicketViewModel;
import com.ttbmp.cinehub.ui.web.buyticket.UseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Controller
public class ChooseMovieController {

private static final BuyTicketUseCase USE_CASE = UseCase.buyTicketUseCase;
private final BuyTicketViewModel viewModel = UseCase.getViewModel();
private String chooseDate = "choose_date";
private String chooseMovie = "choose_movie";
private String movieList = "movieList";

    @GetMapping("/choose_movie/{email}/{password}")
        public String chooseMovie(@PathVariable("email") String email,@PathVariable("password")  String password ,Model model) {
        model.addAttribute(chooseDate,LocalDate.now().toString());
        USE_CASE.getListMovie(new GetListMovieRequest(formatDate(LocalDate.now().toString())));
        model.addAttribute(movieList, viewModel.getMovieDtoList());
        return chooseMovie;
        }


    @GetMapping("/choose_movie")
    public String chooseMovie(Model model) {
        model.addAttribute(chooseDate,LocalDate.now().toString());
        USE_CASE.getListMovie(new GetListMovieRequest(formatDate(LocalDate.now().toString())));
        model.addAttribute(movieList, viewModel.getMovieDtoList());
        return chooseMovie;
    }



    @GetMapping("/choose_movie/{date}")
    public String getMovie(@PathVariable("date") String date, Model model){
        model.addAttribute(chooseDate,date);
        USE_CASE.getListMovie(new GetListMovieRequest(formatDate(date)));
        model.addAttribute(movieList, viewModel.getMovieDtoList());
        return chooseMovie;
    }

    private LocalDate formatDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        return LocalDate.parse(date, formatter);
    }
}


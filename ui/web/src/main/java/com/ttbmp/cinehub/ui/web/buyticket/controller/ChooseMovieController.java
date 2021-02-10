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
import java.util.ArrayList;
import java.util.List;


@Controller
public class ChooseMovieController {

private static final BuyTicketUseCase USE_CASE = UseCase.buyTicketUseCase;
private final BuyTicketViewModel viewModel = UseCase.getViewModel();
private static final String DATE_CHOOSE = "choose_date";

    @GetMapping("/choose_movie/{email}/{password}")
        public String chooseMovie(@PathVariable("email") String email,@PathVariable("password")  String password ,Model model) {
        model.addAttribute(DATE_CHOOSE,LocalDate.now().toString());
        model.addAttribute("dayList", generateTab());
        String date = (String) model.getAttribute(DATE_CHOOSE);
        USE_CASE.getListMovie(new GetListMovieRequest(formatDate(date)));
        model.addAttribute("movieList", viewModel.getMovieDtoList());
        return "choose_movie";
        }


    @GetMapping("/choose_movie")
    public String chooseMovie(Model model) {
        model.addAttribute(DATE_CHOOSE,LocalDate.now().toString());
        model.addAttribute("dayList", generateTab());
        String date = (String) model.getAttribute(DATE_CHOOSE);
        USE_CASE.getListMovie(new GetListMovieRequest(formatDate(date)));
        model.addAttribute("movieList", viewModel.getMovieDtoList());
        return "choose_movie";
    }



    @GetMapping("/choose_movie/{date}")
    public String getMovie(@PathVariable("date") String date, Model model){
        model.addAttribute(DATE_CHOOSE,date);
        model.addAttribute("dayList", generateTab());
        USE_CASE.getListMovie(new GetListMovieRequest(formatDate(date)));
        model.addAttribute("movieList", viewModel.getMovieDtoList());
        return "choose_movie";
    }

    private List<String> generateTab() {
        List<String> dayList = new ArrayList<>();
        for(int i=0;i<9;i++){
            String mounth = String.valueOf(LocalDate.now().getMonthValue());
            if(LocalDate.now().getMonthValue()<10){
                mounth = "0"+LocalDate.now().getMonthValue();
            }
            int year= LocalDate.now().getYear();
            int day=LocalDate.now().getDayOfMonth()+i;
            dayList.add(year+"-"+mounth+"-"+day);
        }
        return dayList;
    }

    private LocalDate formatDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        return LocalDate.parse(date, formatter);
    }
}


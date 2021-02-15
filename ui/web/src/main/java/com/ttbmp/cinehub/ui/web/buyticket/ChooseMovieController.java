package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketHandler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetListMovieRequest;
import com.ttbmp.cinehub.ui.web.buyticket.BuyTicketPresenterWeb;
import com.ttbmp.cinehub.ui.web.buyticket.BuyTicketViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Palmieri Ivan
 */
@Controller
public class ChooseMovieController {

    private static final String CHOOSE_DATE = "choose_date";
    private static final String CHOOSE_MOVIE = "choose_movie";
    private static final String MOVIE_LIST = "movieList";
    private BuyTicketViewModel viewModel;
    private BuyTicketUseCase useCase;

    @GetMapping("/choose_movie")
    public String chooseMovie(Model model) {
        viewModel = new BuyTicketViewModel();
        useCase = new BuyTicketHandler(new BuyTicketPresenterWeb(viewModel));
        model.addAttribute(CHOOSE_DATE, LocalDate.now().toString());
        useCase.getListMovie(new GetListMovieRequest(formatDate(LocalDate.now().toString())));
        model.addAttribute(MOVIE_LIST, viewModel.getMovieDtoList());
        return CHOOSE_MOVIE;
    }

    @GetMapping("/choose_movie/{date}")
    public String getMovie(@PathVariable("date") String date, Model model) {
        model.addAttribute(CHOOSE_DATE, date);
        useCase.getListMovie(new GetListMovieRequest(formatDate(date)));
        model.addAttribute(MOVIE_LIST, viewModel.getMovieDtoList());
        return CHOOSE_MOVIE;
    }

    private LocalDate formatDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        return LocalDate.parse(date, formatter);
    }

}

package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketHandler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetListCinemaRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetListMovieRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Palmieri Ivan
 */
@Controller
public class ChooseMovieViewController {

    private static final String CHOOSE_DATE = "choose_date";
    private static final String CHOOSE_MOVIE = "choose_movie";
    private static final String MOVIE_LIST = "movieList";
    private BuyTicketViewModel viewModel;
    private BuyTicketUseCase useCase;

    @GetMapping("/choose_movie")
    public String chooseMovie(Model model) {
        viewModel = new BuyTicketViewModel();
        useCase = new BuyTicketHandler(new BuyTicketPresenterWeb(viewModel));
        GetListMovieRequest request = new GetListMovieRequest(LocalDate.now());
        model.addAttribute(CHOOSE_DATE, LocalDate.now().toString());
        useCase.getListMovie(request);
        model.addAttribute(MOVIE_LIST, viewModel.getMovieDtoList());
        GetListCinemaRequest getListCinemaRequest = new GetListCinemaRequest(0,null);
        model.addAttribute("getListCinemaRequest", getListCinemaRequest);
        model.addAttribute("getListMovieRequest", request);
        return CHOOSE_MOVIE;
    }


    @PostMapping("/choose_movie")
    public String getMoviePost(@ModelAttribute("getListMovieRequest") GetListMovieRequest getListMovieRequest, Model model) {
        useCase.getListMovie(getListMovieRequest);
        model.addAttribute(MOVIE_LIST, viewModel.getMovieDtoList());
        model.addAttribute(CHOOSE_DATE, getListMovieRequest.getDate().toString());
        model.addAttribute("getListMovieRequest", getListMovieRequest);
        GetListCinemaRequest getListCinemaRequest = new GetListCinemaRequest(0,null);
        model.addAttribute("getListCinemaRequest", getListCinemaRequest);
        return CHOOSE_MOVIE;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text != null)
                    setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
        });
    }


}

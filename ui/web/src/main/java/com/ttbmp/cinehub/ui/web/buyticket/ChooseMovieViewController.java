package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketHandler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetListMovieRequest;
import com.ttbmp.cinehub.ui.web.domain.Projection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
        model.addAttribute(CHOOSE_DATE, LocalDate.now());
        useCase.getListMovie(new GetListMovieRequest(LocalDate.now()));
        model.addAttribute(MOVIE_LIST, viewModel.getMovieDtoList());
        model.addAttribute("projection", new Projection());
        return CHOOSE_MOVIE;
    }



    @PostMapping("/choose_movie")
    public String getMoviePost(@ModelAttribute("projection") Projection projection, Model model) {
        useCase.getListMovie(new GetListMovieRequest(LocalDate.parse(projection.getDate())));
        model.addAttribute(MOVIE_LIST, viewModel.getMovieDtoList());
        model.addAttribute(CHOOSE_DATE, projection.getDate());
        model.addAttribute("projection", projection);
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

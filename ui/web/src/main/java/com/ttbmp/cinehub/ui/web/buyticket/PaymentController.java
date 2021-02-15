package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketHandler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

/**
 * @author Palmieri Ivan
 */
@Controller
public class PaymentController {
    private ProjectionDto selectedProjection;
    private MovieDto selectedMovie;
    private CinemaDto selectedCinema;
    private BuyTicketViewModel viewModel;
    private BuyTicketUseCase useCase;


    @GetMapping("/payment/{position}/{index}/{hall_id}/{selected_date}/{movie_id}/{cinema_id}/{optionOne}/{optionTwo}/{optionThree}")
    public String payment(
            @PathVariable("position") String position,
            @PathVariable("index") Integer number,
            @PathVariable("optionOne") Boolean optionOne,
            @PathVariable("optionTwo") Boolean optionTwo,
            @PathVariable("optionThree") Boolean optionThree,
            @PathVariable("hall_id") int hallId,
            @PathVariable("selected_date") String date,
            @PathVariable("movie_id") int movieId,
            @PathVariable("cinema_id") int cinemaId,
            Model model) {
        viewModel = new BuyTicketViewModel();
        viewModel.setSelectedPosition(number);
        useCase = new BuyTicketHandler(new BuyTicketPresenterWeb(viewModel));


        useCase.getListMovie(new GetListMovieRequest(LocalDate.parse(date)));
        viewModel.getMovieDtoList().forEach(movie -> {
            if (movie.getId() == movieId) {
                selectedMovie = movie;
            }
        });

        useCase.getListCinema(new GetListCinemaRequest(selectedMovie, date));
        viewModel.getCinemaDtoList().forEach(cinema -> {
            if (cinema.getId() == cinemaId) {
                selectedCinema = cinema;
            }
        });

        useCase.getProjectionList(new GetProjectionRequest(selectedMovie, selectedCinema, LocalDate.parse(date)));
        viewModel.getProjectionList().forEach(projection -> {
            if (projection.getHallDto().getId() == hallId) {
                selectedProjection = projection;
            }
        });

        useCase.createTicket(new GetTicketBySeatsRequest(selectedProjection.getHallDto().getSeatList(), position, number, optionOne, optionTwo, optionThree));
        viewModel.setSelectedCinema(selectedCinema);
        viewModel.setSelectedProjection(selectedProjection);
        viewModel.setSelectedDate(date);
        viewModel.setSelectedPosition(number);
        viewModel.setSelectedMovie(selectedMovie);
        return "payment";
    }

    @GetMapping("/payment")
    public String payment(Model model) {
        useCase.pay(new PayRequest(viewModel.getSelectedTicket(),
                viewModel.getSelectedProjection(),
                viewModel.getSelectedPosition(),
                viewModel.getSelectedCinema(),
                viewModel.getSelectedMovie(),
                viewModel.getSelectedDate()));
        if (viewModel.getPaymentError() == null) {
            return "confirm_email";
        }
        model.addAttribute("errorPayment", "There are an error with the payment, try to late...");
        return "payment";
    }


}

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

/**
 * @author Palmieri Ivan
 */
@Controller
public class PaymentViewController {
    private ProjectionDto selectedProjection;
    private MovieDto selectedMovie;
    private CinemaDto selectedCinema;
    private BuyTicketViewModel viewModel;
    private BuyTicketUseCase useCase;


    @PostMapping("/payment/{optionOne}/{optionTwo}/{optionThree}")
    public String payment(
            @ModelAttribute("paymentRequest") PaymentRequest paymentRequest,
            @PathVariable("optionOne") boolean optionOne,
            @PathVariable("optionTwo") boolean optionTwo,
            @PathVariable("optionThree") boolean optionThree,
            Model model) {
        viewModel = new BuyTicketViewModel();
        viewModel.setSelectedPosition(paymentRequest.getNumber());
        useCase = new BuyTicketHandler(new BuyTicketPresenterWeb(viewModel));
        useCase.getListMovie(new GetListMovieRequest(LocalDate.parse(paymentRequest.getDate())));
        viewModel.getMovieDtoList().forEach(movie -> {
            if (movie.getId() == paymentRequest.getMovieId()) {
                selectedMovie = movie;
            }
        });
        useCase.getListCinema(new GetListCinemaRequest(selectedMovie.getId(), paymentRequest.getDate()));
        viewModel.getCinemaDtoList().forEach(cinema -> {
            if (cinema.getId() == paymentRequest.getCinemaId()) {
                selectedCinema = cinema;
            }
        });

        useCase.getProjectionList(new GetProjectionRequest(selectedMovie, selectedCinema, LocalDate.parse(paymentRequest.getDate())));
        viewModel.getProjectionList().forEach(projection -> {
            if (projection.getHallDto().getId() == paymentRequest.getHallId()) {
                selectedProjection = projection;
            }
        });

        useCase.createTicket(new GetTicketBySeatsRequest(
                selectedProjection.getHallDto().getSeatList(),
                paymentRequest.getPosition(),
                paymentRequest.getNumber(),
                optionOne,
                optionTwo,
                optionThree
        ));
        viewModel.setSelectedCinema(selectedCinema);
        viewModel.setSelectedProjection(selectedProjection);
        viewModel.setSelectedDate(paymentRequest.getDate());
        viewModel.setSelectedPosition(paymentRequest.getNumber());
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

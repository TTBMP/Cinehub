package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.app.usecase.buyticket.Handler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;
import com.ttbmp.cinehub.ui.web.domain.Payment;
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
    private BuyTicketUseCase buyTicketUseCase;


    @PostMapping("/payment/{optionOne}/{optionTwo}/{optionThree}")
    public String payment(
            @ModelAttribute("payment") Payment payment,
            @PathVariable("optionOne") boolean optionOne,
            @PathVariable("optionTwo") boolean optionTwo,
            @PathVariable("optionThree") boolean optionThree,
            Model model) {
        viewModel = new BuyTicketViewModel();
        viewModel.setSelectedPosition(payment.getNumber());
     //   useCase = new BuyTicketHandler(new BuyTicketPresenterWeb(viewModel));
        buyTicketUseCase = new Handler(new BuyTicketPresenterWeb(model));
        buyTicketUseCase.getListMovie(new GetListMovieRequest(LocalDate.parse(payment.getDate())));
        viewModel.getMovieDtoList().forEach(movie -> {
            if (movie.getId() == payment.getMovieId()) {
                selectedMovie = movie;
            }
        });
        buyTicketUseCase.getListCinema(new GetListCinemaRequest(selectedMovie.getId(), payment.getDate()));
        viewModel.getCinemaDtoList().forEach(cinema -> {
            if (cinema.getId() == payment.getCinemaId()) {
                selectedCinema = cinema;
            }
        });

        buyTicketUseCase.getProjectionList(new GetProjectionRequest(selectedMovie.getId(), selectedCinema.getId(), LocalDate.parse(payment.getDate())));
        viewModel.getProjectionList().forEach(projection -> {
            if (projection.getHallDto().getId() == payment.getHallId()) {
                selectedProjection = projection;
            }
        });

        buyTicketUseCase.createTicket(new GetTicketBySeatsRequest(
                selectedProjection.getHallDto().getSeatList(),
                payment.getPosition(),
                payment.getNumber(),
                optionOne,
                optionTwo,
                optionThree
        ));
        viewModel.setSelectedCinema(selectedCinema);
        viewModel.setSelectedProjection(selectedProjection);
        viewModel.setSelectedDate(payment.getDate());
        viewModel.setSelectedPosition(payment.getNumber());
        viewModel.setSelectedMovie(selectedMovie);
        return "payment";
    }



    @GetMapping("/payment")
    public String payment(Model model) {
        buyTicketUseCase.pay(new PaymentRequest(viewModel.getSelectedTicket(),
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

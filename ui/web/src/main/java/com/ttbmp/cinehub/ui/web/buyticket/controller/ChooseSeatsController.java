package com.ttbmp.cinehub.ui.web.buyticket.controller;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.app.dto.TicketDto;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketHandler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetListCinemaRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetListMovieRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetNumberOfSeatsRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetProjectionRequest;
import com.ttbmp.cinehub.ui.web.buyticket.BuyTicketPresenterWeb;
import com.ttbmp.cinehub.ui.web.buyticket.BuyTicketViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
@Controller
public class ChooseSeatsController {

    private BuyTicketViewModel viewModel;
    private ProjectionDto selectedProjection;
    private MovieDto selectedMovie;
    private CinemaDto selectedCinema;


    @GetMapping("/choose_seats/{hall_id}/{selected_date}/{movie_id}/{cinema_id}")
    public String chooseSeats(
            @PathVariable("hall_id") int hallId,
            @PathVariable("selected_date") String date,
            @PathVariable("movie_id") int movieId,
            @PathVariable("cinema_id") int cinemaId,
            Model model) {
        viewModel = new BuyTicketViewModel();
        BuyTicketUseCase useCase = new BuyTicketHandler(new BuyTicketPresenterWeb(viewModel));
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

        viewModel.setSelectedProjection(selectedProjection);
        useCase.getListOfSeat(new GetNumberOfSeatsRequest(viewModel.getSelectedProjection()));

        viewModel.setSeatList(viewModel.getSeatDtoList());
        model.addAttribute("projection", viewModel.getSelectedProjection());
        model.addAttribute("seatList", viewModel.getSeatList());
        model.addAttribute("boolean1", false);
        model.addAttribute("boolean2", false);
        model.addAttribute("boolean3", false);
        model.addAttribute("selectedDate", date);
        model.addAttribute("movieId", selectedMovie.getId());
        model.addAttribute("cinemaId", selectedCinema.getId());
        model.addAttribute("hallId", selectedProjection.getHallDto().getId());
        model.addAttribute("color", "color:" + "white");//per cambiargli colore
        model.addAttribute("classValue", "material-icons");//per impostarlo disabilitato
        addNameAtSeats(model);
        return "choose_seats";
    }

    private void addNameAtSeats(Model model) {
        int size = (viewModel.getSeatList()).size();
        int rows = 7;
        int columns = (size / rows);
        int rest = size % rows;
        int counter = 0;
        List<String> valList = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                addName(valList, counter);
                counter++;
            }
        }
        for (int k = 0; k < rest; k++) {
            addRestartName(valList, counter);
            counter++;
        }
        model.addAttribute("valList", valList);
    }

    private void addRestartName(List<String> valList, int counter) {
        if (!viewModel.getSelectedProjection().getListTicket().isEmpty()) {
            List<TicketDto> ticketDtoList = viewModel.getSelectedProjection().getListTicket();
            boolean count = false;
            for (TicketDto ticket : ticketDtoList) {
                String val = selectedProjection.getHallDto().getSeatList().get(counter).getPosition();
                if (ticket.getSeatDto().getPosition().equals(val)) {
                    count = true;
                    valList.add("SOLD");

                }
            }
            if (!count) {
                valList.add(selectedProjection.getHallDto().getSeatList().get(counter).getPosition());

            }
        } else {
            valList.add(selectedProjection.getHallDto().getSeatList().get(counter).getPosition());

        }

    }


    private void addName(List<String> valList, int counter) {

        if (!viewModel.getSelectedProjection().getListTicket().isEmpty()) {
            List<TicketDto> ticketDtoList = viewModel.getSelectedProjection().getListTicket();
            boolean count = false;
            for (TicketDto ticket : ticketDtoList) {
                String val = selectedProjection.getHallDto().getSeatList().get(counter).getPosition();
                if (ticket.getSeatDto().getPosition().equals(val)) {
                    count = true;
                    valList.add("SOLD");

                }
            }
            if (!count) {
                valList.add(selectedProjection.getHallDto().getSeatList().get(counter).getPosition());
            }
        } else {
            valList.add(selectedProjection.getHallDto().getSeatList().get(counter).getPosition());

        }
    }


}

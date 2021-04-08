package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.app.dto.TicketDto;
import com.ttbmp.cinehub.app.usecase.buyticket.Handler;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetListCinemaRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetListMovieRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetNumberOfSeatsRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetProjectionRequest;
import com.ttbmp.cinehub.ui.web.domain.Payment;
import com.ttbmp.cinehub.ui.web.domain.Projection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
@Controller
public class ChooseSeatsViewController {

    private BuyTicketViewModel viewModel;
    private ProjectionDto selectedProjection;
    private MovieDto selectedMovie;
    private CinemaDto selectedCinema;


    @PostMapping("/choose_seat")
    public String chooseSeats(
            @ModelAttribute("projection") Projection projection,
            Model model) {
        viewModel = new BuyTicketViewModel();
        //BuyTicketUseCase useCase = new BuyTicketHandler(new BuyTicketPresenterWeb(viewModel));
        BuyTicketUseCase buyTicketUseCase = new Handler(new BuyTicketPresenterWeb(model));

        buyTicketUseCase.getListMovie(new GetListMovieRequest(LocalDate.parse(projection.getDate())));
        viewModel.getMovieDtoList().forEach(movieDto -> {
            if (movieDto.getId() == projection.getMovieId()) {
                selectedMovie = movieDto;
            }
        });

        buyTicketUseCase.getListCinema(new GetListCinemaRequest(selectedMovie.getId(), projection.getDate()));
        viewModel.getCinemaDtoList().forEach(cinemaDto -> {
            if (cinemaDto.getId() == projection.getCinemaId()) {
                selectedCinema = cinemaDto;
            }
        });


        buyTicketUseCase.getProjectionList(new GetProjectionRequest(selectedMovie.getId(), selectedCinema.getId(), LocalDate.parse(projection.getDate())));
        viewModel.getProjectionList().forEach(projectionDto -> {
            if (projectionDto.getHallDto().getId() == projection.getHallId()) {
                selectedProjection = projectionDto;
            }
        });

        viewModel.setSelectedProjection(selectedProjection);

       //TODO buyTicketUseCase.getListOfSeat(new GetNumberOfSeatsRequest(projection.getMovieId(), projection.getCinemaId(), LocalDate.parse(projection.getDate()));

        buyTicketUseCase.getListOfSeat(new GetNumberOfSeatsRequest(viewModel.getSelectedProjection()));

        viewModel.setSeatList(viewModel.getSeatDtoList());
        model.addAttribute("projection", viewModel.getSelectedProjection());
        model.addAttribute("seatList", viewModel.getSeatList());
        model.addAttribute("boolean1", false);
        model.addAttribute("boolean2", false);
        model.addAttribute("boolean3", false);
        model.addAttribute("selectedDate", projection.getDate());
        model.addAttribute("movieId", selectedMovie.getId());
        model.addAttribute("cinemaId", selectedCinema.getId());
        model.addAttribute("hallId", selectedProjection.getHallDto().getId());
        model.addAttribute("color", "color:" + "white");
        model.addAttribute("classValue", "material-icons");
        model.addAttribute("payment", new Payment());
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

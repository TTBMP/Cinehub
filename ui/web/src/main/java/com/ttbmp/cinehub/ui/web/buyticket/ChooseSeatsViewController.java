package com.ttbmp.cinehub.ui.web.buyticket;

import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.app.dto.TicketDto;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.Handler;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetNumberOfSeatsRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetProjectionRequest;
import com.ttbmp.cinehub.ui.web.domain.Projection;
import com.ttbmp.cinehub.ui.web.domain.Seat;
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

    private final String projectionList="projectionList";

    @PostMapping("/choose_seat")
    public String chooseSeats(@ModelAttribute("projection") Projection projection,
            Model model) {

        BuyTicketUseCase buyTicketUseCase = new Handler(new BuyTicketPresenterWeb(model));
        buyTicketUseCase.getProjectionList(new GetProjectionRequest(
                projection.getMovieId(),
                projection.getCinemaId(),
                LocalDate.parse(projection.getDate()),
                projection.getStartTime(),
                projection.getHallId()
        ));
        buyTicketUseCase.getListOfSeat(new GetNumberOfSeatsRequest(((ArrayList<ProjectionDto>)model.getAttribute(projectionList)).get(0))); //Mi ritorna sempre una lista
        model.addAttribute("boolean1", false);
        model.addAttribute("boolean2", false);
        model.addAttribute("boolean3", false);
        model.addAttribute("color", "color:" + "white");
        model.addAttribute("classValue", "material-icons");
        model.addAttribute("selectedDate", projection.getDate());
        model.addAttribute("movieId", projection.getMovieId());
        model.addAttribute("cinemaId", projection.getCinemaId());
        model.addAttribute("hallId", projection.getHallId());
        model.addAttribute("seat", new Seat());
        addNameAtSeats(model);
        return "choose_seats";
    }


    private void addNameAtSeats(Model model) {
        int size = (int) model.getAttribute("sizeSeatList");
        int rows = 7;
        int columns = (size / rows);
        int rest = size % rows;
        int counter = 0;
        List<String> valList = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                addName(valList, counter,model);
                counter++;
            }
        }
        for (int k = 0; k < rest; k++) {
            addName(valList, counter,model);
            counter++;
        }
        model.addAttribute("valList", valList);
    }


    private void addName(List<String> valList, int counter,Model model) {
        ProjectionDto selectedProjection = ((ArrayList<ProjectionDto>)model.getAttribute(projectionList)).get(0);
        if (!(selectedProjection.getListTicket().isEmpty())) {
            List<TicketDto> ticketDtoList = selectedProjection.getListTicket();
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

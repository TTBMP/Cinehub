package com.ttbmp.cinehub.ui.web.buyticket.controller;

import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.app.dto.TicketDto;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetNumberOfSeatsRequest;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetProjectionRequest;
import com.ttbmp.cinehub.ui.web.buyticket.BuyTicketViewModel;
import com.ttbmp.cinehub.ui.web.buyticket.UseCase;
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

    private static final BuyTicketUseCase USE_CASE = UseCase.buyTicketUseCase;
    private final BuyTicketViewModel viewModel = UseCase.getViewModel();
    private  ProjectionDto selectedProjection;



    @GetMapping("/choose_seats/{projection_id}")
    public String chooseSeats(
            @PathVariable("projection_id") int id,
            Model model) {

        USE_CASE.getProjectionList(new GetProjectionRequest(viewModel.getSelectedMovie(), viewModel.getSelectedCinema(), LocalDate.parse(viewModel.getSelectedDate())));
        viewModel.getProjectionList().forEach(x->{
            if(x.getHallDto().getId()==id){
                selectedProjection = x;
            }
        });
        viewModel.setSelectedProjection(selectedProjection);
        USE_CASE.getListOfSeat(new GetNumberOfSeatsRequest(viewModel.getSelectedProjection()));
        viewModel.setSeatList(viewModel.getSeatDtoList());
        model.addAttribute("projection", viewModel.getSelectedProjection());
        model.addAttribute("seatList", viewModel.getSeatList());
        model.addAttribute("boolean1", false);
        model.addAttribute("boolean2", false);
        model.addAttribute("boolean3", false);
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
        List<String> valList = new ArrayList<>();
        char[] a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'Z'};
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                addName(valList,a,j,i);
            }
        }
        for (int k = 0; k < rest; k++) {
           addRestartName(a,columns,valList,k);
        }
        model.addAttribute("valList", valList);
    }

    private void addRestartName(char[] a, int columns, List<String> valList, int k) {
        if(!viewModel.getSelectedProjection().getTicketList().isEmpty()) {
            List<TicketDto> ticketDtoList = viewModel.getSelectedProjection().getTicketList();
            boolean count = false;
            for(TicketDto ticket: ticketDtoList){
                String val = a[columns + 1] + String.valueOf(k);
                if (ticket.getPosition().equals(val) ) {
                    count=true;
                    valList.add("SOLD");
                }
            }
            if(!count){
                valList.add(String.valueOf(a[columns + 1]) + k);
            }
        }
        else{
            valList.add(String.valueOf(a[columns + 1]) + k);
        }

    }




    private void addName(List<String> valList, char[] a, int j, int i) {
        if(!viewModel.getSelectedProjection().getTicketList().isEmpty()) {
            List<TicketDto> ticketDtoList = viewModel.getSelectedProjection().getTicketList();
            boolean count = false;
            for(TicketDto ticket: ticketDtoList){
                String val = (a[i]) + String.valueOf(j);
                if (ticket.getPosition().equals(val) ) {
                    count=true;
                    valList.add("SOLD");
                }
            }
            if(!count){
                valList.add(String.valueOf(a[i]) + j);
            }
        }
        else{
            valList.add(String.valueOf(a[i]) + j);
        }
    }


}

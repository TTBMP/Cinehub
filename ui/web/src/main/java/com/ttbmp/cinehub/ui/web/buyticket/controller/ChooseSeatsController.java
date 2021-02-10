package com.ttbmp.cinehub.ui.web.buyticket.controller;


import com.ttbmp.cinehub.app.datamapper.HallDataMapper;
import com.ttbmp.cinehub.app.dto.HallDto;
import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.app.dto.SeatDto;
import com.ttbmp.cinehub.app.repository.HallRepository;
import com.ttbmp.cinehub.app.repository.mock.MockHallRepository;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketUseCase;
import com.ttbmp.cinehub.app.usecase.buyticket.request.GetNumberOfSeatsRequest;
import com.ttbmp.cinehub.ui.web.buyticket.BuyTicketViewModel;
import com.ttbmp.cinehub.ui.web.buyticket.UseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChooseSeatsController {

    private static final BuyTicketUseCase USE_CASE = UseCase.buyTicketUseCase;
    private final BuyTicketViewModel viewModel = UseCase.getViewModel();
    String color ="white";



    @GetMapping("/choose_seats/{startTime}/{hall_id}")
    public String chooseSeats(
            @PathVariable("startTime")String startTime,
            @PathVariable("hall_id")Integer hall,
            Model model
    ) {
        MockHallRepository hallRepository = new MockHallRepository();
        HallDto hallDto = HallDataMapper.mapToDto(hallRepository.getHall(hall)) ;
        viewModel.setSelectedHall(hallDto);
        viewModel.setSelectedTime(startTime);
        viewModel.setSelectedProjection(new ProjectionDto(
                viewModel.getSelectedMovie(),
                viewModel.getSelectedCinema(),
                viewModel.getSelectedHall(),
                viewModel.getSelectedTime(),
                viewModel.getSelectedDate()
        ));

        USE_CASE.getListOfSeat(new GetNumberOfSeatsRequest(viewModel.getSelectedProjection()));
        viewModel.setSeatList(viewModel.getSeatDtoList());
        model.addAttribute("projection", viewModel.getSelectedProjection());
        model.addAttribute("seatList", viewModel.getSeatList());
        model.addAttribute("boolean1",false);
        model.addAttribute("boolean2",false);
        model.addAttribute("boolean3",false);

        model.addAttribute("color","color:"+color);//per cambiargli colore
        model.addAttribute("classValue","material-icons");//per impostarlo disabilitato
        addNameAtSeat(model);
        return "choose_seats";
    }

    private void addNameAtSeat(Model model) {
        int size = (viewModel.getSeatList()).size();
        int rows = 10;
        int columns = (size / rows);
        int rest = size % rows;
        List<String> valList = new ArrayList<>();
        char[] a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'Z'};
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                valList.add(String.valueOf(a[i])+j);
            }
        }
        for (int k = 0; k < rest; k++) {
            valList.add(String.valueOf(a[columns + 1])+k);
        }
        model.addAttribute("valList",valList);
    }


}

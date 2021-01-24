package com.ttbmp.cinehub.app.client.desktop.ui.buyticket;


import com.ttbmp.cinehub.core.usecase.buyticket.*;
import javafx.scene.control.RadioButton;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class BuyTicketPresenterFx implements BuyTicketPresenter {


    private final BuyTicketViewModel viewModel;

    public BuyTicketPresenterFx(BuyTicketViewModel viewModel) {
        this.viewModel = viewModel;
    }


    @Override
    public void presentMovieApiList(GetListMovieResponse response) {
        viewModel.getMovieList().clear();
        if (viewModel.selectedDateProperty().getValue().equals(LocalDate.now())) {
            viewModel.getMovieList().addAll(response.getMovieList().subList(0, 5));
        } else {
            viewModel.getMovieList().addAll(response.getMovieList().subList(5, 10));
        }
    }

    @Override
    public void presentCinemaList(GetListCinemaResponse response) {
        viewModel.getCinemaList().clear();
        viewModel.getCinemaList().addAll(response.getCinemaList());
    }

    @Override
    public void presentTimeList(List<String> timeOfProjectionList) {
        viewModel.getTimeOfProjectionList().clear();
        viewModel.getTimeOfProjectionList().addAll(timeOfProjectionList);
    }

    @Override
    public void presentSeatList(GetNumberOfSeatsResponse response) {
        viewModel.getSeatList().clear();
        viewModel.getSeatList().addAll(response.getSeatDtoList());
    }

    @Override
    public void presentProjection(SetProjectionResponse response) {
        viewModel.selectedProjectionProperty().setValue(response.getProjectionDto());
    }


    @Override
    public void confirmSeatsRandom() {
        int i = 0;
        int j = 0;
        while (j == 0) {
            if (((RadioButton) viewModel.getGroup().getToggles().get(i)).isDisabled()) {
                i++;
            } else {
                j = 1;
            }
        }
        RadioButton radioSelect = (RadioButton) viewModel.getGroup().getToggles().get(i);
        String position = radioSelect.getText();
        viewModel.selectedSeatsProperty().setValue(position);
        viewModel.selectedPositionSeatIntegerProperty().setValue(i);


    }


    @Override
    public void setSelectedTicket(GetTicketBySeatsResponse response) {
        viewModel.selectedTicketProperty().setValue(response.getTicketDto());
    }


    @Override
    public void presentInvalidSendEmail(SendEmailRequest request) {
        System.out.println(SendEmailRequest.MISSING_RECIPIENT_ERROR.getMessage());
    }

    @Override//lanciano messaaggio sulla view, cioè viewModel e poi sulla view
    public void presentSendEmailNullRequest() {
        System.out.println("La richiesta SendEmail non può essere null");
    }

    @Override
    public void presentPayNullRequest() {
        System.out.println("La richiesta Pay non può essere null");
    }

    @Override
    public void presentInvalidPay(PayRequest request) {
        if (request.getErrorList().contains(PayRequest.MISSING_INDEX_ERROR)) {
            System.out.println(PayRequest.MISSING_INDEX_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PayRequest.MISSING_TICKET_ERROR)) {
            System.out.println(PayRequest.MISSING_TICKET_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PayRequest.MISSING_PROJECTION_ERROR)) {
            System.out.println(PayRequest.MISSING_PROJECTION_ERROR.getMessage());
        }
    }

    @Override
    public void presentGetTicketBySeatsNullRequest() {
        System.out.println("La richiesta GetTicketBySeats non può essere null");
    }

    @Override
    public void presentInvalidGetTicketBySeats(GetTicketBySeatsRequest request) {
        if (request.getErrorList().contains(GetTicketBySeatsRequest.MISSING_LIST_SEATS_ERROR)) {
            System.out.println(GetTicketBySeatsRequest.MISSING_LIST_SEATS_ERROR.getMessage());
        }
        if (request.getErrorList().contains(GetTicketBySeatsRequest.MISSING_POSITION_ERROR)) {
            System.out.println(GetTicketBySeatsRequest.MISSING_POSITION_ERROR.getMessage());
        }
        if (request.getErrorList().contains(GetTicketBySeatsRequest.MISSING_INDEX_ERROR)) {
            System.out.println(GetTicketBySeatsRequest.MISSING_INDEX_ERROR.getMessage());
        }
    }

    @Override
    public void presentGetListCinemaNullRequest() {
        System.out.println("La richiesta GetListCinema non può essere null");
    }

    @Override
    public void presentInvalidGetListCinema(GetListCinemaRequest request) {
        System.out.println(GetListCinemaRequest.MISSING_CINEMA_ERROR.getMessage());
    }

    @Override
    public void presentGetTimeOfProjectionNullRequest() {
        System.out.println("La richiesta GetTimeOfProjection non può essere null");

    }

    @Override
    public void presentInvalidGetTimeOfProjection(GetTimeOfProjecitonRequest request) {
        if (request.getErrorList().contains(GetTimeOfProjecitonRequest.MISSING_MOVIE_ERROR)) {
            System.out.println(GetTimeOfProjecitonRequest.MISSING_MOVIE_ERROR.getMessage());
        }
        if (request.getErrorList().contains(GetTimeOfProjecitonRequest.MISSING_CINEMA_ERROR)) {
            System.out.println(GetTimeOfProjecitonRequest.MISSING_CINEMA_ERROR.getMessage());
        }
    }

    @Override
    public void presentSetProjectionNullRequest() {
        System.out.println("La richiesta SetProjection non può essere null");

    }

    @Override
    public void presentInvalidSetProjection(SetProjectionRequest request) {
        if (request.getErrorList().contains(SetProjectionRequest.MISSING_CINEMA_ERROR)) {
            System.out.println(SetProjectionRequest.MISSING_CINEMA_ERROR.getMessage());
        }
        if (request.getErrorList().contains(SetProjectionRequest.MISSING_MOVIE_ERROR)) {
            System.out.println(SetProjectionRequest.MISSING_MOVIE_ERROR.getMessage());
        }
        if (request.getErrorList().contains(SetProjectionRequest.MISSING_TIME_ERROR)) {
            System.out.println(SetProjectionRequest.MISSING_TIME_ERROR.getMessage());
        }
    }

    @Override
    public void presentGetNumberOfSeatsNullRequest() {
        System.out.println("La richiesta GetNumberOfSeats non può essere null");

    }

    @Override
    public void presentInvalidGetNumberOfSeats(GetNumberOfSeatsRequest request) {
        System.out.println(GetNumberOfSeatsRequest.MISSING_PROJECTION_ERROR.getMessage());

    }


    @Override
    public void confirmSeatsSpecific() {
        //Reperisco la stringa associata al radioButton
        String position = viewModel.getGroup().getSelectedToggle()
                .toString()
                .substring(viewModel.getGroup().getSelectedToggle()
                        .toString().length() - 3, viewModel.getGroup()
                        .getSelectedToggle()
                        .toString()
                        .length() - 1);
        int i = 0;
        int j = 0;
        while (j == 0) {
            String text = ((RadioButton) viewModel.getGroup().getToggles().get(i)).getText();
            if (text.equals(position)) {
                j = 1;
            } else {
                i++;
            }
        }
        viewModel.selectedSeatsProperty().setValue(position);
        viewModel.selectedPositionSeatIntegerProperty().setValue(i);


    }


}

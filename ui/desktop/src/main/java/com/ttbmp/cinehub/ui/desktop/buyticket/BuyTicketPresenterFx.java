package com.ttbmp.cinehub.ui.desktop.buyticket;


import com.ttbmp.cinehub.app.service.payment.PaymentServiceException;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketPresenter;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;
import com.ttbmp.cinehub.app.usecase.buyticket.response.*;

/**
 * @author Ivan Palmieri
 */
public class BuyTicketPresenterFx implements BuyTicketPresenter {


    private final BuyTicketViewModel viewModel;

    public BuyTicketPresenterFx(BuyTicketViewModel viewModel) {
        this.viewModel = viewModel;
    }


    @Override
    public void presentMovieApiList(MovieListResponse response) {
        viewModel.getMovieList().clear();
        viewModel.getMovieList().addAll(response.getMovieList());
    }

    @Override
    public void presentCinemaList(CinemaListResponse response) {
        viewModel.getCinemaList().setAll(response.getCinemaList());
    }

    @Override
    public void presentCinema(CinemaResponse response) {
        viewModel.selectedCinemaNameProperty();
    }


    @Override
    public void presentSeatList(NumberOfSeatsResponse response) {
        viewModel.getSeatList().setAll(response.getSeatDtoList());
    }


    @Override
    public void setSelectedTicket(TicketResponse response) {
        viewModel.selectedTicketProperty().setValue(response.getTicketDto());
        viewModel.selectedSeatPriceProperty().setValue(response.getTicketDto().getPrice().toString());
    }


    @Override
    public void presentPayNullRequest() {
        viewModel.paymentErrorProperty().setValue("Error with operation Pay");
    }

    @Override
    public void presentInvalidPay(PaymentRequest request) {
        if (request.getErrorList().contains(PaymentRequest.MISSING_TICKET_ERROR)) {
            viewModel.paymentErrorProperty().setValue(PaymentRequest.MISSING_TICKET_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.MISSING_PROJECTION_ERROR)) {
            viewModel.paymentErrorProperty().setValue(PaymentRequest.MISSING_PROJECTION_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.MISSING_CINEMA_ERROR)) {
            viewModel.paymentErrorProperty().setValue(PaymentRequest.MISSING_CINEMA_ERROR.getMessage());
        }
        if (request.getErrorList().contains(PaymentRequest.MISSING_CREDIT_CARD_ERROR)) {
            viewModel.paymentErrorProperty().setValue(PaymentRequest.MISSING_CREDIT_CARD_ERROR.getMessage());
        }


        if (request.getErrorList().contains(PaymentRequest.MISSING_EMAIL_ERROR)) {
            viewModel.paymentErrorProperty().setValue(PaymentRequest.MISSING_EMAIL_ERROR.getMessage());
        }

    }

    @Override
    public void presentGetTicketBySeatsNullRequest() {
        viewModel.seatErrorProperty().setValue("Error with operation GetTicketBySeats");
    }

    @Override
    public void presentInvalidGetTicketBySeats(TicketRequest request) {
        if (request.getErrorList().contains(TicketRequest.MISSING_LIST_SEATS_ERROR)) {
            viewModel.seatErrorProperty().setValue(TicketRequest.MISSING_LIST_SEATS_ERROR.getMessage());
        }
        if (request.getErrorList().contains(TicketRequest.MISSING_OPTION_ONE_ERROR)) {
            viewModel.seatErrorProperty().setValue(TicketRequest.MISSING_OPTION_ONE_ERROR.getMessage());
        }
        if (request.getErrorList().contains(TicketRequest.MISSING_OPTION_TWO_ERROR)) {
            viewModel.seatErrorProperty().setValue(TicketRequest.MISSING_OPTION_TWO_ERROR.getMessage());
        }
        if (request.getErrorList().contains(TicketRequest.MISSING_OPTION_THREE_ERROR)) {
            viewModel.seatErrorProperty().setValue(TicketRequest.MISSING_OPTION_THREE_ERROR.getMessage());
        }
        if (request.getErrorList().contains(TicketRequest.MISSING_PROJECTION_ERROR)) {
            viewModel.seatErrorProperty().setValue(TicketRequest.MISSING_PROJECTION_ERROR.getMessage());
        }
        if (request.getErrorList().contains(TicketRequest.MISSING_NUMBER_ERROR)) {
            viewModel.seatErrorProperty().setValue(TicketRequest.MISSING_NUMBER_ERROR.getMessage());
        }

    }

    @Override
    public void presentGetListCinemaNullRequest() {
        viewModel.cinemaErrorProperty().setValue("Error with operation GetListCinema");
    }

    @Override
    public void presentInvalidGetListCinema(CinemaListRequest request) {
        viewModel.cinemaErrorProperty().setValue(CinemaListRequest.MISSING_MOVIE_ERROR.getMessage());
    }

    @Override
    public void presentGetTimeOfProjectionNullRequest() {
        viewModel.cinemaErrorProperty().setValue("Error with operation GetTimeOfProjection");

    }

    @Override
    public void presentInvalidGetTimeOfProjection(ProjectionListRequest request) {
        if (request.getErrorList().contains(ProjectionListRequest.MISSING_MOVIE_ERROR)) {
            viewModel.cinemaErrorProperty().setValue(ProjectionListRequest.MISSING_MOVIE_ERROR.getMessage());
        }
        if (request.getErrorList().contains(ProjectionListRequest.MISSING_DATE_ERROR)) {
            viewModel.cinemaErrorProperty().setValue(ProjectionListRequest.MISSING_DATE_ERROR.getMessage());
        }
        if (request.getErrorList().contains(ProjectionListRequest.MISSING_CINEMA_ERROR)) {
            viewModel.cinemaErrorProperty().setValue(ProjectionListRequest.MISSING_CINEMA_ERROR.getMessage());
        }

    }

    @Override
    public void presentProjection(ProjectionResponse request) {
        viewModel.seatErrorProperty().setValue("Error with projection presenting");
    }


    @Override
    public void presentGetNumberOfSeatsNullRequest() {
        viewModel.seatErrorProperty().setValue("Error with operation GetNumberOfSeats");
    }

    @Override
    public void presentInvalidGetNumberOfSeats(CinemaInformationRequest request) {
        viewModel.seatErrorProperty().setValue(CinemaInformationRequest.MISSING_PROJECTION_ERROR.getMessage());
    }

    @Override
    public void presentGetListMovieError() {
        viewModel.movieErrorProperty().setValue("Unable to recover movies by service");
    }

    @Override
    public void presentErrorByStripe(PaymentServiceException error) {
        viewModel.paymentErrorProperty().setValue(error.getMessage());
    }

    @Override
    public void presentProjectionList(ProjectionListResponse response) {
        viewModel.getProjectionOfProjectionTimeList().setAll(response.getProjectionDtoList());
    }

    @Override
    public void presentGetListMovieNullRequest() {
        viewModel.movieErrorProperty().setValue("Unable to recover movies by service");

    }

    @Override
    public void presentInvalidGetListMovie(MovieListRequest request) {
        viewModel.movieErrorProperty().setValue(CinemaListRequest.MISSING_MOVIE_ERROR.getMessage());
    }

    @Override
    public void presentAuthenticationError() {
        viewModel.authenticationErrorProperty().setValue("Problems with accessing the server ");

    }



    @Override
    public void presentInvalidGetCinema(CinemaInformationRequest request) {
        viewModel.cinemaErrorProperty().setValue("Problems with retrieve the cinema ");
    }

    @Override
    public void presentPayRepositoryException(String message) {
        viewModel.payRepositoryExceptionProperty().setValue(message);
    }

    @Override
    public void presentGetListMovieRepositoryException(String message) {
        viewModel.listMovieRepositoryExceptionProperty().setValue(message);

    }

    @Override
    public void presentGetCinemaListRepositoryException(String message) {
        viewModel.cinemaListRepositoryExceptionProperty().setValue(message);

    }

    @Override
    public void presentCreateTicketRepositoryException(String message) {
        viewModel.ticketRepositoryExceptionProperty().setValue(message);

    }

    @Override
    public void presentGetProjectionListRepositoryException(String message) {
        viewModel.projectionListRepositoryExceptionProperty().setValue(message);

    }

    @Override
    public void presentGetProjectionRepositoryException(String message) {
        viewModel.projectionRepositoryExceptionProperty().setValue(message);

    }

    @Override
    public void presentGetCinemaRepositoryException(String message) {
        viewModel.cinemaRepositoryExceptionProperty().setValue(message);

    }


}

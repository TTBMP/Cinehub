package com.ttbmp.cinehub.ui.web.buyticket;




import com.ttbmp.cinehub.app.service.payment.PaymentException;
import com.ttbmp.cinehub.app.usecase.buyticket.BuyTicketPresenter;
import com.ttbmp.cinehub.app.usecase.buyticket.request.*;
import com.ttbmp.cinehub.app.usecase.buyticket.response.*;

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
        viewModel.setMovieDtoList(response.getMovieList());
    }

    @Override
    public void presentCinemaList(GetListCinemaResponse response) {
        viewModel.setCinemaDtoList(response.getCinemaList());
    }


    @Override
    public void presentSeatList(GetNumberOfSeatsResponse response) {
        viewModel.setSeatDtoList(response.getSeatDtoList());
    }

    @Override
    public void setSelectedTicket(GetTicketBySeatsResponse response) {
        viewModel.setSelectedTicket(response.getTicketDto());
    }


    @Override
    public void presentPayNullRequest() {

    }

    @Override
    public void presentInvalidPay(PayRequest request) {

    }

    @Override
    public void presentGetTicketBySeatsNullRequest() {

    }

    @Override
    public void presentInvalidGetTicketBySeats(GetTicketBySeatsRequest request) {

    }

    @Override
    public void presentGetListCinemaNullRequest() {

    }

    @Override
    public void presentInvalidGetListCinema(GetListCinemaRequest request) {

    }

    @Override
    public void presentGetTimeOfProjectionNullRequest() {

    }

    @Override
    public void presentInvalidGetTimeOfProjection(GetTimeOfProjectionRequest request) {

    }


    @Override
    public void presentGetNumberOfSeatsNullRequest() {

    }

    @Override
    public void presentInvalidGetNumberOfSeats(GetNumberOfSeatsRequest request) {

    }

    @Override
    public void presentGetListMovieError() {

    }

    @Override
    public void presentErrorByStripe(PaymentException error) {

    }

    @Override
    public void presentProjectionList(ProjectionListResponse projectionTimeList) {
        viewModel.setProjectionList(projectionTimeList.getProjectionDto());
    }

    @Override
    public void presentGetListMovieNullRequest() {

    }

    @Override
    public void presentInvalidGetListMovie(GetListMovieRequest request) {

    }


}

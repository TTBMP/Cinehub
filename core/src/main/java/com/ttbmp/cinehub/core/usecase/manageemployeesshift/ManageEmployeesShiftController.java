package com.ttbmp.cinehub.core.usecase.manageemployeesshift;

import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.entity.Shift;
import com.ttbmp.cinehub.core.repository.CinemaRepository;
import com.ttbmp.cinehub.core.repository.HallRepository;
import com.ttbmp.cinehub.core.repository.ShiftRepository;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.request.GetShiftListRequest;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.request.GetShiftRequest;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.response.GetCinemaListResponse;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.response.GetHallListResponse;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.response.GetShiftListResponse;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.response.GetShiftResponse;
import com.ttbmp.cinehub.core.utilities.result.Result;

import java.time.LocalDate;


public class ManageEmployeesShiftController implements ManageEmployeesShiftUseCase {
    private final ManageEmployeesShiftPresenter manageEmployeesShiftPresenter;
    private final ShiftRepository shiftRepository;
    private final CinemaRepository cinemaRepository;
    private final HallRepository hallRepository;

    public ManageEmployeesShiftController(ManageEmployeesShiftPresenter manageEmployeesShiftPresenter, ShiftRepository shiftRepository, CinemaRepository cinemaRepository, HallRepository hallRepository) {
        this.manageEmployeesShiftPresenter = manageEmployeesShiftPresenter;
        this.shiftRepository = shiftRepository;
        this.cinemaRepository=cinemaRepository;
        this.hallRepository=hallRepository;
    }

    @Override
    public void getShiftList(GetShiftListRequest request){
        manageEmployeesShiftPresenter.presentShiftList(new Result<>(new GetShiftListResponse(shiftRepository.getShiftList().getValue(), request.getStart(), request.getCinema())));
    }

    @Override
    public boolean saveShift(GetShiftRequest request) {
        request.validate();
        Result<Boolean> shiftResult = shiftRepository.saveShift(request.getShift());
        if(shiftResult.getValue()) {
            manageEmployeesShiftPresenter.presentSaveShift(new Result<>(new GetShiftResponse(request.getShift())));
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void deleteShift(GetShiftRequest request){
        request.validate();

        Result<Shift> shiftResult= shiftRepository.deletedShift(request.getShift());

        manageEmployeesShiftPresenter.presentDeleteShift(new Result<>(new GetShiftResponse(shiftResult.getValue())));
    }

    @Override
    public void getCinemaList() {
        manageEmployeesShiftPresenter.presentCinemaList(new Result<>(new GetCinemaListResponse(cinemaRepository.getAllCinema())));
    }


    @Override
    public void getHallList(Cinema cinema){
        manageEmployeesShiftPresenter.presentHallList(new Result<>(new GetHallListResponse(hallRepository.getCinemaHallList(cinema))));
    }

}

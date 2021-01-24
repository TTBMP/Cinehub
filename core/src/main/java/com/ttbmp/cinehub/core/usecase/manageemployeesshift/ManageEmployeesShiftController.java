package com.ttbmp.cinehub.core.usecase.manageemployeesshift;

import com.ttbmp.cinehub.core.datamapper.CinemaDataMapper;
import com.ttbmp.cinehub.core.datamapper.HallDataMapper;
import com.ttbmp.cinehub.core.datamapper.ShiftDataMapper;
import com.ttbmp.cinehub.core.entity.Shift;
import com.ttbmp.cinehub.core.repository.CinemaRepository;
import com.ttbmp.cinehub.core.repository.HallRepository;
import com.ttbmp.cinehub.core.repository.ShiftRepository;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.request.GetHallListRequest;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.request.GetShiftListRequest;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.request.ShiftRequest;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.response.GetCinemaListResponse;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.response.GetHallListResponse;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.response.GetShiftListResponse;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.response.ShiftResponse;
import com.ttbmp.cinehub.core.utilities.result.Result;


/**
 * @author Massimo Mazzetti
 */

public class ManageEmployeesShiftController implements ManageEmployeesShiftUseCase {
    private final ManageEmployeesShiftPresenter manageEmployeesShiftPresenter;
    private final ShiftRepository shiftRepository;
    private final CinemaRepository cinemaRepository;
    private final HallRepository hallRepository;

    public ManageEmployeesShiftController(ManageEmployeesShiftPresenter manageEmployeesShiftPresenter, ShiftRepository shiftRepository, CinemaRepository cinemaRepository, HallRepository hallRepository) {
        this.manageEmployeesShiftPresenter = manageEmployeesShiftPresenter;
        this.shiftRepository = shiftRepository;
        this.cinemaRepository = cinemaRepository;
        this.hallRepository = hallRepository;
    }

    @Override
    public void getShiftList(GetShiftListRequest request) {
        manageEmployeesShiftPresenter.presentShiftList(new Result<>(new GetShiftListResponse(ShiftDataMapper.mapToDtoList(shiftRepository.getShiftList().getValue()), request.getStart(), CinemaDataMapper.mapToDto(request.getCinema()))));
    }

    @Override
    public boolean saveShift(ShiftRequest request) {
        request.validate();
        Result<Boolean> shiftResult = shiftRepository.saveShift(request.getShift());
        boolean result = shiftResult.getValue();
        if (result) {
            manageEmployeesShiftPresenter.presentSaveShift(new Result<>(new ShiftResponse(ShiftDataMapper.mapToDto(request.getShift()))));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void deleteShift(ShiftRequest request) {
        request.validate();
        Result<Shift> shiftResult = shiftRepository.deletedShift(request.getShift());
        manageEmployeesShiftPresenter.presentDeleteShift(new Result<>(new ShiftResponse(ShiftDataMapper.mapToDto(shiftResult.getValue()))));
    }

    @Override
    public void getCinemaList() {
        manageEmployeesShiftPresenter.presentCinemaList(new Result<>(new GetCinemaListResponse(CinemaDataMapper.mapToDtoList(cinemaRepository.getAllCinema()))));
    }

    @Override
    public void getHallList(GetHallListRequest request) {
        manageEmployeesShiftPresenter.presentHallList(new Result<>(new GetHallListResponse(HallDataMapper.mapToDtoList(hallRepository.getCinemaHallList(request.getCinema())))));
    }

}

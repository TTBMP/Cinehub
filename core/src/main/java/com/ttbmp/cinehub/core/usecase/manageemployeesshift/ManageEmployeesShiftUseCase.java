package com.ttbmp.cinehub.core.usecase.manageemployeesshift;

import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.usecase.UseCase;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.request.GetShiftListRequest;
import com.ttbmp.cinehub.core.usecase.manageemployeesshift.request.GetShiftRequest;

import java.time.LocalDate;


public interface ManageEmployeesShiftUseCase extends UseCase {
    
    void getShiftList(GetShiftListRequest request);

    boolean saveShift(GetShiftRequest shift);

    void deleteShift(GetShiftRequest shift);

    void getCinemaList();

     void getHallList(Cinema cinema);

}

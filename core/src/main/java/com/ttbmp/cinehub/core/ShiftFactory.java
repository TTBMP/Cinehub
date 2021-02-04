package com.ttbmp.cinehub.core;

import com.ttbmp.cinehub.core.entity.Shift;
import com.ttbmp.cinehub.core.entity.ShiftProjectionist;
import com.ttbmp.cinehub.core.entity.ShiftUsher;


public class ShiftFactory {

    public Shift createConcreteShift(int type) {
        switch (type) {
            case Constant.SHIFT_PROJECTIONIST:
                return createShiftProjectionist();
            case Constant.SHIFT_USHER:
                return createShiftUsher();
            default:
                return null;
            //exception tipo non valido
        }
    }

    private Shift createShiftProjectionist() {
        return new ShiftProjectionist();
    }

    private Shift createShiftUsher() {
        return new ShiftUsher();
    }
}
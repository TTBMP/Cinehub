package com.ttbmp.cinehub.core;

import com.ttbmp.cinehub.core.entity.Shift;
import com.ttbmp.cinehub.core.entity.ShiftProjectionist;

public class ConcreteAbstractShiftProjectionist extends Shift implements AbstractShift {

    @Override
    public Shift createShift() {
        return new ShiftProjectionist();
    }

}

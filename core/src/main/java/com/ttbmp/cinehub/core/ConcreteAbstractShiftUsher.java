package com.ttbmp.cinehub.core;

import com.ttbmp.cinehub.core.entity.Shift;
import com.ttbmp.cinehub.core.entity.ShiftUsher;

public class ConcreteAbstractShiftUsher extends Shift implements AbstractShift {

    @Override
    public Shift createShift() {
        return new ShiftUsher();
    }
}
package com.ttbmp.cinehub.core;

public class ShiftFactory {

    public ConcreteAbstractShiftProjectionist createShiftProjectionist() {
        return new ConcreteAbstractShiftProjectionist();
    }

    public ConcreteAbstractShiftUsher createShiftUsher() {
        return new ConcreteAbstractShiftUsher();
    }
}

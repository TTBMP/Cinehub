package com.ttbmp.cinehub.core.entity.shift;


public class ShiftFactory {

    public Shift createConcreteShift(Class<? extends Shift> shiftClass) {
        if (ProjectionistShift.class.equals(shiftClass)) {
            return createShiftProjectionist();
        } else if (UsherShift.class.equals(shiftClass)) {
            return createShiftUsher();
        }
        return null;
        //exception tipo non valido
    }

    private Shift createShiftProjectionist() {
        return new ProjectionistShift();
    }

    private Shift createShiftUsher() {
        return new UsherShift();
    }
}
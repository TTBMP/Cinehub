package com.ttbmp.cinehub.app.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import com.ttbmp.cinehub.domain.shift.Shift;

/**
 * @author Massimo Mazzetti
 */
public class ShiftRequest extends AuthenticatedRequest {
    public static final Request.Error MISSING_SHIFT = new Request.Error("Shift non valido");

    private int shiftId;

    public ShiftRequest(String sessionToken, int shiftId) {
        super(sessionToken);
        this.shiftId = shiftId;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    @Override
    protected void onValidate() {
        // Do nothing because the class doesn't have attributes.
    }

    public void semanticValidate(Shift shift) throws InvalidRequestException{
            if(shift == null){
                addError(MISSING_SHIFT);
                throw new InvalidRequestException();
            }
    }
}

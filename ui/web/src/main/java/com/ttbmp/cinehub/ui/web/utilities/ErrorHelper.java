package com.ttbmp.cinehub.ui.web.utilities;

import com.google.common.base.Joiner;
import com.ttbmp.cinehub.app.utilities.request.Request;
import org.springframework.ui.Model;

import java.util.ArrayList;

public class ErrorHelper {

    public static final String ERROR_VIEW_PATH = "error";
    public static final String ERROR_ATTRIBUTE_NAME = "error";
    public static final String INVALID_ERROR_MESSAGE = "Invalid request";

    private ErrorHelper() {

    }

    public static String getRequestErrorMessage(Request request) {
        return Joiner.on("\n").join(new ArrayList<>(request.getErrorList()));
    }

    public static String returnView(Model model, String viewPath) {
        return (model.getAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME) == null) ? viewPath : ERROR_VIEW_PATH;
    }

}

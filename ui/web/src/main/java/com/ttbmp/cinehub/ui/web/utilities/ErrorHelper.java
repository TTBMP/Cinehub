package com.ttbmp.cinehub.ui.web.utilities;

import com.ttbmp.cinehub.app.utilities.request.Request;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class ErrorHelper {

    public static final String ERROR_VIEW_PATH = "error";
    public static final String ERROR_ATTRIBUTE_NAME = "error";
    public static final String INVALID_ERROR_MESSAGE = "Invalid request";
    public static final String UNAUTHENTICATED_ERROR_MESSAGE = "Unauthenticated request";

    private ErrorHelper() {

    }

    public static String getRequestErrorMessage(Request request) {
        return request.getErrorList().stream()
                .map(Request.Error::getMessage)
                .collect(Collectors.joining());
    }

    public static String returnView(HttpServletResponse response, Model model, String viewPath) {
        var errorMessage = (String) model.getAttribute(ErrorHelper.ERROR_ATTRIBUTE_NAME);
        if (errorMessage == null) {
            return viewPath;
        }
        if (UNAUTHENTICATED_ERROR_MESSAGE.equals(errorMessage)) {
            response.setHeader("Location", "/login");
            response.setStatus(302);
            return viewPath;
        }
        return ERROR_VIEW_PATH;
    }

}

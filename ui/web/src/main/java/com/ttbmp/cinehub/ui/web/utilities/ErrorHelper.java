package com.ttbmp.cinehub.ui.web.utilities;

import com.ttbmp.cinehub.app.utilities.request.Request;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;

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
        var stringBuilder = new StringBuilder();
        for (var message : request.getErrorList()) {
            stringBuilder.append(message.getMessage());
        }
        return stringBuilder.toString();
        //  return Joiner.on("\n").join(new ArrayList<>(request.getErrorList()));         versione vecchia problema
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

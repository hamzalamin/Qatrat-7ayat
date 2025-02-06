package com.wora.qatrat7ayat.security.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wora.qatrat7ayat.exceptions.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.time.LocalDateTime;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest req,
                       HttpServletResponse res,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {


        ObjectMapper mapper = new ObjectMapper();
        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(403);
        res.getWriter().write(mapper.writeValueAsString(new ErrorResponse(
                403, LocalDateTime.now(), "access denied handler who handle this ", accessDeniedException.getMessage())

        ));
    }
}
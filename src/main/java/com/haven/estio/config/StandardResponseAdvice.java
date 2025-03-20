package com.haven.estio.config;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.haven.estio.models.StandardResponse;

@ControllerAdvice
public class StandardResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true; // Intercept all responses
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {

        // ✅ Allow file streams, byte arrays, and other non-JSON responses to pass through
        if (body instanceof byte[] || selectedContentType.equals(MediaType.APPLICATION_OCTET_STREAM)
                || selectedContentType.equals(MediaType.MULTIPART_FORM_DATA)
                || selectedContentType.equals(MediaType.APPLICATION_PDF)
                || selectedContentType.equals(MediaType.IMAGE_PNG)
                || selectedContentType.equals(MediaType.IMAGE_JPEG)
                || selectedContentType.equals(MediaType.TEXT_PLAIN)) {
            return body;
        }

        // ✅ If the response is already wrapped, return as is
        if (body instanceof StandardResponse) {
            return body;
        }

        // ✅ Wrap JSON responses
        return new StandardResponse<>(true, "Request processed successfully", body);
    }
}


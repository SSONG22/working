package com.songi.working.result;

import com.songi.working.result.error.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResponseResult {
    private Object response;
    private ErrorResponse error;

    public static ResponseResult success(Object response) {
        return ResponseResult.builder()
                .response(response)
                .error(null)
                .build();
    }

    public static ResponseResult fail(ErrorResponse error) {
        return ResponseResult.builder()
                .response(null)
                .error(error)
                .build();
    }
}

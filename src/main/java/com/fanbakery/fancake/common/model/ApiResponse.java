package com.fanbakery.fancake.common.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> {
    T data;

    public ApiResponse<T> build(T data) {
        this.data = data;
        return this;
    }
}

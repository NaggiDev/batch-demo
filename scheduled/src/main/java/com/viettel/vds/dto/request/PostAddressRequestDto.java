package com.viettel.vds.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PostAddressRequestDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull(message = "address là bắt buộc")
    private String address;
}

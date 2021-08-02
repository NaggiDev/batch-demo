package com.viettel.vds.dto.request;

import lombok.Data;

@Data
public class PostUserAndAddressRequestDto {
    private PostUserRequestDto userDto;
    private PostAddressRequestDto addressDto;
}

package com.viettel.vds.dto.response;

import lombok.Builder;
import lombok.Data;
import com.viettel.vds.entity.address.AddressEntity;
import com.viettel.vds.entity.user.UserEntity;

@Data
@Builder
public class PostUserAndAddressResponseDto {
    private AddressEntity address;
    private UserEntity user;
}

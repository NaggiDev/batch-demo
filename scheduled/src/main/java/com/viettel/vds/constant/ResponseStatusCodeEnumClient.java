package com.viettel.vds.constant;

import vn.com.viettel.vds.arch.constant.ResponseStatusCode;
import vn.com.viettel.vds.arch.constant.ResponseStatusCodeEnum;
@SuppressWarnings("java:S1214")
public interface ResponseStatusCodeEnumClient extends ResponseStatusCodeEnum {
    ResponseStatusCode DATA_NOT_FOUND = ResponseStatusCode.builder().code("01").httpCode(200).build();
    ResponseStatusCode TOKEN_ERROR = ResponseStatusCode.builder().code("02").httpCode(200).build();
    ResponseStatusCode USER_ID_NOT_FOUND = ResponseStatusCode.builder().code("user id not found").httpCode(400).build();
}

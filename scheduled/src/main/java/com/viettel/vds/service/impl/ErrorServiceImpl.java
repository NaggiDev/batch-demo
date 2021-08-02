package com.viettel.vds.service.impl;

import org.springframework.stereotype.Service;
import vn.com.viettel.vds.arch.service.ErrorService;

@Service
public class ErrorServiceImpl implements ErrorService {
    @Override
    @SuppressWarnings("all")
    public String getErrorDetail(String errorCode, String language) {
        // sử dụng nếu bạn muốn sử lý một error với một msg được lấy từ ở bên ngoài, ví dụ database...etc..

        //fake
        switch (language) {
            case "vi": {
                switch (errorCode) {
                    case "00": {
                        return "Thành công";
                    }
                    default: {
                        return null;
                    }
                }
            }
            default: {
                switch (errorCode) {
                    case "00": {
                        return "success";
                    }
                    default: {
                        return null;
                    }
                }
            }
        }
    }
}

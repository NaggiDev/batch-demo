package com.viettel.vds.controller.restful;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.viettel.vds.arch.controller.restful.BaseController;
import vn.com.viettel.vds.arch.exception.BaseResponseException;
import vn.com.viettel.vds.arch.factory.response.GeneralResponse;
import vn.com.viettel.vds.arch.factory.response.ResponseFactory;
import vn.com.viettel.vds.arch.service.AuthService;
import com.viettel.vds.constant.ResponseStatusCodeEnumClient;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/user-management/v1/api")
@Tag(name = "AuthVerifyController", description = "AuthVerifyController")
@Slf4j
public class AuthVerifyController extends BaseController {
    @Autowired(required = false)
    AuthService authService;
    @Autowired
    ResponseFactory responseFactory;

    @GetMapping(value = "/demoAuthVerify")
    public ResponseEntity<GeneralResponse<Claims>> getRestDemo(String token) {
        Claims claims = authService.getClaims(token);
        if (Objects.nonNull(claims)) {
            return responseFactory.success(claims);
        }
        throw new BaseResponseException(ResponseStatusCodeEnumClient.TOKEN_ERROR );
    }

    @GetMapping(value = "/verifyTokenWReason")
    public ResponseEntity<GeneralResponse<Boolean>> getVerifyTokenWReason(String token) {
        boolean claims = false;
        try {
            claims = authService.verifyTokenWReason(token);
            return responseFactory.success(claims);
        } catch (SignatureException e) {
            log.warn("Error verfy");
        } catch (ExpiredJwtException e) {
            log.warn("Error ExpiredJwtException");
        } catch (InvalidKeySpecException e) {
            log.warn("Error InvalidKeySpecException");
        } catch (NoSuchAlgorithmException e) {
            log.error("Error NoSuchAlgorithmException");
        } catch (Exception e) {
            log.error("Error Exception");
        }


        Map<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put("Error", "vui long check lai");
        throw new BaseResponseException(null, ResponseStatusCodeEnumClient.TOKEN_ERROR,stringStringMap );
    }
}

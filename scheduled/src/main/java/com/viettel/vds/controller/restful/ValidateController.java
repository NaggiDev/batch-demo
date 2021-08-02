package com.viettel.vds.controller.restful;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.com.viettel.vds.arch.controller.restful.BaseController;
import vn.com.viettel.vds.arch.factory.response.GeneralResponse;
import vn.com.viettel.vds.arch.factory.response.ResponseFactory;
import vn.com.viettel.vds.arch.validator.annotation.NotNumber;
import com.viettel.vds.dto.request.PostUserRequestDto;
import com.viettel.vds.entity.user.UserEntity;
import com.viettel.vds.repository.user.UserRepo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/user-management/v1/api")
@Tag(name = "Validate", description = "Validate")
@Validated
public class ValidateController extends BaseController {
    @Autowired
    ResponseFactory responseFactory;
    @Autowired
    UserRepo userRepo;

    @PostMapping(value = "/headerValidate")
    public ResponseEntity<GeneralResponse<String>> headerValidate(
            @RequestHeader(value = "id", required = false)
            @NotNull(message = "id trong header không thể null")
                    String id,
            @RequestHeader(value = "number", required = false)
            @NotNull(message = "Number trong header không thể null")
            @NotNumber(message = "Number phải là chữ số")
                    String number

    ) {
        return responseFactory.success("ok");
    }

    @PostMapping(value = "/bodyValidate", produces = "application/json", consumes = "application/json")
    @Transactional
    public ResponseEntity<GeneralResponse<UserEntity>> bodyValidate(@Valid @RequestBody() PostUserRequestDto postUserDto) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(postUserDto, userEntity);
        userEntity = userRepo.save(userEntity);
        return responseFactory.success(userEntity);
    }

    @PostMapping(value = "/PathValidate")
    public ResponseEntity<GeneralResponse<String>> pathValidate(
            @RequestParam(value = "id", required = false)
            @NotNull(message = "id trong RequestParam không thể null")
                    String id,
            @RequestParam(value = "number", required = false)
            @NotNull(message = "Number trong RequestParam không thể null")
            @NotNumber(message = "Number phải là chữ số")
                    String number

    ) {
        return responseFactory.success("ok");
    }
}

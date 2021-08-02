package com.viettel.vds.controller.restful;

import com.viettel.vds.constant.ResponseStatusCodeEnumClient;
import com.viettel.vds.dto.request.PostUserRequestDto;
import com.viettel.vds.entity.user.UserEntity;
import com.viettel.vds.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.com.viettel.vds.arch.controller.restful.BaseController;
import vn.com.viettel.vds.arch.exception.BaseResponseException;
import vn.com.viettel.vds.arch.factory.response.GeneralResponse;
import vn.com.viettel.vds.arch.factory.response.ResponseFactory;

import javax.validation.Valid;
import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user-management/v1/api")
@Tag(name = "UserController", description = "User API")
@Validated
public class UserController extends BaseController {
    UserServiceImpl userService;
    @Autowired
    ResponseFactory responseFactory;

    @GetMapping(value = "getAllUser")
    public ResponseEntity<GeneralResponse<List<UserEntity>>> listAll(){
        return responseFactory.success(userService.listAll());
    }

    @GetMapping(value = "getAllUserPaging")
    public ResponseEntity<GeneralResponse<List<UserEntity>>> listPaging(@RequestParam int pageNo,
                                     @RequestParam(defaultValue = "2") Integer pageSize,
                                     @RequestParam(defaultValue = "name") String sortBy){
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        return responseFactory.success(userService.findByPaging(pageable).getContent());
    }

    @GetMapping(value = "findUser")
    public ResponseEntity<GeneralResponse<UserEntity>> findById(@RequestParam(value = "id")long id){
        return responseFactory.success(userService.findById(id));
    }

    @PostMapping(value = "createUser", produces = "application/json", consumes = "application/json")
    @Transactional
    public ResponseEntity<GeneralResponse<UserEntity>> createUser(@Valid @RequestBody PostUserRequestDto userDto){
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        return responseFactory.success(userService.save(userEntity));
    }

    @PutMapping(value = "updateUser", produces = "application/json", consumes = "application/json")
    @Transactional
    public ResponseEntity<GeneralResponse<UserEntity>> updateUser(@Valid @RequestBody PostUserRequestDto userDto){
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(userDto, user);
        System.out.println(user);
        UserEntity userCurrent = userService.findById(user.getId());
        if(userCurrent != null){
            userCurrent.setName(user.getName());
            userCurrent.setAddress(user.getAddress());
            userCurrent.setCreateDate(user.getCreateDate());
            userCurrent.setPhone(user.getPhone());
            return responseFactory.success(userService.save(userCurrent));
        }
        throw new BaseResponseException(ResponseStatusCodeEnumClient.USER_ID_NOT_FOUND);
    }

    @DeleteMapping(value = "deleteUser")
    @Transactional
    public ResponseEntity<GeneralResponse<String>> delete(@RequestParam(value = "id")Integer id){
        userService.deleteById(id);
        return responseFactory.success("ok");
    }
    @DeleteMapping(value = "deleteAllUser")
    @Transactional
    public ResponseEntity<GeneralResponse<String>> deleteAll(){
        userService.deleteAll();
        return responseFactory.success("ok");
    }
}
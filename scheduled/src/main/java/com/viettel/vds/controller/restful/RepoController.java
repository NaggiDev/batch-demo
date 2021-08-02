//package com.viettel.vds.controller.restful;
//
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import vn.com.viettel.vds.arch.controller.restful.BaseController;
//import vn.com.viettel.vds.arch.factory.response.GeneralResponse;
//import vn.com.viettel.vds.arch.factory.response.ResponseFactory;
//import com.viettel.vds.dto.request.PostUserAndAddressRequestDto;
//import com.viettel.vds.dto.request.PostUserRequestDto;
//import com.viettel.vds.dto.response.PostUserAndAddressResponseDto;
//import com.viettel.vds.entity.address.AddressEntity;
//import com.viettel.vds.entity.user.UserEntity;
//import com.viettel.vds.repository.address.AddressEntityRepo;
//import com.viettel.vds.repository.user.UserRepo;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@RequestMapping("/user-management/v1/api")
//@Tag(name = "testRepo", description = "testRepo")
//public class RepoController  extends BaseController {
//    @Autowired
//    UserRepo userRepo;
//    @Autowired
//    AddressEntityRepo addressEntityRepo;
//    @Autowired
//    ResponseFactory responseFactory;
//
//    @GetMapping(value = "/getAllUser")
//    public ResponseEntity<GeneralResponse<List<UserEntity>>> getAllUser() {
//        return responseFactory.success((List<UserEntity>) userRepo.findAll());
//    }
//
//    @GetMapping(value = "/getAllAddress")
//    public ResponseEntity<GeneralResponse<List<AddressEntity>>> getAllAddress() {
//        return responseFactory.success((List<AddressEntity>) addressEntityRepo.findAll());
//    }
//
//    @PostMapping(value = "/userPostOK", produces = "application/json", consumes = "application/json")
//    @Transactional
//    public ResponseEntity<GeneralResponse<UserEntity>> postOK(@Valid @RequestBody() PostUserRequestDto postUserDto) {
//        UserEntity userEntity = new UserEntity();
//        BeanUtils.copyProperties(postUserDto, userEntity);
//        userEntity = userRepo.save(userEntity);
//        return responseFactory.success(userEntity);
//    }
//    @SuppressWarnings("java:S112")
//    @PostMapping(value = "/userPostError", produces = "application/json", consumes = "application/json")
//    @Transactional
//    public ResponseEntity<GeneralResponse<UserEntity>> postError(@Valid @RequestBody() PostUserRequestDto postUserDto) {
//        UserEntity userEntity = new UserEntity();
//        BeanUtils.copyProperties(postUserDto, userEntity);
//        userRepo.save(userEntity);
//        throw new RuntimeException("test");
//    }
//
//    @PostMapping(value = "/ChainedTransactionOK", produces = "application/json", consumes = "application/json")
//    @Transactional(value = "chainedTransactionManager")
//    public ResponseEntity<GeneralResponse<PostUserAndAddressResponseDto>> chainedTransactionOK(@Valid @RequestBody() PostUserAndAddressRequestDto postUserDto) {
//        UserEntity userEntity = new UserEntity();
//        BeanUtils.copyProperties(postUserDto.getUserDto(), userEntity);
//        userEntity = userRepo.save(userEntity);
//
//        AddressEntity addressEntity = new AddressEntity();
//        BeanUtils.copyProperties(postUserDto.getAddressDto(), addressEntity);
//        addressEntity = addressEntityRepo.save(addressEntity);
//
//        PostUserAndAddressResponseDto postUserAndAddressResponseDto = PostUserAndAddressResponseDto.builder()
//                .address(addressEntity).user(userEntity).build();
//        return responseFactory.success(postUserAndAddressResponseDto);
//    }
//    @SuppressWarnings("java:S112")
//    @PostMapping(value = "/ChainedTransactionError", produces = "application/json", consumes = "application/json")
//    @Transactional(value = "chainedTransactionManager")
//    public ResponseEntity<GeneralResponse<PostUserAndAddressResponseDto>> chainedTransactionError(@Valid @RequestBody() PostUserAndAddressRequestDto postUserDto) {
//        UserEntity userEntity = new UserEntity();
//        BeanUtils.copyProperties(postUserDto.getUserDto(), userEntity);
//        // nếu không sử dụng chainedTransaction thì lỗi thì chỉ có transacton đầu tiên được revert.
//         userRepo.save(userEntity);
//
//        AddressEntity addressEntity = new AddressEntity();
//        BeanUtils.copyProperties(postUserDto.getAddressDto(), addressEntity);
//       addressEntityRepo.save(addressEntity);
//
//        throw new RuntimeException();
//    }
//
//
//}

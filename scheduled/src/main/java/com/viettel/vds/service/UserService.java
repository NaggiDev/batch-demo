package com.viettel.vds.service;

import com.viettel.vds.entity.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<UserEntity> listAll();
    UserEntity findById(long id);
    UserEntity save(UserEntity user);
    void deleteById(long id);
    void deleteAll();
    Page<UserEntity> findByPaging(Pageable pageable);
}

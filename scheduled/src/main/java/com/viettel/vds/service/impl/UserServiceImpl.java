package com.viettel.vds.service.impl;

import com.viettel.vds.entity.user.UserEntity;
import com.viettel.vds.repository.user.UserRepo;
import com.viettel.vds.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    UserRepo repo;

    @Override
    public List<UserEntity> listAll(){
        return repo.findAll();
    }

    @Override
    public UserEntity findById(long id){
        return repo.findById(id);
    }

    @Override
    public UserEntity save(UserEntity user){
        return repo.save(user);
    }

    @Override
    public void deleteById(long id){
        repo.deleteById(id);
    }

    @Override
    public void deleteAll(){
        repo.deleteAll();
    }

    @Override
    public Page<UserEntity> findByPaging(Pageable pageable){
        return repo.findAll(pageable);
    }
}
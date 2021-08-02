package com.viettel.vds.repository.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.viettel.vds.entity.user.UserEntity;

import java.util.List;

@Repository
public interface UserRepo extends PagingAndSortingRepository<UserEntity,Long>, JpaRepository<UserEntity,Long>{
    List<UserEntity> findAll();

    UserEntity findById(long id);

    UserEntity save(UserEntity user);

    void deleteById(long id);

    void delete(UserEntity user);

    void deleteAll();

    @Override
    Page<UserEntity> findAll(Pageable pageable);
}

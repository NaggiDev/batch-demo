package com.viettel.vds.repository.address;


import org.springframework.data.repository.CrudRepository;
import com.viettel.vds.entity.address.AddressEntity;

public interface AddressEntityRepo extends CrudRepository<AddressEntity, Integer> {

}

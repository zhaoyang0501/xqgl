package com.yxy.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.yxy.entity.Owner;
public interface OwnerRepository extends PagingAndSortingRepository<Owner, String>,JpaSpecificationExecutor<Owner>{
	public List<Owner> findByUsernameAndPassword(String userName,String password);
}


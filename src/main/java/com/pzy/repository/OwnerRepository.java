package com.pzy.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.Owner;
public interface OwnerRepository extends PagingAndSortingRepository<Owner, String>,JpaSpecificationExecutor<Owner>{
	public List<Owner> findByUsernameAndPassword(String userName,String password);
}


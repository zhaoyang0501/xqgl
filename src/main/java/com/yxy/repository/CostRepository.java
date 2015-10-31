package com.yxy.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.yxy.entity.Cost;
import com.yxy.entity.Guest;
import com.yxy.entity.Owner;
public interface CostRepository extends PagingAndSortingRepository<Cost, Long>,JpaSpecificationExecutor<Cost>{
	 public List<Cost> findByOwner(Owner owner);
}


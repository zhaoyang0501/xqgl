package com.pzy.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.Cost;
import com.pzy.entity.Guest;
import com.pzy.entity.Owner;
public interface CostRepository extends PagingAndSortingRepository<Cost, Long>,JpaSpecificationExecutor<Cost>{
	 public List<Cost> findByOwner(Owner owner);
}


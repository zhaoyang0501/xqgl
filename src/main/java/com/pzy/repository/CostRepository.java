package com.pzy.repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.Cost;
public interface CostRepository extends PagingAndSortingRepository<Cost, Long>,JpaSpecificationExecutor<Cost>{
}


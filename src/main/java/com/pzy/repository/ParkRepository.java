package com.pzy.repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.Park;
public interface ParkRepository extends PagingAndSortingRepository<Park, Long>,JpaSpecificationExecutor<Park>{
 
}


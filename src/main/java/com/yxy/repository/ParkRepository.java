package com.yxy.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.yxy.entity.Park;
public interface ParkRepository extends PagingAndSortingRepository<Park, Long>,JpaSpecificationExecutor<Park>{
	
	public List<Park> findByArea(String area);
 
}


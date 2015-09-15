package com.pzy.repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.Guest;
public interface GuestRepository extends PagingAndSortingRepository<Guest, Long>,JpaSpecificationExecutor<Guest>{
 
}


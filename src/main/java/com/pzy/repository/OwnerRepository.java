package com.pzy.repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.Owner;
public interface OwnerRepository extends PagingAndSortingRepository<Owner, String>,JpaSpecificationExecutor<Owner>{
 
}


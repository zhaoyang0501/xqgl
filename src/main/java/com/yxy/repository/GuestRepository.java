package com.yxy.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.yxy.entity.Guest;
import com.yxy.entity.Owner;
public interface GuestRepository extends PagingAndSortingRepository<Guest, Long>,JpaSpecificationExecutor<Guest>{
 public List<Guest> findByOwner(Owner owner);
}


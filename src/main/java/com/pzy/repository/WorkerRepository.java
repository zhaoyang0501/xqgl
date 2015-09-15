package com.pzy.repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.Worker;
public interface WorkerRepository extends PagingAndSortingRepository<Worker, Long>,JpaSpecificationExecutor<Worker>{
 
}


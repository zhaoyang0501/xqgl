package com.yxy.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.yxy.entity.Worker;
public interface WorkerRepository extends PagingAndSortingRepository<Worker, Long>,JpaSpecificationExecutor<Worker>{
	public List<Worker> findByUsernameAndPassword(String userName,String password);
}


package com.yxy.repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.yxy.entity.News;
public interface NewsRepository extends PagingAndSortingRepository<News, Long>,JpaSpecificationExecutor<News>{
}


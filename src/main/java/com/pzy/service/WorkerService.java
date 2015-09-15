
package com.pzy.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.pzy.entity.Worker;
import com.pzy.repository.WorkerRepository;

@Service
public class WorkerService {
     @Autowired
     private WorkerRepository workerRepository;
     public List<Worker> findAll() {
          return (List<Worker>) workerRepository.findAll();
     }
     public Page<Worker> findAll(final int pageNumber, final int pageSize,final String workerName){
               PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
              
               Specification<Worker> spec = new Specification<Worker>() {
                    @Override
                    public Predicate toPredicate(Root<Worker> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    Predicate predicate = cb.conjunction();
                    if (workerName != null) {
                         predicate.getExpressions().add(cb.like(root.get("workerName").as(String.class), workerName+"%"));
                    }
                    return predicate;
                    }
               };
               Page<Worker> result = (Page<Worker>) workerRepository.findAll(spec, pageRequest);
               return result;
     }
     public void delete(Long id){
          workerRepository.delete(id);
     }
     public Worker find(Long id){
    	  return workerRepository.findOne(id);
     }
     public void save(Worker Worker){
    	 workerRepository.save(Worker);
     }
}

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

import com.pzy.entity.Park;
import com.pzy.repository.ParkRepository;

@Service
public class ParkService {
     @Autowired
     private ParkRepository parkRepository;
     public List<Park> findAll() {
          return (List<Park>) parkRepository.findAll();
     }
     public Page<Park> findAll(final int pageNumber, final int pageSize,final String parkName){
               PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
              
               Specification<Park> spec = new Specification<Park>() {
                    @Override
                    public Predicate toPredicate(Root<Park> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    Predicate predicate = cb.conjunction();
                    if (parkName != null) {
                         predicate.getExpressions().add(cb.like(root.get("parkName").as(String.class), parkName+"%"));
                    }
                    return predicate;
                    }
               };
               Page<Park> result = (Page<Park>) parkRepository.findAll(spec, pageRequest);
               return result;
     }
     public void delete(Long id){
          parkRepository.delete(id);
     }
     public Park find(Long id){
    	  return parkRepository.findOne(id);
     }
     public void save(Park Park){
    	 parkRepository.save(Park);
     }
}
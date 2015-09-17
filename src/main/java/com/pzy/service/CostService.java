
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

import com.pzy.entity.Cost;
import com.pzy.entity.Guest;
import com.pzy.entity.Owner;
import com.pzy.repository.CostRepository;

@Service
public class CostService {
     @Autowired
     private CostRepository costRepository;
     public List<Cost> findAll() {
          return (List<Cost>) costRepository.findAll();
     }
     public Page<Cost> findAll(final int pageNumber, final int pageSize,final String costName){
               PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
              
               Specification<Cost> spec = new Specification<Cost>() {
                    @Override
                    public Predicate toPredicate(Root<Cost> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    Predicate predicate = cb.conjunction();
                    if (costName != null) {
                         predicate.getExpressions().add(cb.like(root.get("year").as(String.class), costName+"%"));
                    }
                    return predicate;
                    }
               };
               Page<Cost> result = (Page<Cost>) costRepository.findAll(spec, pageRequest);
               return result;
     }
     public void delete(Long id){
          costRepository.delete(id);
     }
     public Cost find(Long id){
    	  return costRepository.findOne(id);
     }
     public void save(Cost Cost){
    	 costRepository.save(Cost);
     }
     
     public List<Cost> findByOwner(Owner owner){
    	 return costRepository.findByOwner(owner);
     } 
}

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

import com.pzy.entity.Owner;
import com.pzy.repository.OwnerRepository;

@Service
public class OwnerService {
     @Autowired
     private OwnerRepository ownerRepository;
     public List<Owner> findAll() {
          return (List<Owner>) ownerRepository.findAll();
     }
     public Page<Owner> findAll(final int pageNumber, final int pageSize,final String ownerName){
               PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
              
               Specification<Owner> spec = new Specification<Owner>() {
                    @Override
                    public Predicate toPredicate(Root<Owner> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    Predicate predicate = cb.conjunction();
                    if (ownerName != null) {
                         predicate.getExpressions().add(cb.like(root.get("ownerName").as(String.class), ownerName+"%"));
                    }
                    return predicate;
                    }
               };
               Page<Owner> result = (Page<Owner>) ownerRepository.findAll(spec, pageRequest);
               return result;
     }
     public void delete(String id){
          ownerRepository.delete(id);
     }
     public Owner find(String id){
    	  return ownerRepository.findOne(id);
     }
     public void save(Owner Owner){
    	 ownerRepository.save(Owner);
     }
}
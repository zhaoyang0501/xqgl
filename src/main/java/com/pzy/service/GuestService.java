
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

import com.pzy.entity.Guest;
import com.pzy.repository.GuestRepository;

@Service
public class GuestService {
     @Autowired
     private GuestRepository guestRepository;
     public List<Guest> findAll() {
          return (List<Guest>) guestRepository.findAll();
     }
     public Page<Guest> findAll(final int pageNumber, final int pageSize,final String guestName){
               PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
              
               Specification<Guest> spec = new Specification<Guest>() {
                    @Override
                    public Predicate toPredicate(Root<Guest> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    Predicate predicate = cb.conjunction();
                    if (guestName != null) {
                         predicate.getExpressions().add(cb.like(root.get("guestName").as(String.class), guestName+"%"));
                    }
                    return predicate;
                    }
               };
               Page<Guest> result = (Page<Guest>) guestRepository.findAll(spec, pageRequest);
               return result;
     }
     public void delete(Long id){
          guestRepository.delete(id);
     }
     public Guest find(Long id){
    	  return guestRepository.findOne(id);
     }
     public void save(Guest Guest){
    	 guestRepository.save(Guest);
     }
}
package com.michal.examples.spring.security.repository;

import com.michal.examples.spring.security.entity.CashCardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CashCardRepository extends CrudRepository<CashCardEntity, Long>, PagingAndSortingRepository<CashCardEntity, Long> {

    Optional<CashCardEntity> findByIdAndOwner(Long id, String owner);

    Page<CashCardEntity> findByOwner(String owner, PageRequest pageRequest);
}



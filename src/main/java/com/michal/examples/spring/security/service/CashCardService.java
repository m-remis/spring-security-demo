package com.michal.examples.spring.security.service;

import com.michal.examples.spring.security.dto.CashCardResponseDto;
import com.michal.examples.spring.security.entity.CashCardEntity;
import com.michal.examples.spring.security.exception.CardNotFoundException;
import com.michal.examples.spring.security.repository.CashCardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CashCardService {

    private final CashCardRepository cashCardRepository;
    private final ConvertService convertService;

    public CashCardService(
            CashCardRepository cashCardRepository,
            ConvertService convertService) {
        this.cashCardRepository = cashCardRepository;
        this.convertService = convertService;
    }

    public CashCardResponseDto findByIdAndOwner(Long id, String owner) {
        return cashCardRepository
                .findByIdAndOwner(id, owner)
                .map(convertService::convert)
                .orElseThrow(() -> CardNotFoundException.of("Card not found"));
    }

    public Page<CashCardResponseDto> findByOwner(String owner, PageRequest pageRequest) {
        return cashCardRepository.findByOwner(owner, pageRequest).map(convertService::convert);
    }

    @Transactional
    public CashCardResponseDto save(Double amount, String owner) {
        return convertService.convert(
                cashCardRepository.save(new CashCardEntity(amount, owner))
        );
    }
}

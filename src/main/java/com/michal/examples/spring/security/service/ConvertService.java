package com.michal.examples.spring.security.service;

import com.michal.examples.spring.security.dto.CashCardResponseDto;
import com.michal.examples.spring.security.entity.CashCardEntity;
import org.springframework.stereotype.Component;

@Component
public class ConvertService {

    public CashCardResponseDto convert(CashCardEntity entity) {
        return new CashCardResponseDto(entity.getId(), entity.getAmount(), entity.getOwner());
    }
}

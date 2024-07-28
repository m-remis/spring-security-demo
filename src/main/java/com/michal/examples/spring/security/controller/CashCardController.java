package com.michal.examples.spring.security.controller;

import com.michal.examples.spring.security.dto.CashCardRequestDto;
import com.michal.examples.spring.security.dto.CashCardResponseDto;
import com.michal.examples.spring.security.service.CashCardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/cashcards")
class CashCardController {

    private final CashCardService cashCardService;

    private CashCardController(CashCardService cashCardService) {
        this.cashCardService = cashCardService;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<CashCardResponseDto> findById(
            @PathVariable Long requestedId,
            Principal principal
    ) {
        CashCardResponseDto cashCardOptional = cashCardService.findByIdAndOwner(requestedId, principal.getName());
        return ResponseEntity.ok(cashCardOptional);
    }

    @PostMapping
    private ResponseEntity<CashCardResponseDto> createCashCard(
            @RequestBody CashCardRequestDto newCashCardRequestDto,
            UriComponentsBuilder ucb,
            Principal principal
    ) {
        var saved = cashCardService.save(newCashCardRequestDto.amount(), principal.getName());
        URI locationOfNewCashCard = ucb
                .path("cashcards/{id}")
                .buildAndExpand(saved.id())
                .toUri();
        return ResponseEntity.created(locationOfNewCashCard).build();
    }

    @GetMapping
    private ResponseEntity<List<CashCardResponseDto>> findAll(
            Pageable pageable,
            Principal principal
    ) {
        Page<CashCardResponseDto> page = cashCardService.findByOwner(principal.getName(),
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "amount"))
                ));
        return ResponseEntity.ok(page.getContent());
    }
}

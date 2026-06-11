package com.jpbolanio.portfolio_backend.technology;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpbolanio.portfolio_backend.technology.dto.CreateTechnologyDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/technologies")
@RequiredArgsConstructor
public class TechnologyController {
    private final TechnologyService technologyService;

    @GetMapping
    public ResponseEntity<List<Technology>> findAll() {
        return ResponseEntity.ok(technologyService.findAll());
    }

    @PostMapping
    public ResponseEntity<Technology> create(@Valid @RequestBody CreateTechnologyDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(technologyService.save(dto));
    }
}

package com.jpbolanio.portfolio_backend.technology;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{uuid}")
    public ResponseEntity<Technology> update(@PathVariable UUID uuid, @Valid @RequestBody CreateTechnologyDto dto) {
        return ResponseEntity.ok(technologyService.update(uuid, dto));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable java.util.UUID uuid) {
        technologyService.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}

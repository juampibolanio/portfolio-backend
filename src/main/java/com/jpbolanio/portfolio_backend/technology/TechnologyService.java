package com.jpbolanio.portfolio_backend.technology;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jpbolanio.portfolio_backend.technology.dto.CreateTechnologyDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TechnologyService {
    private final TechnologyRepository technologyRepository;

    public List<Technology> findAll() {
        return technologyRepository.findAll();
    }

    public Technology save(CreateTechnologyDto dto) {
        Technology newTech = Technology.builder()
                .name(dto.name())
                .iconUrl(dto.iconUrl())
                .build();
        return technologyRepository.save(newTech);
    }

    public Technology update(UUID uuid, CreateTechnologyDto dto) {
        Technology technology = technologyRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Technology not found"));
        technology.setName(dto.name());
        technology.setIconUrl(dto.iconUrl());
        return technologyRepository.save(technology);
    }

    public void delete(UUID uuid) {
        Technology technology = technologyRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Technology not found"));
        technologyRepository.delete(technology);
    }
}

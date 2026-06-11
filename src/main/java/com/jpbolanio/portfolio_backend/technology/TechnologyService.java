package com.jpbolanio.portfolio_backend.technology;

import java.util.List;

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
}

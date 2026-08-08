package com.jpbolanio.portfolio_backend.technology;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnologyRepository extends JpaRepository<Technology, UUID>{}

package com.jpbolanio.portfolio_backend.project;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID>{
    Optional<Project> findBySlug(String slug);
}

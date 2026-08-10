package com.jpbolanio.portfolio_backend.project;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jpbolanio.portfolio_backend.project.dto.ProjectRequestDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<Project>> getAll() {
        List<Project> projects = projectService.findAll();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<Project> getBySlug(@PathVariable String slug) {
        Project project = projectService.findBySlug(slug);
        return ResponseEntity.ok(project);
    }

    @PostMapping
    public ResponseEntity<Project> create(@Valid @RequestBody ProjectRequestDto dto) {
        Project savedProduct = projectService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Project> getByUuid(@PathVariable UUID uuid) {
        return ResponseEntity.ok(projectService.getByUuid(uuid));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Project> update(@PathVariable UUID uuid, @Valid @RequestBody ProjectRequestDto dto) {
        Project updatedProject = projectService.update(uuid, dto);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        projectService.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}

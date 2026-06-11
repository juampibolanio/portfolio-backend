package com.jpbolanio.portfolio_backend.project;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{slug}")
    public ResponseEntity<Project> getBySlug(@PathVariable("slug") String slug) {
        Project project = projectService.findBySlug(slug);
        return ResponseEntity.ok(project);
    }

    @PostMapping
    public ResponseEntity<Project> create(@Valid @RequestBody ProjectRequestDto dto) {
        Project savedProduct = projectService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }
}

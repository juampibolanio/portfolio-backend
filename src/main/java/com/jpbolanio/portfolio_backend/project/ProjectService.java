package com.jpbolanio.portfolio_backend.project;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jpbolanio.portfolio_backend.common.exceptions.NotFoundException;
import com.jpbolanio.portfolio_backend.media.Media;
import com.jpbolanio.portfolio_backend.project.dto.ProjectRequestDto;
import com.jpbolanio.portfolio_backend.technology.Technology;
import com.jpbolanio.portfolio_backend.technology.TechnologyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final TechnologyRepository technologyRepository;

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project findBySlug(String slug) {
        return projectRepository.findBySlug(slug)
                .orElseThrow(() -> new NotFoundException("Project not found"));
    }

    public Project save(ProjectRequestDto createProjectDto) {
        List<Technology> foundTechnologies = new java.util.ArrayList<>();

        if (createProjectDto.technologies() != null && !createProjectDto.technologies().isEmpty()) {
            foundTechnologies = technologyRepository.findAllById(createProjectDto.technologies());
        }

        Project newProject = Project.builder()
                .title(createProjectDto.title())
                .slug(createProjectDto.slug())
                .shortDescription(createProjectDto.shortDescription())
                .fullDescription(createProjectDto.fullDescription())
                .githubUrl(createProjectDto.githubUrl())
                .liveUrl(createProjectDto.liveUrl())
                .featured(createProjectDto.featured())
                .technologies(new HashSet<>(foundTechnologies))
                .build();

        if (createProjectDto.mediafiles() != null && !createProjectDto.mediafiles().isEmpty()) {
            List<Media> mediaEntities = createProjectDto.mediafiles().stream()
                    .map(mediaDto -> Media.builder()
                            .url(mediaDto.url())
                            .mediaType(mediaDto.mediaType())
                            .cloudinaryPublicId(mediaDto.cloudinaryPublicId())
                            .main(mediaDto.main())
                            .project(newProject)
                            .build())
                    .toList();

            newProject.setMediaFiles(mediaEntities);
        }
        return projectRepository.save(newProject);
    }
}

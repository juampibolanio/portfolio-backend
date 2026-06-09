package com.jpbolanio.portfolio_backend.project;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.jpbolanio.portfolio_backend.media.Media;
import com.jpbolanio.portfolio_backend.technology.Technology;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "projects", indexes = {
    @Index(name = "idx_project_slug", columnList = "slug", unique = true)
})
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(name = "short_description", length = 500)
    private String shortDescription;

    @Column(name = "full_description", columnDefinition = "TEXT")
    private String fullDescription;

    @Column(name = "github_url")
    private String githubUrl;

    @Column(name = "live_url")
    private String liveUrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    private boolean featured;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Media> mediaFiles = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "project_technologies",
        joinColumns = @JoinColumn(name = "project_id"),
        inverseJoinColumns = @JoinColumn(name = "technology_id")
    )
    @Builder.Default
    private Set<Technology> technologies = new HashSet<>();

    @PrePersist
    @PreUpdate
    private void configurateAutoSlugAndCreatedTime() {
        if (this.slug == null || this.slug.isBlank()) {
            this.slug = convertToSlug(this.title);
        } else {
            this.slug = convertToSlug(this.slug);
        }

        if (createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    private String convertToSlug(String text) {
        if (text == null) return "";
        return text.toLowerCase()
                .replaceAll("[^a-z0-8\\s-]", "") 
                .replaceAll("\\s+", "-")         
                .replaceAll("-+", "-")           
                .trim();
    }
}

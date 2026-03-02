package com.gustcustodio.url_shortening_service.dtos;

import com.gustcustodio.url_shortening_service.entities.UrlEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UrlResponseDTO {

    private Long id;

    private String url;

    private String shortCode;

    private Instant createdAt;

    private Instant updatedAt;

    public UrlResponseDTO(UrlEntity urlEntity) {
        this.id = urlEntity.getId();
        this.url = urlEntity.getUrl();
        this.shortCode = urlEntity.getShortCode();
        this.createdAt = urlEntity.getCreatedAt();
        this.updatedAt = urlEntity.getUpdatedAt();
    }

}

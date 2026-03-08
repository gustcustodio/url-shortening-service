package com.gustcustodio.url_shortening_service.dtos;

import com.gustcustodio.url_shortening_service.entities.UrlEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UrlStatisticsResponseDTO extends UrlResponseDTO {

    private Integer accessCount;

    public UrlStatisticsResponseDTO(UrlEntity urlEntity) {
        super(urlEntity);
        this.accessCount = urlEntity.getAccessCount();
    }

}

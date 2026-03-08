package com.gustcustodio.url_shortening_service.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UrlRequestDTO {

    @NotBlank(message = "Required field")
    private String url;

}

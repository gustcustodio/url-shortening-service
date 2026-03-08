package com.gustcustodio.url_shortening_service.controllers;

import com.gustcustodio.url_shortening_service.dtos.UrlRequestDTO;
import com.gustcustodio.url_shortening_service.dtos.UrlResponseDTO;
import com.gustcustodio.url_shortening_service.dtos.UrlStatisticsResponseDTO;
import com.gustcustodio.url_shortening_service.services.UrlService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/shorten")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping(value = "{shortCode}")
    public ResponseEntity<UrlResponseDTO> getOriginalUrl(@PathVariable String shortCode) {
        UrlResponseDTO urlResponseDTO = urlService.getOriginalUrl(shortCode);
        return ResponseEntity.ok(urlResponseDTO);
    }

    @GetMapping(value = "{shortCode}/stats")
    public ResponseEntity<UrlStatisticsResponseDTO> getUrlStatistics(@PathVariable String shortCode) {
        UrlStatisticsResponseDTO urlStatisticsResponseDTO = urlService.getUrlStatistics(shortCode);
        return ResponseEntity.ok(urlStatisticsResponseDTO);
    }

    @PostMapping
    public ResponseEntity<UrlResponseDTO> createShortUrl(@Valid @RequestBody UrlRequestDTO urlRequestDTO) {
        UrlResponseDTO urlResponseDTO = urlService.createShortUrl(urlRequestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(urlResponseDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(urlResponseDTO);
    }

    @PutMapping(value = "{shortCode}")
    public ResponseEntity<UrlResponseDTO> updateUrl(@PathVariable String shortCode, @Valid @RequestBody UrlRequestDTO urlRequestDTO) {
        UrlResponseDTO urlResponseDTO = urlService.updateUrl(shortCode, urlRequestDTO);
        return ResponseEntity.ok(urlResponseDTO);
    }

    @DeleteMapping(value = "{shortCode}")
    public ResponseEntity<Void> deleteUrl(@PathVariable String shortCode) {
        urlService.deleteUrl(shortCode);
        return ResponseEntity.noContent().build();
    }

}

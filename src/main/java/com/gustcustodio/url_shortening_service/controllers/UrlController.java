package com.gustcustodio.url_shortening_service.controllers;

import com.gustcustodio.url_shortening_service.dtos.UrlRequestDTO;
import com.gustcustodio.url_shortening_service.dtos.UrlResponseDTO;
import com.gustcustodio.url_shortening_service.services.UrlService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    private ResponseEntity<UrlResponseDTO> getOriginalUrl(@PathVariable String shortCode) {
        UrlResponseDTO urlResponseDTO = urlService.getOriginalUrl(shortCode);
        return ResponseEntity.ok(urlResponseDTO);
    }

    @PostMapping
    private ResponseEntity<UrlResponseDTO> createShortUrl(@Valid @RequestBody UrlRequestDTO urlRequestDTO) {
        UrlResponseDTO urlResponseDTO = urlService.createShortUrl(urlRequestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(urlResponseDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(urlResponseDTO);
    }

    @PutMapping(value = "{shortCode}")
    private ResponseEntity<UrlResponseDTO> updateUrl(@PathVariable String shortCode, @Valid @RequestBody UrlRequestDTO urlRequestDTO) {
        UrlResponseDTO urlResponseDTO = urlService.updateUrl(shortCode, urlRequestDTO);
        return ResponseEntity.ok(urlResponseDTO);
    }

    @DeleteMapping(value = "{shortCode}")
    private ResponseEntity<Void> deleteUrl(@PathVariable String shortCode) {
        urlService.deleteUrl(shortCode);
        return ResponseEntity.noContent().build();
    }

}

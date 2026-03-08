package com.gustcustodio.url_shortening_service.controllers;

import com.gustcustodio.url_shortening_service.dtos.UrlRequestDTO;
import com.gustcustodio.url_shortening_service.dtos.UrlResponseDTO;
import com.gustcustodio.url_shortening_service.services.UrlService;
import org.springframework.http.ResponseEntity;
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
    private ResponseEntity<UrlResponseDTO> getOriginalUrl(@PathVariable String shortCode) {
        UrlResponseDTO urlResponseDTO = urlService.getOriginalUrl(shortCode);
        return ResponseEntity.ok(urlResponseDTO);
    }

    @PostMapping
    private ResponseEntity<UrlResponseDTO> createShortUrl(@RequestBody UrlRequestDTO urlRequestDTO) {
        UrlResponseDTO urlResponseDTO = urlService.createShortUrl(urlRequestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(urlResponseDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(urlResponseDTO);
    }

    @PutMapping(value = "{shortCode}")
    private ResponseEntity<UrlResponseDTO> updateUrl(@PathVariable String shortCode, @RequestBody UrlRequestDTO urlRequestDTO) {
        UrlResponseDTO urlResponseDTO = urlService.updateUrl(shortCode, urlRequestDTO);
        return ResponseEntity.ok(urlResponseDTO);
    }

}

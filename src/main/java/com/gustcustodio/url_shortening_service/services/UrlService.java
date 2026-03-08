package com.gustcustodio.url_shortening_service.services;

import com.gustcustodio.url_shortening_service.dtos.UrlRequestDTO;
import com.gustcustodio.url_shortening_service.dtos.UrlResponseDTO;
import com.gustcustodio.url_shortening_service.entities.UrlEntity;
import com.gustcustodio.url_shortening_service.repositories.UrlRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class UrlService {

    private final UrlRepository urlRepository;
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 6;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Transactional(readOnly = true)
    public UrlResponseDTO getOriginalUrl(String shortCode) {
        UrlEntity urlEntity = urlRepository.findByShortCode(shortCode).orElseThrow();
        return new UrlResponseDTO(urlEntity);
    }

    @Transactional
    public UrlResponseDTO createShortUrl(UrlRequestDTO urlRequestDTO) {
        String shortCode;

        do {
            shortCode = generateRandomCode();
        } while (urlRepository.existsByShortCode(shortCode));

        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setUrl(urlRequestDTO.getUrl());
        urlEntity.setShortCode(shortCode);
        urlEntity = urlRepository.save(urlEntity);

        return new UrlResponseDTO(urlEntity);
    }

    @Transactional
    public UrlResponseDTO updateUrl(String shortCode, UrlRequestDTO urlRequestDTO) {
        UrlEntity urlEntity = urlRepository.findByShortCode(shortCode).orElseThrow();
        urlEntity.setUrl(urlRequestDTO.getUrl());
        urlEntity = urlRepository.saveAndFlush(urlEntity);
        return new UrlResponseDTO(urlEntity);
    }

    private String generateRandomCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

}

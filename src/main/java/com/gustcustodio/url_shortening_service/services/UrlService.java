package com.gustcustodio.url_shortening_service.services;

import com.gustcustodio.url_shortening_service.repositories.UrlRepository;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

}

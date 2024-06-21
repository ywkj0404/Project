package kr.co.shortenurlservice.presentation.Dto;


import jakarta.validation.Valid;
import kr.co.shortenurlservice.application.ShortenUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ShortenUrlController {

    private ShortenUrlService shortenUrlService;

    @Autowired
    public ShortenUrlController(ShortenUrlService shortenUrlService) {
        this.shortenUrlService = shortenUrlService;
    }

    @RequestMapping(value = "/shorten-url", method = RequestMethod.POST)
    public ShortenUrlDto createShortenUrl(
            @RequestBody ShortenUrlCreateRequestDto shortenUrlCreateRequestDto
    ) {
        return shortenUrlService.createShortenKey(shortenUrlCreateRequestDto);
    }

    @RequestMapping(value = "/{shortenKey}", method = RequestMethod.GET)
    public ResponseEntity<?> redirectOriginalUrl(
            @PathVariable String shortenKey
    ) throws URISyntaxException {
       String originalUrl = shortenUrlService.redirectOriginalUrl(shortenKey);
       URI redirectUri = new URI(originalUrl);
       HttpHeaders httpHeaders = new HttpHeaders();
       httpHeaders.setLocation(redirectUri);

       return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }

    @RequestMapping(value = "/shorten-url/{shortenKey}", method = RequestMethod.GET)
    public ShortenUrlDto findShortenUrl(
            @PathVariable String shortenKey
    ) {
        return shortenUrlService.findByShortenKey(shortenKey);
    }

    @RequestMapping(value = "/shorten-url", method = RequestMethod.GET)
    public List<ShortenUrlDto> findAll() {
        return shortenUrlService.findAll();
    }

}

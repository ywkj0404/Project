package kr.co.shortenurlservice.presentation.Dto;


import kr.co.shortenurlservice.application.ShortenUrlService;
import kr.co.shortenurlservice.domain.ShortenUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        ShortenUrlDto shortenUrlDto = shortenUrlService.createShortenKey(shortenUrlCreateRequestDto);

        return shortenUrlDto;
    }

    @RequestMapping(value = "/shorten-url", method = RequestMethod.GET)
    public List<ShortenUrlDto> findShortenUrl(
            @RequestParam(required = false) String shortenKey
    ) {
        if(shortenKey == null) return shortenUrlService.findAll();

        return shortenUrlService.findByShortenKey(shortenKey);
    }

}

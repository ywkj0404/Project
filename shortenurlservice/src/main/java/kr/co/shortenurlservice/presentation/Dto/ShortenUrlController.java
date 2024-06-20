package kr.co.shortenurlservice.presentation.Dto;


import kr.co.shortenurlservice.application.ShortenUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}

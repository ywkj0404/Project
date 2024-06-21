package kr.co.shortenurlservice.application;

import kr.co.shortenurlservice.domain.KeyNotFoundException;
import kr.co.shortenurlservice.domain.LackOfShortenUrlKeyException;
import kr.co.shortenurlservice.domain.ShortenUrl;
import kr.co.shortenurlservice.infrastructure.MapShortenUrlRepository;
import kr.co.shortenurlservice.presentation.Dto.ShortenUrlCreateRequestDto;
import kr.co.shortenurlservice.presentation.Dto.ShortenUrlDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShortenUrlService {

    private MapShortenUrlRepository mapShortenUrlRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ShortenUrlService(
            MapShortenUrlRepository mapShortenUrlRepository,
            ModelMapper modelMapper
    ) {
        this.mapShortenUrlRepository = mapShortenUrlRepository;
        this.modelMapper = modelMapper;
    }

    public ShortenUrlDto createShortenKey(ShortenUrlCreateRequestDto shortenUrlCreateRequestDto) {
        String shortenKey = validShortenKey();
        ShortenUrlDto shortenUrlDto = new ShortenUrlDto(shortenUrlCreateRequestDto, shortenKey);
        ShortenUrl shortenUrl = modelMapper.map(shortenUrlDto, ShortenUrl.class);
        ShortenUrl savedShortenUrl = mapShortenUrlRepository.save(shortenUrl);
        ShortenUrlDto savedShortenUrlDto = modelMapper.map(savedShortenUrl, ShortenUrlDto.class);

        return savedShortenUrlDto;
    }

    public String redirectOriginalUrl(String shortenKey) {
        ShortenUrlDto shortenUrlDto = findByShortenKey(shortenKey);
        shortenUrlDto.setRedirectCount(shortenUrlDto.getRedirectCount() + 1L);
        ShortenUrl shortenUrl = modelMapper.map(shortenUrlDto, ShortenUrl.class);
        mapShortenUrlRepository.save(shortenUrl);

        return shortenUrlDto.getOriginalUrl();
    }

    public List<ShortenUrlDto> findAll() {
        List<ShortenUrl> shortenUrlList = mapShortenUrlRepository.findAll();
        List<ShortenUrlDto> shortenUrlDtoList = shortenUrlList.stream()
                .map(shortenUrl -> modelMapper.map(shortenUrl, ShortenUrlDto.class))
                .toList();

        return shortenUrlDtoList;
    }

    public ShortenUrlDto findByShortenKey(String shortenKey) {
        ShortenUrl shortenUrl = mapShortenUrlRepository.findByShortenKey(shortenKey);

        if(shortenUrl != null) {
            ShortenUrlDto shortenUrlDto = modelMapper.map(shortenUrl, ShortenUrlDto.class);
            return shortenUrlDto;
        } else {
            throw new KeyNotFoundException();
        }

    }

    public String validShortenKey() {
        String shortenKey = "";

        for (int i = 0; i < 10; i++) {
            shortenKey = ShortenUrlDto.createShortenKey();

            if (!mapShortenUrlRepository.containingKey(shortenKey)) {
                break;
            } else {
                shortenKey = null;
            }
        }

        if(shortenKey != null)
            return shortenKey;
        else
            throw new LackOfShortenUrlKeyException();
    }

}

package kr.co.shortenurlservice.application;

import kr.co.shortenurlservice.domain.ShortenUrl;
import kr.co.shortenurlservice.infrastructure.MapShortenUrlRepository;
import kr.co.shortenurlservice.presentation.Dto.ShortenUrlCreateRequestDto;
import kr.co.shortenurlservice.presentation.Dto.ShortenUrlDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        ShortenUrlDto shortenUrlDto = new ShortenUrlDto(shortenUrlCreateRequestDto);
        ShortenUrl shortenUrl = modelMapper.map(shortenUrlDto, ShortenUrl.class);
        ShortenUrl savedShortenUrl = mapShortenUrlRepository.save(shortenUrl);
        ShortenUrlDto savedShortenUrlDto = modelMapper.map(savedShortenUrl, ShortenUrlDto.class);

        return savedShortenUrlDto;
    }

}
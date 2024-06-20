package kr.co.shortenurlservice.infrastructure;

import kr.co.shortenurlservice.domain.ShortenUrl;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MapShortenUrlRepository {

    private Map<String, ShortenUrl> shortenUrlMap = new ConcurrentHashMap<>();

    public ShortenUrl save(ShortenUrl shortenUrl) {
        shortenUrlMap.put(shortenUrl.getShortenKey(), shortenUrl);

        return shortenUrl;
    }

}

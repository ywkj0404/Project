package kr.co.shortenurlservice.infrastructure;

import kr.co.shortenurlservice.domain.ShortenUrl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MapShortenUrlRepository {

    private Map<String, ShortenUrl> shortenUrlMap = new ConcurrentHashMap<>();

    public ShortenUrl save(ShortenUrl shortenUrl) {
        shortenUrlMap.put(shortenUrl.getShortenKey(), shortenUrl);

        return shortenUrl;
    }

    public List<ShortenUrl> findAll() {
        Collection<ShortenUrl> values = shortenUrlMap.values();
        List<ShortenUrl> shortenUrlList = new ArrayList<>(values);

        return shortenUrlList;
    }

    public List<ShortenUrl> findByShortenKey(String shortenKey) {
        return null;
    }

}

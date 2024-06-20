package kr.co.shortenurlservice.domain;

public class ShortenUrl {

    private String originalUrl;
    private String shortenKey;
    private Long redirectCount;

    public String getShortenKey() {
        return shortenKey;
    }

}

package kr.co.shortenurlservice.presentation.Dto;

import java.util.Random;

public class ShortenUrlDto {

    private String originalUrl;
    private String shortenKey;
    private Long redirectCount;

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortenKey() {
        return shortenKey;
    }

    public Long getRedirectCount() {
        return redirectCount;
    }

    public void setRedirectCount(Long redirectCount) {
        this.redirectCount = redirectCount;
    }

    public ShortenUrlDto() {}

    public ShortenUrlDto(ShortenUrlCreateRequestDto shortenUrlCreateRequestDto, String shortenKey) {
        this.originalUrl = shortenUrlCreateRequestDto.getOriginalUrl();
        this.shortenKey = shortenKey;
        this.redirectCount = 0L;
    }

    public static String createShortenKey() {
        Random random = new Random();
        String base56CharacterSet = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcedfghijkmnpqrstuvwxyz";
        StringBuilder shortenKey = new StringBuilder();

        for(int count = 0; count < 8; count++) {
            int base56CharacterSetIndex = random.nextInt(0, base56CharacterSet.length());
            char base56Character = base56CharacterSet.charAt(base56CharacterSetIndex);

            shortenKey.append(base56Character);
        }

        return shortenKey.toString();
    }

}

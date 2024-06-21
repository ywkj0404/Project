package kr.co.shortenurlservice.presentation.Dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public class ShortenUrlCreateRequestDto {

    @NotNull
    @URL(regexp = "^(https?:\\/\\/)?([a-zA-Z0-9\\-]+\\.)+[a-zA-Z]{2,6}(\\/[a-zA-Z0-9\\-._~:\\/?#\\[\\]@!$&'()*+,;=%]*)?$\n")
    private String originalUrl;

    public String getOriginalUrl() {
        return originalUrl;
    }

}

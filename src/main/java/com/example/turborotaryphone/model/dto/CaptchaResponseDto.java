package com.example.turborotaryphone.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CaptchaResponseDto {

    private boolean success;

    @JsonAlias("error-codes")
    private Set<String> errorCodes;

}

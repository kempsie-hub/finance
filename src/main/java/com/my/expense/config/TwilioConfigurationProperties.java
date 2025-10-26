package com.my.expense.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@Component
@ConfigurationProperties(prefix = "twilio")
public class TwilioConfigurationProperties {
    @NotBlank
//    @Pattern(regexp = "^AC[0-9a-fA-F]{34}$")
    private String accountSid;

    @NotBlank
    private String authToken;

    @NotBlank
//    @Pattern(regexp = "^MG[0-9a-fA-F]{32}$")
    private String phoneNumber;
}

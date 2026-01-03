package demowebshop;

import config.WebDriverConfig;
import models.UserGenerator;
import org.aeonbits.owner.ConfigFactory;

public class UserData {
    private static final WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class);

    public static final UserGenerator DEFAULT_USER = UserGenerator.builder()
            .email(config.defaultUserEmail())
            .password(config.defaultUserPassword())
            .build();
}



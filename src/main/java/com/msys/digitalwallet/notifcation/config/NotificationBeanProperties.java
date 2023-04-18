package com.msys.digitalwallet.notifcation.config;

import com.msys.digitalwallet.notifcation.enums.ServiceName;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "notification.integration")
@Configuration
public class NotificationBeanProperties {

    private ServiceName serviceName;
}

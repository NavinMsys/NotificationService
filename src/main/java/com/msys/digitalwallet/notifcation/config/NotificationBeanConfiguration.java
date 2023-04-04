package com.msys.digitalwallet.notifcation.config;

import com.msys.digitalwallet.notifcation.integration.IntegrationClient;
import com.msys.digitalwallet.notifcation.twilio.TwilioService;
import com.msys.digitalwallet.notifcation.twofactor.TwoFactorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class NotificationBeanConfiguration {

    @Value("${notification.service}")
    String notificationBean;

    @Primary
    @Bean
    public IntegrationClient twilioClient(){
        if(notificationBean.equals("Twilio"))
            return new TwilioService();
        else
            return new TwoFactorService();
    }

}

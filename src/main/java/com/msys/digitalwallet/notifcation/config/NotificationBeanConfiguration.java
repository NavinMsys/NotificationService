package com.msys.digitalwallet.notifcation.config;

import com.msys.digitalwallet.notifcation.common.exception.BusinessException;
import com.msys.digitalwallet.notifcation.enums.ErrorType;
import com.msys.digitalwallet.notifcation.enums.ServiceName;
import com.msys.digitalwallet.notifcation.fake.FakeNotificationService;
import com.msys.digitalwallet.notifcation.integration.IntegrationClient;
import com.msys.digitalwallet.notifcation.twilio.TwilioService;
import com.msys.digitalwallet.notifcation.twofactor.TwoFactorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class NotificationBeanConfiguration {

    @Autowired
    NotificationBeanProperties notificationBeanProperties;

    @Primary
    @Bean
    public IntegrationClient integrationClient(){
        if(notificationBeanProperties.getServiceName()== ServiceName.TWILIO)
            return new TwilioService();
        else if(notificationBeanProperties.getServiceName()== ServiceName.TWOFACTOR)
            return new TwoFactorService();
        else  if(notificationBeanProperties.getServiceName()== ServiceName.FAKE)
            return new FakeNotificationService();
        else
            throw new BusinessException(ErrorType.UNSUPPORTED_CONFIGURATION
                    ,notificationBeanProperties.getServiceName()+" is not configured.");
    }

}

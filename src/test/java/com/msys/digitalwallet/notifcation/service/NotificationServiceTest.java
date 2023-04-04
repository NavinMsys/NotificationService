package com.msys.digitalwallet.notifcation.service;

import com.msys.digitalwallet.notifcation.enums.Channel;
import com.msys.digitalwallet.notifcation.integration.IntegrationClient;
import com.msys.digitalwallet.notifcation.model.Notification;
import com.msys.digitalwallet.notifcation.twilio.TwilioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

    @Mock
    IntegrationClient integrationClient;

    @InjectMocks
    public NotificationService notificationService;

    @Test
    public void sendSMSNotification()  {

        Notification notification = Notification.builder().message("test")
                .identifier("+919790794687").channel(Channel.sms).build();
        Mockito.when(integrationClient.sendNotification(any())).thenReturn("pending");

        String response = notificationService.sendNotification(notification);
        Assertions.assertEquals("pending",response);

    }

    @Test
    public void sendEmailNotification_success() {

        Notification notification = Notification.builder().message("test")
                .identifier("tprabu@msystechnologies.com").channel(Channel.sms).build();
        Mockito.when(integrationClient.sendNotification(any())).thenReturn("202");

        String response = notificationService.sendNotification(notification);
        Assertions.assertEquals("202",response);

    }


}

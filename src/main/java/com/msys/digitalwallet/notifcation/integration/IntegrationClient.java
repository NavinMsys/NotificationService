package com.msys.digitalwallet.notifcation.integration;

import com.msys.digitalwallet.notifcation.enums.Channel;
import com.msys.digitalwallet.notifcation.model.Notification;

public interface IntegrationClient {
    String sendNotification(Notification notification) ;

    String verifyOTP(String identifier, String token);

    String sendOTP(String identifier, Channel channel );

}

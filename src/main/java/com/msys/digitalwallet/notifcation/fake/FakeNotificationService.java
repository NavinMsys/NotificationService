package com.msys.digitalwallet.notifcation.fake;

import com.msys.digitalwallet.notifcation.common.exception.BusinessException;
import com.msys.digitalwallet.notifcation.enums.Channel;
import com.msys.digitalwallet.notifcation.enums.ErrorType;
import com.msys.digitalwallet.notifcation.integration.FakeClient;
import com.msys.digitalwallet.notifcation.model.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FakeNotificationService implements FakeClient {
    @Override
    public String sendNotification(Notification notification) {

        log.debug("Sending fake response for sendNotification: QUEUED");
        if(notification.getChannel()==Channel.email){
            return "202";
        }
        return "PENDING";
    }

    @Override
    public String verifyOTP(String identifier, String token) {
        if(!token.equals("123456")){
            throw new BusinessException(ErrorType.INVALID_OTP,"OTP is Invalid");
        }
        log.debug("Sending fake response for sendNotification: VERIFIED");
        return "VERIFIED";
    }
 
    @Override
    public String sendOTP(String identifier, Channel channel) {
        log.debug("Sending fake response for sendNotification: 12345");
        return "123456";
    }
}

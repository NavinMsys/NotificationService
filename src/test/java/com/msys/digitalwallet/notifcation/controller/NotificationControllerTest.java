package com.msys.digitalwallet.notifcation.controller;
import com.msys.digitalwallet.notifcation.enums.Channel;
import com.msys.digitalwallet.notifcation.model.Notification;
import com.msys.digitalwallet.notifcation.model.OtpVerification;
import com.msys.digitalwallet.notifcation.service.NotificationService;
import com.twilio.exception.ApiException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class NotificationControllerTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    @Test
    void send_notification() {
        Notification notification = Notification.builder().message("test")
                .identifier("9790794687").channel(Channel.sms).build();
        Mockito.when(notificationService.sendNotification(any())).thenReturn("QUEUED");

        ResponseEntity<String> response  = notificationController.sendSMSNotification(notification);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("QUEUED", response.getBody());
    }

    @Test
    void send_notification_email_failure(){
        Notification notification = Notification.builder().message("test")
                .identifier("+919790794687").channel(Channel.email).build();
        Mockito.when(notificationService.sendNotification(any())).thenReturn("400");

        ResponseEntity<String> response  = notificationController.sendSMSNotification(notification);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("400", response.getBody());
    }

    @Test
    void send_notification_sms_failure() {
        Notification notification = Notification.builder().message("test")
                .identifier("687").channel(Channel.sms).build();
        Mockito.when(notificationService.sendNotification(any())).thenThrow(new ApiException(""));
        Assertions.assertThrows(ApiException.class,() -> notificationController.sendSMSNotification(notification));
    }

    @Test
    void send_otp_sms_success() {
        OtpVerification otpVerification = OtpVerification.builder().Channel(Channel.sms).identifier("+919790794687").build();
        Mockito.when(notificationService.sendOTP(any(),any())).thenReturn("pending");
        ResponseEntity<String> response  = notificationController.sendOTP(otpVerification);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("pending", response.getBody());
    }

    @Test
    void send_otp_sms_failure() {
        OtpVerification otpVerification = OtpVerification.builder().Channel(Channel.sms).identifier("+919790794687").build();
        Mockito.when(notificationService.sendOTP(any(),any())).thenThrow(new ApiException(""));
        Assertions.assertThrows(ApiException.class,() -> notificationController.sendOTP(otpVerification));

    }

    @Test
    void verify_otp_sms_success() {
        OtpVerification otpVerification = OtpVerification.builder().Channel(Channel.sms).identifier("+919790794687").build();
        Mockito.when(notificationService.verifyOTP(any(),any())).thenReturn("approved");
        ResponseEntity<String> response  = notificationController.verifyOTP(otpVerification);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("approved", response.getBody());
    }

    @Test
    void verify_otp_sms_failure() {
        OtpVerification otpVerification = OtpVerification.builder().Channel(Channel.sms).identifier("+919790794687").build();
        Mockito.when(notificationService.verifyOTP(any(),any())).thenThrow(new ApiException(""));
        Assertions.assertThrows(ApiException.class,() -> notificationController.verifyOTP(otpVerification));

    }

}

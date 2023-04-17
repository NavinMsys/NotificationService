package com.msys.digitalwallet.notifcation.controller;

import com.msys.digitalwallet.notifcation.model.Notification;
import com.msys.digitalwallet.notifcation.model.OtpVerification;
import com.msys.digitalwallet.notifcation.service.NotificationService;
import io.micrometer.core.annotation.Timed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
@Timed
public class NotificationController{

    @Autowired
    NotificationService notificationService;

    @PostMapping
    public ResponseEntity<String> sendSMSNotification(@RequestBody @Valid Notification notification) {
        return ResponseEntity.ok(notificationService.sendNotification(notification));
    }

    @PostMapping(path = "/OTP")
    public ResponseEntity<String> sendOTP(@RequestBody @Valid OtpVerification otpVerification) {
        return ResponseEntity.ok(notificationService.sendOTP(otpVerification.getIdentifier(),otpVerification.getChannel()));
    }

    @PostMapping(path = "/verify")
    public ResponseEntity<String> verifyOTP(@RequestBody @Valid OtpVerification otpVerification) {
        return ResponseEntity.ok(notificationService.verifyOTP(otpVerification.getIdentifier(),otpVerification.getToken()));
    }

}

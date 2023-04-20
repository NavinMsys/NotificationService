package com.msys.digitalwallet.notification.controller;

import com.msys.digitalwallet.notification.common.response.ApiResponse;
import com.msys.digitalwallet.notification.model.Notification;
import com.msys.digitalwallet.notification.model.OtpVerification;
import com.msys.digitalwallet.notification.service.NotificationService;
import io.micrometer.core.annotation.Timed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ApiResponse sendSMSNotification(@RequestBody @Valid Notification notification) {
        return ApiResponse.toApiResponse(notificationService.sendNotification(notification));
    }

    @PostMapping(path = "/OTP")
    public ApiResponse sendOTP(@RequestBody @Valid OtpVerification otpVerification) {
        return ApiResponse.toApiResponse(notificationService.sendOTP(otpVerification.getIdentifier(),otpVerification.getChannel()));
    }

    @PostMapping(path = "/verify")
    public ApiResponse verifyOTP(@RequestBody @Valid OtpVerification otpVerification) {
        return ApiResponse.toApiResponse(notificationService.verifyOTP(otpVerification.getIdentifier(),otpVerification.getToken()));
    }

}

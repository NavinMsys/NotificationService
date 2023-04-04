package com.msys.digitalwallet.notifcation.model;

import com.msys.digitalwallet.notifcation.enums.Channel;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OtpVerification {

    private String identifier;

    private Channel Channel;

    private String token;

}

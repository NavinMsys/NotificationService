package com.msys.digitalwallet.notifcation.model;

import com.msys.digitalwallet.notifcation.enums.Channel;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Notification {

    private String identifier;

    private String subject;

    private String message;

    private String cc;

    private Channel channel;
}

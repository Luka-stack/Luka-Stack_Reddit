package com.lukastack.lukastackreddit.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotificationEmail {

    private String subject;

    private String recipient;

    private String body;
}

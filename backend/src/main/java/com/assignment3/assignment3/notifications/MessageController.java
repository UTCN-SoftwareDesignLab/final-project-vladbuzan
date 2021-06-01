package com.assignment3.assignment3.notifications;

import com.assignment3.assignment3.notifications.dto.MessageRental;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @MessageMapping("/send")
    @SendTo("/receive")
    public MessageRental message(MessageRental patientMessage) {
        return patientMessage;
    }
}

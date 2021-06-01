package com.assignment3.assignment3.rental;

import com.assignment3.assignment3.rental.model.Rental;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    public void sendMail(Rental rental) {
        var message = new SimpleMailMessage();
        message.setTo(rental.getCustomer().getEmail());
        message.setSubject("Rental confirmation");
        message.setText(createMessage(rental));
        javaMailSender.send(message);
    }

    private String createMessage(Rental rental) {
        var sb = new StringBuilder();
        sb.append("This is a confirmation of the rental of the car:")
                .append(rental.getCar().getManufacturer().getName())
                .append(" ")
                .append(rental.getCar().getModel())
                .append(" from the date of: ")
                .append(rental.getRentalStart().toString())
                .append(" until ")
                .append(rental.getRentalEnd().toString())
                .append(" from the office: ")
                .append(rental.getCar().getOffice().getName());
        return sb.toString();
    }
}

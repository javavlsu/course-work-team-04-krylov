package com.polyclinic.notifier.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    private final JavaMailSender mailSender;

    public EmailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendDoctorAppointmentEmail(String toEmail,
                                           String subject,
                                           String body) {
        try {

            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom("polyclinic.mis@gmail.com");

//            String message = "Напоминание! Прием у врача " + doctorAppointment.getDoctor().ReturnFIOAndSpeciality() + "Состоится " + strDate + " в " + strTime;

            mailMessage.setTo(toEmail);
            mailMessage.setText(body);
            mailMessage.setSubject(subject);

            mailSender.send(mailMessage);
            System.out.println("Mail Sent successfully");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}

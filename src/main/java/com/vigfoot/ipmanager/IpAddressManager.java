package com.vigfoot.ipmanager;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class IpAddressManager {

    @Value("${spring.mail.username}")
    private String emailAddress;
    private static String localIpAddress = "127.0.0.1";
    private final JavaMailSender javaMailSender;

    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
    void verifyConnection() throws IOException, MessagingException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("vigfoot.github.io", 443));
        String hostAddress = socket.getLocalAddress().getHostAddress();

        if (!Objects.equals(localIpAddress, hostAddress)){
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject("[IP Manager] converted IP Address of GE40");
            helper.setTo(emailAddress);
            helper.setText("<p>[AS-IS] " + localIpAddress + "</p><p>[TO-BE]" + hostAddress + "<p>", true);
            localIpAddress = hostAddress;
            javaMailSender.send(message);
        }
        socket.close();
    }
}
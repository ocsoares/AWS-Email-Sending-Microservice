package com.ocsoares.awsemailsendingmicroservice.infrastructure.gateways.email.javamail;

import com.ocsoares.awsemailsendingmicroservice.application.gateways.email.IEmailRepositoryGateway;
import com.ocsoares.awsemailsendingmicroservice.domain.entity.EmailDomainEntity;
import com.ocsoares.awsemailsendingmicroservice.domain.exceptions.email.SendEmailException;
import com.ocsoares.awsemailsendingmicroservice.main.config.AppEnvironmentVariables;
import com.ocsoares.awsemailsendingmicroservice.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class JavaMailServiceGatewayTest {
    private final EmailDomainEntity emailBody = TestUtils.createEmailBody();
    private final SimpleMailMessage simpleMailMessage = TestUtils.createSimpleMailMessage();

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private IEmailRepositoryGateway emailRepositoryGateway;

    @Mock
    private AppEnvironmentVariables appEnvironmentVariables;

    @InjectMocks
    private JavaMailServiceGateway javaMailServiceGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("It SHOULD NOT be possible to send an email if throw a send email exception")
    @Test
    void sendEmail_Fail_WhenThrowSendEmailException() {
        doThrow(new MailSendException("Simulated error")).when(this.javaMailSender).send(this.simpleMailMessage);

        SendEmailException sendEmailException = assertThrows(SendEmailException.class,
                () -> this.javaMailServiceGateway.sendEmail(this.emailBody)
        );

        assertEquals(SendEmailException.EXCEPTION_MESSAGE, sendEmailException.getMessage());
        verify(this.javaMailSender, times(1)).send(this.simpleMailMessage);
        verify(this.emailRepositoryGateway, times(1)).saveEmail(this.emailBody, false);
    }

    @DisplayName("It should be possible to send an email")
    @Test
    void sendEmail() throws SendEmailException {
        this.javaMailServiceGateway.sendEmail(this.emailBody);

        verify(this.javaMailSender, times(1)).send(this.simpleMailMessage);
        verify(this.emailRepositoryGateway, times(1)).saveEmail(this.emailBody, true);
    }
}
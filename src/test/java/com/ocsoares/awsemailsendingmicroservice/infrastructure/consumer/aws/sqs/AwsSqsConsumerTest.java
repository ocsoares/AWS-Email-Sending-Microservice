package com.ocsoares.awsemailsendingmicroservice.infrastructure.consumer.aws.sqs;

import com.ocsoares.awsemailsendingmicroservice.application.gateways.email.IEmailServiceGateway;
import com.ocsoares.awsemailsendingmicroservice.domain.entity.EmailDomainEntity;
import com.ocsoares.awsemailsendingmicroservice.domain.exceptions.email.SendEmailException;
import com.ocsoares.awsemailsendingmicroservice.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AwsSqsConsumerTest {
    private final String sqsMessage = TestUtils.createSqsMessage();
    private final EmailDomainEntity emailBody = TestUtils.createEmailBody();

    @Mock
    private IEmailServiceGateway emailServiceGateway;

    @Mock
    private AwsSqsListenerResponse awsSqsListenerResponse;

    @InjectMocks
    private AwsSqsConsumer awsSqsConsumer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("It SHOULD NOT be possible to send an email if throw a send email exception")
    @Test
    void listener_Fail_WhenThrowSendEmailException() throws SendEmailException {
        when(this.awsSqsListenerResponse.getMessage()).thenReturn(this.sqsMessage);

        doThrow(new SendEmailException()).when(this.emailServiceGateway).sendEmail(this.emailBody);

        SendEmailException listenerException = assertThrows(SendEmailException.class,
                () -> this.awsSqsConsumer.listener(this.awsSqsListenerResponse)
        );

        assertEquals(SendEmailException.EXCEPTION_MESSAGE, listenerException.getMessage());
        verify(this.emailServiceGateway, times(1)).sendEmail(this.emailBody);
    }

    @DisplayName("It should be possible to receive a message in the queue and send the email")
    @Test
    void listener() throws SendEmailException {
        when(this.awsSqsListenerResponse.getMessage()).thenReturn(this.sqsMessage);

        this.awsSqsConsumer.listener(this.awsSqsListenerResponse);

        verify(this.emailServiceGateway, times(1)).sendEmail(this.emailBody);
    }
}
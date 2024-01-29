package com.ocsoares.awsemailsendingmicroservice.infrastructure.controllers.email;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import com.ocsoares.awsemailsendingmicroservice.infrastructure.controllers.email.dtos.EmailDTO;
import com.ocsoares.awsemailsendingmicroservice.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class SendEmailControllerTest {
    private final EmailDTO emailDTO = TestUtils.createEmailDTO();

    @Mock
    private AmazonSNS amazonSNS;

    @Mock
    private Topic snsCatalogTopic;

    @InjectMocks
    private SendEmailController sendEmailController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("It should be possible to publish a message")
    @Test
    void handle() {
        this.sendEmailController.handle(this.emailDTO);

        verify(this.amazonSNS, times(1)).publish(this.snsCatalogTopic.getTopicArn(), this.emailDTO.toString());
    }
}
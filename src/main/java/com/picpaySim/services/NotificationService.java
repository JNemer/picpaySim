package com.picpaySim.services;

import com.picpaySim.domain.user.User;
import com.picpaySim.dto.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);

       /*
       o link desse mocklab está fora do ar

       ResponseEntity<String> notifcationResponse = restTemplate.postForEntity("http://o4d9z.mocklab.io/notify", notificationRequest, String.class);

       if (!(notifcationResponse.getStatusCode() == HttpStatus.OK)) {
            System.out.println("Notification error");
            throw new Exception("Notification service is offline");
       }
       */

        System.out.println("Notification sended");
    }
}

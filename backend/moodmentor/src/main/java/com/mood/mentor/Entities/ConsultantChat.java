package com.mood.mentor.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultantChat {

        private Long consultation_id;       // primary key and auto increment
        private Long chat_id;       // foreign key
        private Long user_id;       // foreign key
        private Long professional_id;    // foreign key
        private String consultation_type;   // Specific Type of text only
        private String status;
        private String user_message;
        private String professional_response;
        private String professional_rating;
        private LocalDateTime scheduled_time;
        private LocalDateTime completed_time;
        private String notes;

}

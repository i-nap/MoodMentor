package com.mood.mentor.Entities;


import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChatSupportEntities {
    private Long chat_id;
    private Long user_id;
    private String topic;
    private String user_message;
    private String support_message;
    private LocalDateTime timestamp;

}

package com.mood.mentor.Entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoodEntries {

    private Long mood_id;   //primary key and auto increment
    private Long user_id;   //foreign key
    private int mood_rating;
    private int energy_rating;
    private int stress_level;
    private int sleep_quality;
    private String notes;
    private LocalDateTime createdAt;    //timestamp and auto first time
    private LocalDateTime updatedAt;    //timestamp and updated each time auto

}

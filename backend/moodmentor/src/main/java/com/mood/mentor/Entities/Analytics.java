package com.mood.mentor.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Analytics {
        private Long user_id;       // primary
    private LocalDateTime date_range_start;
    private LocalDateTime date_range_end;
    private int average_mood;
    private int average_energy;
    private int average_stress;
    private int average_quality;
    private String mood_trend;
    private LocalDateTime generated_at;     // Time stamp auto when extracted data


}

package com.mood.mentor.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class QuizTable {

private Long quiz_id;   //primary key and auto increment
private Long user_id;   //foreign key
private String quiz_type;
private int score;
private String result_category;
private LocalDateTime createdAt;   //timestamp

}

package com.mood.mentor.Entities;
public class Scores {
    private double stress;
    private double anxiety;
    private double mood;

    public Scores() {
        this.stress = 0.0;
        this.anxiety = 0.0;
        this.mood = 0.0;
    }

    public void updateStress(double value) {
        this.stress = Math.max(this.stress, value);
    }

    public void updateAnxiety(double value) {
        this.anxiety = Math.max(this.anxiety, value);
    }

    public void updateMood(double value) {
        this.mood = Math.max(this.mood, value);
    }

    @Override
    public String toString() {
        return "Stress: " + stress + "/10, Anxiety: " + anxiety + "/10, Mood: " + mood + "/10";
    }
}

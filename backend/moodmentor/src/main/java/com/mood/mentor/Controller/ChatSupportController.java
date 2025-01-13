package com.mood.mentor.Controller;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatSupportController {

    private static final Map<String, List<Question>> topicQuestions = new HashMap<>();
    private static final Random random = new Random();
    private int currentQuestionIndex = 0;
    private String currentTopic = null;

    // Initialize questions for different topics
    static {
        // General Anxiety Questions
        List<Question> generalAnxietyQuestions = new ArrayList<>();
        generalAnxietyQuestions.add(new Question("How often do you feel restless or on edge?",
                "Based on your response, your restlessness levels are moderate. Remember that occasional restlessness is normal."));
        generalAnxietyQuestions.add(new Question("Do you have trouble controlling your worries?",
                "It's common to struggle with controlling worries. Consider practicing mindfulness techniques."));
        generalAnxietyQuestions.add(new Question("How is your sleep quality lately?",
                "Sleep disruption can significantly impact anxiety levels. Maintaining good sleep hygiene is important."));
        generalAnxietyQuestions.add(new Question("Do you experience physical symptoms like rapid heartbeat or sweating?",
                "Physical symptoms are common manifestations of anxiety. Try deep breathing exercises when they occur."));

        // Social Anxiety Questions
        List<Question> socialAnxietyQuestions = new ArrayList<>();
        socialAnxietyQuestions.add(new Question("How comfortable are you in social gatherings?",
                "Social situations can be challenging. Start with small gatherings to build confidence."));
        socialAnxietyQuestions.add(new Question("Do you worry about being judged by others?",
                "Remember that most people are focused on themselves rather than judging others."));
        socialAnxietyQuestions.add(new Question("How do you feel about public speaking?",
                "Public speaking anxiety is very common. Practice with trusted friends or join speaking groups."));

        // Work Anxiety Questions
        List<Question> workAnxietyQuestions = new ArrayList<>();
        workAnxietyQuestions.add(new Question("How often do you feel overwhelmed at work?",
                "Work overwhelm is common. Try breaking tasks into smaller, manageable pieces."));
        workAnxietyQuestions.add(new Question("Do you worry about meeting deadlines?",
                "Time management strategies can help reduce deadline-related anxiety."));
        workAnxietyQuestions.add(new Question("How do you handle work-related conflicts?",
                "Workplace conflicts are normal. Focus on clear communication and professional boundaries."));

        topicQuestions.put("general", generalAnxietyQuestions);
        topicQuestions.put("social", socialAnxietyQuestions);
        topicQuestions.put("work", workAnxietyQuestions);
    }

    @GetMapping("/topics")
    public List<String> getAvailableTopics() {
        return new ArrayList<>(topicQuestions.keySet());
    }

    @PostMapping("/start/{topic}")
    public String startChat(@PathVariable String topic) {
        if (!topicQuestions.containsKey(topic)) {
            return "Invalid topic. Please choose from: " + String.join(", ", topicQuestions.keySet());
        }
        currentTopic = topic;
        currentQuestionIndex = 0;
        return "Starting assessment for " + topic + " anxiety. Use /next for the first question.";
    }

    @GetMapping("/next")
    public String getNextQuestion() {
        if (currentTopic == null) {
            return "Please start an assessment first using /start/{topic}";
        }

        List<Question> questions = topicQuestions.get(currentTopic);
        if (currentQuestionIndex >= questions.size()) {
            int rating = generateRating();
            String feedback = generateFeedback(rating);
            // Reset for next session
            currentTopic = null;
            currentQuestionIndex = 0;
            return "Assessment complete!\n" + feedback;
        }

        Question question = questions.get(currentQuestionIndex);
        currentQuestionIndex++;
        return "Question " + currentQuestionIndex + ": " + question.getQuestion();
    }

    @PostMapping("/answer")
    public String provideAnswer(@RequestBody String answer) {
        if (currentTopic == null) {
            return "Please start an assessment first using /start/{topic}";
        }

        List<Question> questions = topicQuestions.get(currentTopic);
        if (currentQuestionIndex <= 0 || currentQuestionIndex > questions.size()) {
            return "No active question to answer";
        }

        Question currentQuestion = questions.get(currentQuestionIndex - 1);
        return currentQuestion.getResponse();
    }

    private int generateRating() {
        return random.nextInt(5) + 1; // Generates rating between 1-5
    }

    private String generateFeedback(int rating) {
        String feedback = "Your anxiety level rating is: " + rating + "/5\n";
        switch (rating) {
            case 1:
                feedback += "Your anxiety levels appear to be well-managed. Keep up the good work!";
                break;
            case 2:
                feedback += "You're handling things well, with some room for improvement.";
                break;
            case 3:
                feedback += "You're experiencing moderate anxiety. Consider some stress-management techniques.";
                break;
            case 4:
                feedback += "Your anxiety levels are elevated. Consider speaking with a mental health professional.";
                break;
            case 5:
                feedback += "You're experiencing high levels of anxiety. Please reach out to a mental health professional for support.";
                break;
        }
        return feedback;
    }

    // Question class to store question-response pairs
    private static class Question {
        private final String question;
        private final String response;

        public Question(String question, String response) {
            this.question = question;
            this.response = response;
        }

        public String getQuestion() {
            return question;
        }

        public String getResponse() {
            return response;
        }
    }
}
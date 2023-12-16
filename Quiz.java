import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Quiz {

    private static Timer timer;
    private static int score = 0;
    private static boolean answered = false;

    public static void main(String[] args) {
        // Define your quiz questions, options, and correct answers
        String[] questions = {
                "What is the capital of France?",
                "What is the largest planet in our solar system?",
                "Who wrote 'Romeo and Juliet'?",
        };
        String[][] options = {
                {"A) Paris", "B) Rome", "C) Berlin", "D) Madrid"},
                {"A) Mars", "B) Venus", "C) Jupiter", "D) Saturn"},
                {"A) Charles Dickens", "B) Jane Austen", "C) William Shakespeare", "D) J.K. Rowling"}
        };
        char[] correctAnswers = {'A', 'C', 'C'};

        // Set the time limit for each question (in milliseconds)
        int timeLimit = 20000;

        // Start the quiz
        for (int i = 0; i < questions.length; i++) {
            System.out.println("Question " + (i + 1) + ": " + questions[i]);

            // Display options
            for (String option : options[i]) {
                System.out.println(option);
            }

            startTimer(timeLimit);
            waitForAnswer(correctAnswers[i]);
            stopTimer();

            if (answered) {
                score++;
                System.out.println("Correct! Your current score is: " + score);
            } else {
                System.out.println("Time's up! The correct answer is: " + correctAnswers[i]);
            }

            // Reset the flag
            answered = false;
        }

        // Display final score
        System.out.println("Quiz completed! Your final score is: " + score);
    }

    private static void startTimer(int timeLimit) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time's up!");
                timer.cancel();
            }
        }, timeLimit);
    }

    private static void stopTimer() {
        timer.cancel();
    }

    private static void waitForAnswer(char correctAnswer) {
        Scanner scanner = new Scanner(System.in);
        String userAnswer = scanner.nextLine().toUpperCase(); // Convert to uppercase for case-insensitive comparison

        if (userAnswer.length() == 1 && userAnswer.charAt(0) == correctAnswer) {
            answered = true;
        }
    }
}


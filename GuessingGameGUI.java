import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingGameGUI extends JFrame {
    private int numberToGuess;
    private int numberOfGuesses;
    private boolean hasGuessedCorrectly;

    private JLabel promptLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JTextArea resultArea;

    public GuessingGameGUI() {
        setTitle("Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        promptLabel = new JLabel("Enter your guess:");
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        resultArea = new JTextArea(5, 20);
        resultArea.setEditable(false);

        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        setLayout(new GridLayout(4, 1));
        add(promptLabel);
        add(guessField);
        add(guessButton);
        add(resultArea);

        initializeGame();
    }

    private void initializeGame() {
        Random random = new Random();
        numberToGuess = random.nextInt(100) + 1;
        numberOfGuesses = 0;
        hasGuessedCorrectly = false;
        resultArea.setText("Welcome to the Guessing Game!\n"
                           + "I'm thinking of a number between 1 and 100.\n");
    }

    private void checkGuess() {
        int userGuess = Integer.parseInt(guessField.getText());
        numberOfGuesses++;

        if (userGuess == numberToGuess) {
            hasGuessedCorrectly = true;
            displayResult("Congratulations! You guessed the number in " + numberOfGuesses + " guesses.");
        } else if (userGuess < numberToGuess) {
            displayResult("Try a higher number.");
        } else {
            displayResult("Try a lower number.");
        }

        if (numberOfGuesses >= 10 || hasGuessedCorrectly) {
            guessButton.setEnabled(false);
            displayResult("Game over. The number was: " + numberToGuess);
        }

        guessField.setText("");
    }

    private void displayResult(String message) {
        resultArea.append(message + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuessingGameGUI().setVisible(true);
            }
        });
    }
}
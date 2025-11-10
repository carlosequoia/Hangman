import java.util.Scanner; // import package scanner lam nyo na yan
import java.util.Random; // import package random, bago ito pero basically nag-rarandomize sya ng values type shi

public class Hangman {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        System.out.println(" HH        HH   AAAAAAAAAAAA   NNNN      NN   GGGGGGGGGGG   MMMM        MMMM   AAAAAAAAAAAA   NNNN      NN");
        System.out.println(" HH        HH   AA        AA   NN NN     NN   GG            MM MM      MM MM   AA        AA   NN NN     NN");
        System.out.println(" HH        HH   AA        AA   NN  NN    NN   GG            MM  MM    MM  MM   AA        AA   NN  NN    NN");
        System.out.println(" HHHHHHHHHHHH   AAAAAAAAAAAA   NN   NN   NN   GG   GGGGGG   MM   MM  MM   MM   AAAAAAAAAAAA   NN   NN   NN");
        System.out.println(" HHHHHHHHHHHH   AAAAAAAAAAAA   NN    NN  NN   GG       GG   MM    MMMM    MM   AAAAAAAAAAAA   NN    NN  NN");
        System.out.println(" HH        HH   AA        AA   NN     NN NN   GG       GG   MM     MM     MM   AA        AA   NN     NN NN");
        System.out.println(" HH        HH   AA        AA   NN      NNNN   GG       GG   MM            MM   AA        AA   NN      NNNN");
        System.out.println(" HH        HH   AA        AA   NN       NNN   GGGGGGGGGGG   MM            MM   AA        AA   NN       NNN\n");

        boolean keepRunning = true; // dito natin dinefine kung magrurun sya indefinitely

        while (keepRunning) {
            System.out.println("======================");
            System.out.println("      MAIN MENU       ");
            System.out.println("======================");
            System.out.println("1. Play");
            System.out.println("2. About");
            System.out.println("3. Exit");
            System.out.print("\nChoose: ");

            String choice = userInput.nextLine();

            if (choice.equals("1")) {
                boolean playAgain = true;

                while (playAgain) {
                    // mga words na pwedeng hulaan ng user (dito pipili ng words randomly bawat batch ng laro)
                    String[] wordList = {"JAVA", "COMPUTER", "PROGRAMMING", "KEYBOARD", "MOUSE"};
                    Random randomNum = new Random();
                    String secretWord = wordList[randomNum.nextInt(wordList.length)];

                    int wordLength = secretWord.length();
                    String guessedWord = "";
                    int i = 0;
                    while (i < wordLength) {
                        guessedWord = guessedWord + "_";
                        i++;
                    }

                    String usedLetters = "";
                    int lives = 6;
                    boolean won = false;

                    // main game loop
                    while (lives > 0 && !won) {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();

                        System.out.println("\n========== HANGMAN GAME ==========\n");

                        // draw hangman based sa amount ng current lives
                        System.out.println("   ╔═══╗");
                        System.out.println("   ║     │");

                        if (lives >= 1) {
                            System.out.println("   ║     ●");
                        } else {
                            System.out.println("   ║      ");
                        }

                        if (lives >= 5) {
                            System.out.println("   ║    ╱│╲");
                        } else if (lives >= 4) {
                            System.out.println("   ║    ╱│ ");
                        } else if (lives >= 2) {
                            System.out.println("   ║     │ ");
                        } else {
                            System.out.println("   ║      ");
                        }

                        if (lives >= 6) {
                            System.out.println("   ║    ╱ ╲");
                        } else if (lives >= 5) {
                            System.out.println("   ║    ╱  ");
                        } else {
                            System.out.println("   ║      ");
                        }

                        System.out.println("   ║");
                        System.out.println("  ═╩═══════\n");

                        System.out.println("Lives: " + lives + "/6");
                        System.out.println("\nWord: " + guessedWord);
                        System.out.println("\nUsed: " + usedLetters);

                        System.out.print("\nGuess a letter: ");
                        String input = userInput.nextLine().toUpperCase();

                        // check kung valid yung input
                        if (input.length() != 1) {
                            System.out.println("One letter only!");
                        } else {
                            char letter = input.charAt(0);

                            // check kung ginamit na
                            if (usedLetters.indexOf(letter) >= 0) {
                                System.out.println("Letter is already used!");
                            } else {
                                usedLetters = usedLetters + letter + " ";

                                boolean found = false;
                                String newWord = "";
                                int j = 0;

                                // check kung nasa word yung letter
                                while (j < wordLength) {
                                    if (secretWord.charAt(j) == letter) {
                                        newWord = newWord + letter;
                                        found = true;
                                    } else {
                                        newWord = newWord + guessedWord.charAt(j);
                                    }
                                    j++;
                                }

                                guessedWord = newWord;

                                if (found) {
                                    System.out.println("\nCorrect letter. Keep it going!");
                                } else {
                                    System.out.println("\nWrong letter. Try guessing more!");
                                    lives = lives - 1;
                                }

                                // check kung nanalo na
                                if (guessedWord.indexOf("_") == -1) {
                                    won = true;
                                }
                            }
                        }
                    }

                    // game over screen
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\n========== GAME OVER ==========\n");
                    System.out.println("The word was: " + secretWord + "\n");

                    if (won) {
                        System.out.println("*** YOU WON! ***");
                    } else {
                        System.out.println("*** YOU LOST! ***");
                    }

                    System.out.print("\nPlay again? (Y/N): ");
                    String playChoice = userInput.nextLine().toUpperCase();

                    if (playChoice.equals("Y")) {
                        playAgain = true;
                    } else {
                        playAgain = false;
                        System.out.println("\nReturning to menu...\n");
                    }
                }

            } else if (choice.equals("2")) {
                System.out.println("\n========================================");
                System.out.println("         ABOUT THIS GAME");
                System.out.println("========================================");
                System.out.println("\nGame: Hangman");
                System.out.println("Developed by: Group ");
                System.out.println("Course: Information Technology");
                System.out.println("\nDescription:");
                System.out.println("Guess the word one letter at a time.");
                System.out.println("You have 6 lives!");
                System.out.println("\nPress Enter to continue...");
                userInput.nextLine();
                System.out.println();

            } else if (choice.equals("3")) {
                System.out.println("\nThank you for playing!");
                System.out.println("Exiting...\n");
                keepRunning = false;

            } else {
                System.out.println("\nInvalid! Choose 1, 2, or 3.\n");
            }
        }
    }
}
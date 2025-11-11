import java.util.Scanner; // para makakuha ng input from user
import java.util.Random; // para sa randomization selection ng words natin

public class Hangman {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in); // gumawa ng scanner para sa user input

        // HANGMAN title art - gamit ang ASCII characters para maganda :))
        System.out.println(" HH        HH   AAAAAAAAAAAA   NNNN      NN   GGGGGGGGGGG   MMMM        MMMM   AAAAAAAAAAAA   NNNN      NN");
        System.out.println(" HH        HH   AA        AA   NN NN     NN   GG            MM MM      MM MM   AA        AA   NN NN     NN");
        System.out.println(" HH        HH   AA        AA   NN  NN    NN   GG            MM  MM    MM  MM   AA        AA   NN  NN    NN");
        System.out.println(" HHHHHHHHHHHH   AAAAAAAAAAAA   NN   NN   NN   GG   GGGGGG   MM   MM  MM   MM   AAAAAAAAAAAA   NN   NN   NN");
        System.out.println(" HHHHHHHHHHHH   AAAAAAAAAAAA   NN    NN  NN   GG       GG   MM    MMMM    MM   AAAAAAAAAAAA   NN    NN  NN");
        System.out.println(" HH        HH   AA        AA   NN     NN NN   GG       GG   MM     MM     MM   AA        AA   NN     NN NN");
        System.out.println(" HH        HH   AA        AA   NN      NNNN   GG       GG   MM            MM   AA        AA   NN      NNNN");
        System.out.println(" HH        HH   AA        AA   NN       NNN   GGGGGGGGGGG   MM            MM   AA        AA   NN       NNN\n");

        // boolean para kontrolin kung tuloy-tuloy yung program
        boolean keepRunning = true;

        // main menu loop - uulitin habang hindi pa exit
        while (keepRunning) {
            // display ng menu options
            System.out.println("======================");
            System.out.println("      MAIN MENU       ");
            System.out.println("======================");
            System.out.println("1. Play");
            System.out.println("2. About");
            System.out.println("3. Exit");
            System.out.print("\nChoose: ");
            String choice = userInput.nextLine();

            // pag pinili ay 1 = PLAY
            if (choice.equals("1")) {
                boolean playAgain = true; // para ulitin ang game kung gusto

                // game loop = uulitin kung gusto mag-play ulit ng user
                while (playAgain) {
                    // list ng mga words na pwedeng hulaan, pwede natin ito lagyan ng marami pang letters
                    String[] wordList = {"JAVA", "COMPUTER", "PROGRAMMING", "KEYBOARD", "MOUSE"};

                    // gumawa ng Random object para pumili ng random word
                    Random random = new Random();

                    // kunin ang random word from wordList
                    String secretWord = wordList[random.nextInt(wordList.length)];

                    // haba ng word na huhulaan
                    int wordLength = secretWord.length();

                    // blank word gamit ay underscores (_)
                    String guessedWord = "";
                    for (int i = 0; i < wordLength; i++) {
                        guessedWord += "_"; // dagdag ng underscore per letter hanggang mag-equal yung length
                    }

                    // ito ang magtatrack ng mga nagamit ng letters
                    String usedLetters = "";

                    // starting lives (6 tries)
                    int lives = 6;

                    // flag kung nanalo na ba
                    boolean won = false;

                    // main game loop = tuloy-tuloy ang loop hanggang may lives at hindi pa nanalo/natalo
                    while (lives > 0 && !won) {
                        System.out.println("\n========== HANGMAN GAME ==========\n");

                        // magdadraw ng hangman based sa natitira pang lives
                        System.out.println("   ╔═══╗");
                        System.out.println("   ║   │");

                        // ulo - ito ang lalabas pag nabawasan ng 1 life (5 lives left)
                        if (lives <= 5) {
                            System.out.println("   ║   ◯");
                        } else {
                            System.out.println("   ║    ");
                        }

                        // body and arms eme eme
                        if (lives <= 2) {
                            System.out.println("   ║  ╱│╲");
                        } else if (lives <= 3) {
                            System.out.println("   ║  ╱│ ");
                        } else if (lives <= 4) {
                            System.out.println("   ║   │ ");
                        } else {
                            System.out.println("   ║      ");
                        }

                        // legs lam nyo na yan
                        if (lives <= 1) {
                            System.out.println("   ║  ╱ ╲");
                        } else if (lives <= 2) {
                            System.out.println("   ║  ╱  ");
                        } else {
                            System.out.println("   ║      ");
                        }

                        System.out.println("   ║");
                        System.out.println("  ═╩═══════\n");

                        // display ng game info
                        System.out.println("Lives: " + lives + "/6");
                        System.out.println("\nWord: " + guessedWord);
                        System.out.println("\nUsed: " + usedLetters);

                        // tatanungin yung user ng letter
                        System.out.print("\nGuess a letter: ");
                        String input = userInput.nextLine().toUpperCase(); // gawing uppercase

                        // i-checheck kung isang letter lang yung input
                        if (input.length() != 1) {
                            System.out.println("One letter only!");
                        } else {
                            char letter = input.charAt(0); // kunin yung letter

                            // chine-check kung nagamit na yung letter
                            if (usedLetters.indexOf(letter) >= 0) {
                                System.out.println("Letter is already used!");
                            } else {
                                // i-add sa used letters
                                usedLetters += letter + " ";

                                boolean found = false; // flag kung nasaan ba yung letter
                                String newWord = ""; // bagong version ng guessed word

                                // check bawat letter ng secret word
                                for (int j = 0; j < wordLength; j++) {
                                    if (secretWord.charAt(j) == letter) {
                                        // if tama yung guess, ilagay yung letter
                                        newWord += letter;
                                        found = true;
                                    } else {
                                        // if hindi naman match, ilagay yung previous character
                                        newWord += guessedWord.charAt(j);
                                    }
                                }

                                // update yung guessed word
                                guessedWord = newWord;

                                // feedback sa user
                                if (found) {
                                    System.out.println("\nCorrect letter. Keep it going!");
                                } else {
                                    System.out.println("\nWrong letter. Try guessing more!");
                                    lives--; // bawasan ng isang life
                                }

                                // check kung nanalo na (walang underscore na natitira)
                                if (guessedWord.indexOf("_") == -1) {
                                    won = true;
                                }
                            }
                        }
                    }

                    // game over screen
                    System.out.println("\n========== GAME OVER ==========\n");
                    System.out.println("The word was: " + secretWord + "\n");

                    // result = win or lose
                    if (won) {
                        System.out.println("=== YOU WON! :D ===");
                    } else {
                        System.out.println("=== YOU LOST! :( ===");
                    }

                    // tanong kung gusto mag-play ulit
                    System.out.print("\nPlay again? (Y/N): ");
                    String playChoice = userInput.nextLine().toUpperCase();

                    // check yung choice
                    if (playChoice.equals("Y")) {
                        playAgain = true; // ulitin ang game
                    } else {
                        playAgain = false; // bumalik sa menu
                        System.out.println("\nReturning to menu...\n");
                    }
                }

                // pag pinili 2 - ABOUT
            } else if (choice.equals("2")) {
                System.out.println("\n========================================");
                System.out.println("               ABOUT THIS GAME");
                System.out.println("========================================");
                System.out.println("\nGame: Simple Hangman");
                System.out.println("Developed by: Carlosjade Mabini, Mark Bryan Medrano, Danica Lacap, Erylle Compañero by BSIT-1A!");
                System.out.println("Course: Information Technology");
                System.out.println("\nDescription:");
                System.out.println("Guess the word one letter at a time.");
                System.out.println("You have 6 lives!");
                System.out.println("\nPress Enter to continue...");
                userInput.nextLine(); // wait for enter
                System.out.println();

                // pag pinili 3 - EXIT
            } else if (choice.equals("3")) {
                System.out.println("\nThank you for playing!");
                System.out.println("Exiting...\n");
                keepRunning = false; // stop yung main loop

                // pag invalid yung input
            } else {
                System.out.println("\nInvalid! Choose 1, 2, or 3.\n");
            }
        }

        // optional: pwede natin i-close yung scanner for good measure, pwede rin naman na hindi. bahala ka na.
        userInput.close();
    }
}
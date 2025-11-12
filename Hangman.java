import java.util.Scanner; // para makakuha ng input from user
import java.util.Random; // para sa randomization selection ng words natin

public class Hangman {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in); // declaration ng scanner variable for user input

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
            // dito magdidisplay yung main menu natin
            System.out.println("======================");
            System.out.println("      MAIN MENU       ");
            System.out.println("======================");
            System.out.println("1. Play");
            System.out.println("2. About");
            System.out.println("3. Exit");
            System.out.print("\nChoose: ");
            int choice = userInput.nextInt(); // gamitin ang int para sa menu choice since we will use switch statement
            userInput.nextLine(); // para i-clear yung newline character after, i-try mo tanggalin to tapos run mo, see the difference

            // switch statement to make it simple rather than blocks of if statements
            switch (choice) {
                case 1: // pag ang input ng user ay 1, magpa-play yung hangman
                    boolean playAgain = true; // ito ay mahalaga kasi ito ang magdedefine kung tuloy-tuloy pa yung hangman na laro

                    // game loop = uulitin kung gusto mag-play ulit ng user
                    while (playAgain) {
                        // list ng mga words na pwedeng hulaan, pwede tayo magdagdag ng marami pang words para masaya :D
                        String[] wordList = {"JAVA", "COMPUTER", "PROGRAMMING", "KEYBOARD", "MOUSE", "FILE", "ERYLLE"};

                        // gumawa ng Random object para pumili ng random word
                        Random random = new Random();

                        // dito kukuhanin ang random word sa wordList
                        String secretWord = wordList[random.nextInt(wordList.length)];

                        // kukunin ang haba ng word na huhulaan
                        int wordLength = secretWord.length();

                        // gumawa ng blank word gamit ang underscores (_)
                        // gagawin natin ito by dagdag ng _ per letter ng secret word
                        String currentGuess = ""; // ito yung ipapakita sa user (ex: _ _ _ _)

                        // WHILE LOOP VERSION: Magdadagdag ng underscore hanggang sa maabot yung haba ng word
                        int letterIndex = 0; // simula sa index 0
                        while (letterIndex < wordLength) { // habang hindi pa naaabot yung haba ng word
                            currentGuess += "_"; // dagdag ng underscore per letter
                            letterIndex++; // dagdagan ng 1 para sa next letter (parang counting: 0, 1, 2, 3...)
                        }

                        // ito ang magtatrack ng mga nagamit ng letters
                        String usedLetters = "";

                        // starting lives (6 tries)
                        int lives = 6;

                        // flag kung nanalo na ba
                        boolean hasWon = false;

                        // main game loop = tuloy-tuloy ang loop hanggang may lives at hindi pa nanalo/natalo
                        while (lives > 0 && !hasWon) {
                            System.out.println("\n========== HANGMAN GAME ==========\n");

                            // magdadraw ng hangman based sa natitira pang lives
                            // gallows (bitayan)
                            System.out.println("   ╔═══╗");
                            System.out.println("   ║   │");

                            // ulo - lalabas pag 5 lives or less
                            if (lives <= 5) {
                                System.out.println("   ║   ◯");
                            } else {
                                System.out.println("   ║    ");
                            }

                            // body at arms - may stages depending sa lives
                            if (lives <= 2) {
                                // both arms at body (2 lives or less)
                                System.out.println("   ║  ╱│╲");
                            } else if (lives == 3) {
                                // left arm at body lang (3 lives)
                                System.out.println("   ║  ╱│ ");
                            } else if (lives == 4) {
                                // body lang (4 lives)
                                System.out.println("   ║   │ ");
                            } else {
                                // walang body pa (5 or 6 lives)
                                System.out.println("   ║      ");
                            }

                            // legs - may stages din depending sa lives
                            if (lives == 1) {
                                // both legs (1 life left)
                                System.out.println("   ║  ╱ ╲");
                            } else if (lives == 2) {
                                // left leg lang (2 lives left)
                                System.out.println("   ║  ╱  ");
                            } else {
                                // walang legs pa (3 or more lives)
                                System.out.println("   ║      ");
                            }

                            // ground
                            System.out.println("   ║");
                            System.out.println("  ═╩═════╝\n");

                            // display ng game info
                            System.out.println("Lives: " + lives + "/6");
                            System.out.println("\nWord: " + currentGuess);
                            System.out.println("\nUsed: " + usedLetters);

                            // tatanungin yung user ng letter
                            System.out.print("\nGuess a letter: ");
                            String input = userInput.nextLine().toUpperCase(); // gawing uppercase

                            // i-check kung isang letter lang yung input
                            if (input.length() != 1) {
                                System.out.println("One letter only!");
                                continue; // ulitin yung loop, tanungin ulit
                            }

                            // if ang input ay isang letter lang, papasok na sya dito sa guessedLetter
                            char guessedLetter = input.charAt(0);

                            // WHILE LOOP VERSION: ichecheck kung nagamit na ba yung letter
                            // kung yung letter ay nandun na sa usedLetters string, meaning kung nagamit na
                            boolean alreadyUsed = false; // default: hindi pa nagamit
                            int checkIndex = 0; // simula sa index 0 ng usedLetters

                            // tingnan bawat letter sa usedLetters hanggang matapos
                            while (checkIndex < usedLetters.length()) {
                                // kunin yung letter sa current position ng usedLetters
                                if (usedLetters.charAt(checkIndex) == guessedLetter) {
                                    alreadyUsed = true; // nandun na yung letter!
                                    break; // stop na yung checking, kasi nahanap na
                                }
                                checkIndex++; // move to next letter (counting: 0, 1, 2, 3...)
                            }

                            // kung nagamit na, sabihin sa user at ulitin yung loop
                            if (alreadyUsed) {
                                System.out.println("Letter is already used!");
                                continue; // ulitin yung loop
                            }

                            // if another letter is inputted rather than same letter, papasok naman sya dito
                            usedLetters += guessedLetter + " ";

                            // i-check kung yung letter ay nandun ba sa secret word
                            boolean letterFound = false;
                            String updatedGuess = ""; // bagong version ng current guess

                            // WHILE LOOP VERSION: tignan bawat letter ng secret word
                            int position = 0; // simula sa unang letter (index 0)

                            // habang hindi pa naaabot yung dulo ng word
                            while (position < wordLength) {
                                // kunin yung letter sa secret word sa current position
                                char secretLetter = secretWord.charAt(position);

                                // kung yung guess ay match sa secret letter, ilagay yung letter
                                if (secretLetter == guessedLetter) {
                                    updatedGuess += guessedLetter; // ilagay yung na-guess na letter
                                    letterFound = true; // nandun yung letter!
                                } else {
                                    // kung hindi match, ilagay yung previous character from current guess
                                    updatedGuess += currentGuess.charAt(position);
                                }

                                position++; // move to next position (counting: 0, 1, 2, 3...)
                            }

                            // i-update yung current guess with new version
                            currentGuess = updatedGuess;

                            // feedback sa user kung tama o mali
                            if (letterFound) {
                                System.out.println("\nCorrect letter. Keep it going!");
                            } else {
                                System.out.println("\nWrong letter. Try guessing more!");
                                lives--; // bawasan ng isang life
                            }

                            // WHILE LOOP VERSION: i-check kung nanalo na (walang underscore na natitira sa current guess)
                            boolean hasUnderscore = false; // default: wala nang underscore
                            checkIndex = 0; // simula ulit sa index 0

                            // tingnan bawat character sa current guess
                            while (checkIndex < currentGuess.length()) {
                                // kung may nakita tayong underscore
                                if (currentGuess.charAt(checkIndex) == '_') {
                                    hasUnderscore = true; // may underscore pa!
                                    break; // stop na yung checking, kasi may underscore pa, hindi pa tapos
                                }
                                checkIndex++; // move to next character (counting: 0, 1, 2, 3...)
                            }

                            // kung walang underscore na, nanalo na!
                            if (!hasUnderscore) {
                                hasWon = true;
                            }
                        }

                        // game over screen
                        System.out.println("\n========== GAME OVER ==========\n");
                        System.out.println("The word was: " + secretWord + "\n");

                        // result = win or lose
                        if (hasWon) {
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
                    break;

                case 2:
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
                    break;

                case 3: // EXIT
                    keepRunning = false; // stop yung main loop dahil pinalitan natin yung value from true to false
                    System.out.println("\nThank you for playing!");
                    System.out.println("Exiting...\n");
                    break;

                default: // INVALID INPUT
                    System.out.println("\nInvalid! Choose 1, 2, or 3.\n");
                    break;
            }
        }

        userInput.close();

    }
}
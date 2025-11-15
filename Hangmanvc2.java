import java.util.Scanner; // import scanner package lam nyo na yan
import java.util.Random; // import random package, basically randomizer eme eme

public class Hangmanvc2 {
    // ito yung galing sa code nina jacob, ginaya ko lang :))
    private static final String RESET = "\u001B[0m";
    private static final String PURPLE = "\u001B[35m";
    private static final String YELLOW = "\u001B[33m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String CYAN = "\u001B[36m";

    // scanner para magtake ng user input, kaya nga userInput ang variable ng Scanner natin
    private static Scanner userInput = new Scanner(System.in);

    // Listahan ng mga words na pwedeng hulaan, for now, kakaunti palang pero dadagdagan natin, testing palang ih
    private static String[] words = {"JAVA", "COMPUTER", "PROGRAMMING", "KEYBOARD", "MOUSE", "FILE", "ERYLLE"};

    public static void main(String[] args) {
        showTitle(); // dito ipapakita yung title ng ating laro [HANGMAN GAME]

        // ito yung main loop, as long as hindi pa ineexit ng user, magrurun pa ito
        while (true) {
            int choice = showMenu(); // Kunin ang choice ng user

            // if statement na magtatake ng user input int
            if (choice == 1) {
                playGame(); // play the game obviously haha
            } else if (choice == 2) {
                showAbout(); // pag input ng user ay 2, ipapakita natin kung sino tayo and so on
            } else if (choice == 3) {
                showExit(); // edi exit, ano pa ba?
                break;
            } else {
                System.out.println(RED + "\nInvalid choice! Please enter 1, 2, or 3.\n" + RESET);
                pause(); // delay para mabasa ng user yung message
            }
        }

        userInput.close(); // optional ito, pero dahil tinuro satin to ni sir ade and for good practice, include na natin
    }

    // dito yung title, as stated earlier
    private static void showTitle() {
        System.out.println(PURPLE + "\n╔════════════════════════════════════════════╗");
        System.out.println("║              HANGMAN GAME                  ║");
        System.out.println("╚════════════════════════════════════════════╝" + RESET);
    }

    // dito ipapakita yung main menu
    private static int showMenu() {
        System.out.println(PURPLE + "╔════════════════════════════════════════════╗");
        System.out.println("║               MAIN MENU                    ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("║  " + YELLOW + "1." + PURPLE + " Play Game                              ║");
        System.out.println("║  " + YELLOW + "2." + PURPLE + " About                                  ║");
        System.out.println("║  " + YELLOW + "3." + PURPLE + " Exit                                   ║");
        System.out.println("╚════════════════════════════════════════════╝  " + RESET);
        System.out.print(CYAN + "\nEnter your choice: " + RESET);

        try {
            int choice = userInput.nextInt();
            userInput.nextLine(); // para malinis ang output
            return choice;
        } catch (Exception e) { // exception e ay ginagamit pag try, natutunan ko to sa app making namin
            userInput.nextLine(); // para malinis ang output even if mali ang input (logical error ba yun?)
            return -1;
        }
    }

    // dito nakalagay yung mga logic sa Hangman, testing palang sya, edit ko nalang to pag naverify ko na lahat
    private static void playGame() {
        // pipili ng random word sa listahan kanina
        String word = words[new Random().nextInt(words.length)];

        // gagawa ng blank na word gamit ang underscore (_)
        String guess = "";
        for (int i = 0; i < word.length(); i++) {
            guess += "_";
        }

        String used = ""; // ito naman para sa mga nagamit na letters
        int lives = 7; // total ay 7 lives

        // maglo-loop habang may lives pa at hindi pa tapos yung laro
        while (lives > 0 && guess.contains("_")) {
            showGameScreen(lives, guess, used); // dito ipapakita yung mismong laro like yung hangman, lives, and more

            System.out.print(CYAN + "\nGuess a letter: " + RESET);
            String input = userInput.nextLine().toUpperCase(); // gagawing uppercase pag lowercase ang input

            // titingnan kung isang letter lang ba ang input, otherwise error
            if (input.length() != 1 || !input.matches("[A-Z]")) {
                System.out.println(RED + "\nPlease enter one letter only!" + RESET);
                pause(); // delay para mabasa ng user
                continue;
            }

            char letter = input.charAt(0); // if tama ang input ng user, isto-store yun

            // dito naman titingnan kung nagamit na ba yung letter
            if (used.contains(input)) {
                System.out.println(YELLOW + "\nYou already used that letter!" + RESET);
                pause(); // delay ulit para mabasa ng user
                continue;
            }

            used += letter + " "; // if hindi pa nagamit, edi isto-store yun

            // titingnan kung tama o mali yung letter
            if (word.contains(input)) {
                guess = updateGuess(word, guess, letter); // iup-update yung blank kanina tapos ilalagay dun yung tamang letter
                System.out.println(GREEN + "\nCorrect! Keep going!" + RESET);
            } else {
                lives--; // if mali, mababawasan ng isa sa lives
                System.out.println(RED + "\nWrong! Try again!" + RESET);
            }

            pause(); // delay ulit ulit para mabasa ng user
        }

        showResult(lives > 0, word); // ipapakita kung natalo o nanalo ang user

        // dito tatanungin kung gusto pa maglaro ng user after game over
        System.out.print(YELLOW + "\nPlay again? (Y/N): " + RESET);
        if (!userInput.nextLine().toUpperCase().equals("Y")) { // pansin na gumamit ako ng ! kasi NOT meaning if hindi, lam nyo na yan, andali lang ehh
            System.out.println(CYAN + "\nReturning to menu...\n" + RESET);
            pause(); // pause bago bumalik sa menu
        } else {
            System.out.println(YELLOW + "Continuing..." + RESET);
            pause();
            playGame(); // if Y or y, edi go ulit
        }
    }

    // dito na ipapakita yung main game screen
    private static void showGameScreen(int lives, String guess, String used) {
        System.out.println(PURPLE + "\n╔════════════════════════════════════════════╗");
        System.out.println("║              HANGMAN GAME                  ║");
        System.out.println("╚════════════════════════════════════════════╝" + RESET);

        drawHangman(lives); // idr-draw si hangman

        System.out.println(YELLOW + "\nLives: " + lives + "/7" + RESET);
        System.out.println(CYAN + "\nWord: " + GREEN + addSpaces(guess) + RESET);
        System.out.println(PURPLE + "\nUsed: " + RESET + used);
    }

    // idr-draw si hangman depende sa dami ng lives
    private static void drawHangman(int lives) {
        String color = (lives <= 2) ? RED : YELLOW; // magiging RED yung hangman if equals or less than 2 for added game detail naks

        System.out.println(color + "\n   ╔═══╗");
        System.out.println("   ║   ║");

        // if lives ay equals or less than 5, idr-draw yung head
        if (lives <= 5) {
            System.out.println("   ║   O"); // ampangit ng head haha mukhang vertical oval eh
        } else {
            System.out.println("   ║    ");
        }

        // head and arms
        if (lives == 2) {
            // if lives is equals to 2, idr-draw ang head, body, and both arms ang ididisplay
            System.out.println("   ║  /|\\");
        } else if (lives == 3) {
            // if lives is equals to 3, head, body, at left arm lang ang ididisplay
            System.out.println("   ║  /| ");
        } else if (lives == 4) {
            // if lives is equals to 4, head at body lang ang ididisplay
            System.out.println("   ║   | ");
        } else if (lives <= 1) {
            // if lives is 1 or 0, head, body, and both arms ang ididisplay
            System.out.println("   ║  /|\\");
        } else {
            // if lives ay 6 or 7, head palang ang idr-draw
            System.out.println("   ║     ");
        }

        // dito naman yung legs
        if (lives == 0) {
            // if lives ay 0, edi talo na
            System.out.println("   ║  / \\");
        } else if (lives == 1) {
            // if lives ay 1 or more, left leg lang ididisplay
            System.out.println("   ║  /  ");
        } else {
            // if lives ay 2 or more, wala pang legs na ididisplay
            System.out.println("   ║     ");
        }

        System.out.println("   ║");
        System.out.println("  ╚╩════╝" + RESET);
    }

    // iup-update yung guess na blank if tama ang letter
    private static String updateGuess(String word, String guess, char letter) {
        String result = "";
        // titingnan ang bawat letter ng word na huhulaan
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                result += letter; // dito ilalagay yung tama
            } else {
                result += guess.charAt(i); // dito namin yung dating letter
            }
        }
        return result;
    }

    // dito lalagyan ng spaces para readable at malinis
    private static String addSpaces(String text) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            result += text.charAt(i) + " ";
        }
        return result.trim();
    }

    // ipapakita kung natalo o nanalo, part 2 nung comment ko kanina
    private static void showResult(boolean won, String word) {
        System.out.println("\n" + PURPLE + "╔════════════════════════════════════════════╗");
        System.out.println("║               GAME OVER!                   ║");
        System.out.println("╚════════════════════════════════════════════╝" + RESET);
        System.out.println(CYAN + "\nThe word was: " + YELLOW + word + RESET);

        if (won) {
            System.out.println(GREEN + "\n╔══════════════════════════════════════╗");
            System.out.println("║          YOU WON! AWESOME!           ║");
            System.out.println("╚══════════════════════════════════════╝" + RESET);
        } else {
            System.out.println(RED + "\n╔══════════════════════════════════════╗");
            System.out.println("║        YOU LOST! TRY AGAIN!          ║");
            System.out.println("╚══════════════════════════════════════╝" + RESET);
        }
        pause(); // delay ulit ulit ulit para mabasa ng user yung result
    }

    // if 2 yung input sa main menu, ipapakita yung about natin lam nyo na yan
    private static void showAbout() {
        System.out.println(PURPLE + "\n╔════════════════════════════════════════════╗");
        System.out.println("║       ABOUT US AND WHAT IS HANGMAN?        ║");
        System.out.println("╚════════════════════════════════════════════╝" + RESET);
        System.out.println(YELLOW + "Game: " + RESET + "Simple Hangman");
        System.out.println(YELLOW + "Developers: " + RESET + "Carlosjade Mabini, Mark Bryan Medrano, Danica Lacap, Erylle Compañero");
        System.out.println(YELLOW + "Course: " + RESET + "BSIT-1A - Information Technology");
        System.out.println(YELLOW + "\nDescription:" + RESET);
        System.out.println("Guess the word one letter at a time.");
        System.out.println("You have a total of 7 lives. Good luck!");
    }

    // dito naman if 3, which is exit
    private static void showExit() {
        System.out.println(GREEN + "\n╔════════════════════════════════════════════╗");
        System.out.println("║      Thank you for playing Hangman!        ║");
        System.out.println("║              See you again!                ║");
        System.out.println("╚════════════════════════════════════════════╝" + RESET + "\n");
    }

    // Ito yung main function sa mga pause and delay
    private static void pause() {
        try {
            Thread.sleep(1000); // 1 second delay para mabasa ng user yung mga message type shi
        } catch (InterruptedException e) {
            // ignore lang even if may input or logical error ang user
        }
    }
}
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
// Provide a driver menu program that builds the MorseCodeTree and asks the user if they want to
// 1) test output for all morse code letters with their respective translated alphabet letters
//      (make sure you are using the MorseCodeTree to do the actual translation, output as a nicely
//      formatted table. Doesn't have to be alphabetized but that would be nice for the user 😃),
// 2) enter an input textfile name with morse code to decode and output the translated text to
//      the console, and
// 3) enter in a line of morse code through the console to decode morse code and output the
//      translated text to the console.

    public static void main(String[] args) throws FileNotFoundException {
	MorseCodeTree morseCodeTree = new MorseCodeTree();


       //System.out.println(morseCodeTree.toString());
         Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter your morse code to translate");
         String code = keyboard.nextLine();
        System.out.println( morseCodeTree.translateFromMorseCode(code));


    }
}

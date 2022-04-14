import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * MorseCodeTree : A BinaryTree, with Nodes of type Character to represent each letter of the English alphabet,
 * and a means of traversal to be used to decipher Morse code.
 *
 * @version 1.0
 */
public class MorseCodeTree extends BinaryTree<Character> {

    // TODO:
    // Build this class, which includes the parent BinaryTree implementation in addition to
    // the `translateFromMorseCode` and `readMorseCodeTree` methods. Documentation has been suggested for the former,
    // where said exceptional cases are to be handled according to the corresponding unit tests.
    //declare new tree that will be filed by readBinaryTree
  //  BinaryTree<Character> morseCode = new BinaryTree();

    {
        readMorseCodeTree();
    }



    //To build the tree, read a text file in which each line consists of a letter followed by its code (i.e., b -***)
    // and add the node appropriately to the tree using the morse code. The letters should be ordered by tree level, from
    // left-to-right (i.e., e, t, i, a, n, m, etc. where each letter is on its own line with the code as described previously).
    public void readMorseCodeTree() {
    try {
        File name = new File("morseCode.txt");
        Scanner scan = new Scanner(name);
        root = new Node<>();
        Node<Character> currentNode;
        //need an array to hold the input from the file
        String[] inputValues;
        char[] sequence;
        //while theres a next line in the file
        while (scan.hasNextLine()) {
            //read file using delim " "
            inputValues = scan.nextLine().split(" ");
            currentNode = root;
            //for our tree we only need the second part of that string, the chars * and -
            //take that piece of input and convert it to an array. From my file I know that going to be at index 1
            sequence = inputValues[1].toCharArray();
            //for length of that array
            for (char c : sequence) {
                //if char is * and its left child is null set left to newNode<>()
                if (c == '*') {
                    //and set node = node.left which moves as down the tree
                    if (currentNode.left == null) {
                        currentNode.left = new Node<>();
                    }

                    currentNode = currentNode.left;
                }
                //if char[i] is - and the right child is null set right to char[i]
                else if (c == '-') {
                    if (currentNode.right == null)
                        currentNode.right = new Node<>();
                    currentNode = currentNode.right;
                } else{
                    System.out.println("character c = " + c);
                    throw new IllegalArgumentException();
                }
                //and set right to node akak node = node.right

            }//for loop
            //set data of current node to the letter - the letter is stored in our inputValues[0] but we need a char value so we take char at the 0th index of inputValues
            currentNode.data = inputValues[0].charAt(0);

        }//while loop
    }catch (FileNotFoundException e){
        e.printStackTrace();
        }
    }

    /**
     * Non-recursive method for translating a String comprised of morse code values through traversals
     * in the MorseCodeTree.
     *
     * The given input is expected to contain morse code values, with '*' for dots and '-' for dashes, representing
     * only letters in the English alphabet.
     *
     * This method will also handle exceptional cases, namely if a given token's length exceeds that of the tree's
     * number of possible traversals, or if the given token contains a character that is neither '*' nor '-'.
     *
     * @param morseCode The given input representing letters in Morse code
     * @return a String representing the decoded values from morseCode
     */

    public String translateFromMorseCode(String morseCode) {
       //need string builder
        StringBuilder sb = new StringBuilder();
        // string array or the morse code entered
        String[] charSequence = morseCode.split(" ");
        //char node
        Node<Character> currentNode;
        //another char[] to hold each individual character to then step through tree to find data
        char[] characters;
        //for all the letters in string array
        for(String s : charSequence) {
            //set currentNode to root
            currentNode = root;
            // give values to our char array
            characters = s.toCharArray();
            if(characters.length >4)
                throw new IndexOutOfBoundsException("Too Characters in encrypted message");
            // if c == * move left
            for(char c : characters) {

                if(c == '*')
                {
                    currentNode = currentNode.left;
                }
                //if c == - mover right
                else if(c == '-'){
                    currentNode = currentNode.right;
                }
                //if not a * or - throw an IllegalArgumentException
                else throw new IllegalArgumentException();
                //add the data at the currentNode to our string builder

            }//end nested for loop
            sb.append(currentNode.data);

        }//for loop
        // return the string
        return sb.toString();
    }

} // End of class MorseCodeTree
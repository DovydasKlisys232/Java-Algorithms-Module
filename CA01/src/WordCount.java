/*
Student: B00165094
ID: B00165094
Date: 23/2/26
Purpose: This code allows the user to input a file (.txt extension) and counts the number of words in it. it outputs a word and 
its corresponding count. It prints each word and its count once and on their own line. 
If piping a large text file into the program, you can specify how many lines are read using this command:
head -n 10 file.txt | java WordCount
This takes the first 10 lines of the text file.
*/

import java.util.ArrayList;             // Import the ArrayList class to create arrays that change in size
import java.util.Scanner;               // Import the Scanner class to read from standard input

public class WordCount {

    private String word; // stores read word
    private int occ;     // stores number of occurrences of the word

    public WordCount(String word, int occ) {
        this.word = word;
        this.occ = occ;
    }

    public String getWord() {return word;}

    public int getOcc() {return occ;}
    
    public void incrementOcc() {this.occ++;}

    // toString override to print word count pairs
    public String toString() {return word + " " + occ;}

    public static void main(String[] args) {
        // array list to store pairs and scanner to read from I/O
        ArrayList<WordCount> wordslist = new ArrayList<WordCount>();
        Scanner text = new Scanner(System.in);
        
        // while there is another word to read, another word is read.
        // It loops throug the whole wordlist arraylist to check if the word is already there.
        // If it is then the word occurence is incremented and loop is exited.
        // if not then the word is added to list. 
        while (text.hasNext()) {
            String word = text.next(); 
            boolean found = false; 

            for (int i = 0; i < wordslist.size(); i++) {
                if (wordslist.get(i).getWord().equals(word)) {
                    wordslist.get(i).incrementOcc(); 
                    found = true; 
                    break; 
                }
            }

            if (!found) {
                wordslist.add(new WordCount(word, 1));
            }
        }

        // print all word count pairs
        for (int i = 0; i < wordslist.size(); i++) {
            System.out.println(wordslist.get(i));
        }

        text.close(); 
    }
}

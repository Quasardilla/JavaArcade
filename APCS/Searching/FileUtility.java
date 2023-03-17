package Searching;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class FileUtility {

    public static void main(String[] args) {
        String[] words = null;
        try {
            words = readFile("APCS/Searching/wordlist.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int[] nums = new int[]{1, 2, 3, 4, 5};
        Arrays.sort(nums);
        System.out.println(Searcher.binaryIndexOf(nums, 3));
        Arrays.sort(words);
        System.out.println(Searcher.binaryIndexOf(words, "programming"));
        
    }


    public static String[] readFile(String fileName) throws FileNotFoundException
    {
        Scanner reader = new Scanner(new File(fileName));
        int count = 0;
        while(reader.hasNext())
        {
           count++;
           reader.nextLine();
        }
        System.out.println(fileName + " contains " + count + " items.");
  
        reader.close();
        reader = new Scanner(new File(fileName));
  
        String[] words = new String[count];
        count = 0;
        while(reader.hasNext())
        {
           words[count] = reader.nextLine();
           count++;
        }
  
        reader.close();
  
        return words;
    }

     /**
     * 
     * @param text
     *      : A list of strings
     * @param target
     *      : A target string that you're
     *      looking for in the list of 
     *      strings
     * @return
     *      : Whether or not the word is
     *      contained in the list of strings
     */
    public static boolean contains(String[] text, String target) {
        for(int i = 0; i < text.length; i++)
            if(text[i].equals(target))
                return true;
        return false;
    }

}

//Mr. Uhl
//Program description: A tester class for methods in the StringStuff class.

package Intro.stringStuff;

public class StringStuffTester
{
   public static void main(String[] args)
   {
      System.out.println("If your compareTo and equals methods are correct,");
      System.out.println("then each of the following values should be the same:\n");
      String s1 = "cat";
      String s2 = "dog";
      System.out.println("Text: " + s1 + " vs. " + s2 + ":");
      System.out.println("compareTo results: " + s1.compareTo(s2) + " vs. " + StringStuff.compareStrings(s1, s2));
      System.out.println("equals results: " + s1.equals(s2)  + " vs. " + StringStuff.equalStrings(s1, s2));
      System.out.println();
      
      s1 = "dragon";
      s2 = "drag";
      System.out.println("Text: " + s1 + " vs. " + s2 + ":");
      System.out.println("compareTo results: " + s1.compareTo(s2) + " vs. " + StringStuff.compareStrings(s1, s2));
      System.out.println("equals results: " + s1.equals(s2)  + " vs. " + StringStuff.equalStrings(s1, s2));
      System.out.println();
      
      s1 = "sparky";
      s2 = "Sparky";
      System.out.println("Text: " + s1 + " vs. " + s2 + ":");
      System.out.println("compareTo results: " + s1.compareTo(s2) + " vs. " + StringStuff.compareStrings(s1, s2));
      System.out.println("equals results: " + s1.equals(s2)  + " vs. " + StringStuff.equalStrings(s1, s2));
      System.out.println();
      
      s1 = "Hi.";
      s2 = "Hi!";
      System.out.println("Text: " + s1 + " vs. " + s2 + ":");
      System.out.println("compareTo results: " + s1.compareTo(s2) + " vs. " + StringStuff.compareStrings(s1, s2));
      System.out.println("equals results: " + s1.equals(s2)  + " vs. " + StringStuff.equalStrings(s1, s2));
      System.out.println();
      
      s1 = "Hello";
      s2 = "Hello";
      System.out.println("Text: " + s1 + " vs. " + s2 + ":");
      System.out.println("compareTo results: " + s1.compareTo(s2) + " vs. " + StringStuff.compareStrings(s1, s2));
      System.out.println("equals results: " + s1.equals(s2)  + " vs. " + StringStuff.equalStrings(s1, s2));
      System.out.println();
      
      s1 = "Carter";
      s2 = "cart";
      System.out.println("Text: " + s1 + " vs. " + s2 + ":");
      System.out.println("compareTo results: " + s1.compareTo(s2) + " vs. " + StringStuff.compareStrings(s1, s2));
      System.out.println("equals results: " + s1.equals(s2)  + " vs. " + StringStuff.equalStrings(s1, s2));
      System.out.println();
      
      s1 = "I am so good at this!";
      s2 = "so ";
      System.out.println("Remove '" + s2 + "' from '" + s1 + "'");
      s1 = StringStuff.removeString(s1, s2);
      System.out.println("Result: " + s1);
      System.out.println();
      
      System.out.println("Remove '" + s2 + "' from '" + s1 + "'");
      s1 = StringStuff.removeString(s1, s2);
      System.out.println("Result: " + s1);
      System.out.println();
      
      s2 = "good";
      String s3 = "great";
      System.out.println("Replace '" + s2 + "' with '" + s3 + "' in " + s1 + "'");
      s1 = StringStuff.replaceString(s1, s2, s3);
      System.out.println("Result: " + s1);
      System.out.println();
      
      s2 = "fine";
      System.out.println("Replace '" + s2 + "' with '" + s3 + "' in " + s1 + "'");
      s1 = StringStuff.replaceString(s1, s2, s3);
      System.out.println("Result: " + s1);
      System.out.println();
      
      s2 = "at";
      System.out.println("Remove All '" + s2 + "' from '" + s1 + "'");
      s1 = StringStuff.removeAllString(s1, s2);
      System.out.println("Result: " + s1);
      System.out.println();
      
      s2 = "hello";
      System.out.println("Remove All '" + s2 + "' from '" + s1 + "'");
      s1 = StringStuff.removeAllString(s1, s2);
      System.out.println("Result: " + s1);
      System.out.println();
      
      System.out.println("Five randomly generated text sequences:");
      for(int i = 1; i <= 5; i++) {
         int numLetters = (int)(Math.random() * 5) + 4;
         System.out.println("Here are " + numLetters + " letters: " + StringStuff.getRandomText(numLetters));
      }
      System.out.println();
      
      System.out.println("Counting vowels:");
      for(int i = 1; i <= 10; i++) {
         int numLetters = (int)(Math.random() * 10) + 5;
         String text = StringStuff.getRandomText(numLetters);
         if(Math.random() < 0.5)
            text = text.toUpperCase();
         int vowelCount = StringStuff.countVowels(text);
         if(vowelCount == 1)
            System.out.println("There is " + vowelCount + " vowel in: " + text);
         else
            System.out.println("There are " + vowelCount + " vowels in: " + text);
      }      
   }
}
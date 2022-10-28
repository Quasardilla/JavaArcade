//AP Computer Science
//Program description: Complete each of the methods defined in the class

package Intro.stringStuff;

public class StringStuff
{
   /**
    * Compares s1 to the s2. The result is {@code true} if and only if
    * the arguments are not {@code null} and are {@code String} values
    * that represent the same sequence of characters.
    * 
    * @param  s1
    *         The first value being compared
    * @param  s2
    *         The second value being compared
    *         
    * @return  {@code true} if s1 is the same string as s2,
    *          {@code false} otherwise
    */
   public static boolean equalStrings(String s1, String s2)
   {
      return false;
   }
   
   /**
    * Compares two strings lexicographically.
    * The comparison is based on the Unicode value of each character in
    * the strings. The character sequence represented by s1 is compared
    * is compared lexicographically to the character sequence represented
    * by s2. The result is a negative integer if s1 lexicographically
    * precedes s2. The result is a positive integer if s1 lexicographically
    * follows s2. The result is zero if the strings are equal.
    * 
    * @param  s1
    *         The first value being compared
    * @param  s2
    *         The second value being compared
    *          
    * @return  An integer values based on the ruled defined above.
    */
   public static int compareStrings(String s1, String s2)
   {
      return 0;
   }
   
   /**
    * Removes the target string value from the given text value.
    * If the target string is not found in the text, the original text is returned.
    * 
    * @param text
    *        The text string to be examined for text removal.
    * @param target
    *        The string to be removed from the text parameter.
    *        
    * @return  The text parameter with the target string removed,
    *          or the original text if the target is not found.
    */
   public static String removeString(String text, String target)
   {
      return "removeString is  not yet implemented.";
   }
   
   /**
    * Replaces the target string value with the replacement string
    * value in the given text value.
    * If the target string is not found in the text, the original text is returned.
    * 
    * @param text
    *        The text string to be examined for text replacement.
    * @param target
    *        The string to be replaced by another string.
    * @param repl
    *        The string to used as a replacement of the target string.
    *        
    * @return  The text parameter with the target string replaced by the replacement string,
    *          or the original text if the target is not found.
    */
   public static String replaceString(String text, String target, String repl)
   {
      return "removeString is  not yet implemented.";
   }
   
   /**
    * Removes all occurrences of the target string value from the given text value.
    * If the target string is not found in the text, the original text is returned.
    * 
    * @param text
    *        The text string to be examined for text removal.
    * @param target
    *        The string to be removed from the text parameter.
    *        
    * @return  The text parameter with all occurrences of the target string removed,
    *          or the original text if the target is not found.
    */
   public static String removeAllString(String text, String target)
   {
      return "removeString is  not yet implemented.";
   }
   
   /**
    * Replaces all occurrences of the target string value with the
    * replacement string value in the given text value.
    * If the target string is not found in the text, the original text is returned.
    * 
    * @param text
    *        The text string to be examined for text replacement.
    * @param target
    *        The string to be replaced by another string.
    * @param repl
    *        The string to used as a replacement of the target string.
    *        
    * @return  The text parameter with all occurrences of the target
    *          string replaced by the replacement string,
    *          or the original text if the target is not found.
    */
   public static String getRandomText(int numLetters)
   {
      return "removeString is  not yet implemented.";
   }
   
   /**
    * Counts the number of vowels in the given string parameter.
    * A vowel is defined as any case of the letters a, e, i, o, u.
    * The option to consider 'y' as a vowel is left to the developer.
    * 
    * @param text
    *        The text string to be examined for counting vowels.
    *        
    * @return  An integer value representing the number of vowels in
    *          the string parameter.
    */
   public static int countVowels(String text)
   {
      return -1;
   }
   
}
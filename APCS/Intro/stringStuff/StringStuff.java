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
      if (s1 != null && s2 != null)
      {
         if (s1.length() != s2.length())
         {
            return false;
         }

         for (int i = 0; i < s1.length(); i++)
         {
            if (s1.charAt(i) != s2.charAt(i))
            {
               return false;
            }
         }
         return true;
      }
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
      for (int i = 0; i < Math.min(s1.length(), s2.length()); i++)
      {
         if (s1.charAt(i) != s2.charAt(i))
         {
            return s1.charAt(i) - s2.charAt(i);
         }
      }
      if (s1.length()!=s2.length())
      {
         return s1.length()-s2.length();
      }
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
      if (text.contains(target))
      {
         return text.substring(0, text.indexOf(target)) + text.substring(text.indexOf(target) + target.length());
      }
      return text;
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
      if (text.contains(target))
      {
         return text.substring(0, text.indexOf(target)) + repl + text.substring(text.indexOf(target) + target.length());
      }
      return text;
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
      if (text.contains(target))
      {
         return removeAllString(text.substring(0, text.indexOf(target)) + text.substring(text.indexOf(target) + target.length()), target);
      }
      return text;
   }

   /**
    * Replaces all occurrences of the target string value from the given text value.
    * If the target string is not found in the text, the original text is returned.
    * 
    * @param text
    *        The text string to be examined for text replacement.
    * @param target
    *        The string to be removed from the text parameter.
    * @param repl
    *        The string to be replace from the text parameter.
    *        
    * @return  The text parameter with all occurrences of the target string replaced,
    *          or the original text if the target is not found.
    */
   public static String replaceAllString(String text, String target, String repl)
   {
      if (text.contains(target))
      {
         return replaceAllString(text.substring(0, text.indexOf(target)) + repl + text.substring(text.indexOf(target) + target.length()), target, repl);
      }
      return text;
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
      String text = "";
      for (int i = 0; i < numLetters; i++)
      {
         text += (char) (Math.random() * 26 + 97);
      }
      return text;
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
      int count = 0;
      String text1=text.toLowerCase();
      for (int i = 0; i < text.length(); i++)
      {
         if (text1.charAt(i) == 'a' || text1.charAt(i) == 'e' || text1.charAt(i) == 'i' || text1.charAt(i) == 'o' || text1.charAt(i) == 'u')
         {
            count++;
         }
      }
      return count;
   }
   
}
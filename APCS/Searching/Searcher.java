package Searching;

public class Searcher {
    

    /**
     * 
     * @param text
     *      : A list of strings
     * @param target
     *      : A target string that you're
     *      looking for in the list of 
     *      strings
     * @return
     *      : The index correlating to
     *      the location of the target string
     */
    public static int indexOf(String[] text, String target) {
        for(int i = 0; i < text.length; i++)
            if(text[i].equals(target))
                return i;
        return -1;
    }
}

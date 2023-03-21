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

    // public static int binaryIndexOf(int[] nums, int target)
    // {
    //     int first = 0;
    //     int last = nums.length-1;
    //     while (first <= last)
    //     {
    //         int mid = (first + last) / 2;
    //         if (nums[mid] == target)
    //             return mid;
    //         if (target < nums[mid])
    //             last = mid - 1;
    //         else
    //             first = mid + 1;
    //     }

    //     return -1;
    // }

    public static int binaryIndexOf(int[] nums, int target)
    {
        return binaryIndexOf(nums, target, 0, nums.length-1);
    }

    private static int binaryIndexOf(int[] nums, int target, int first, int last)
    {
        if (first <= last)
        {
            int mid = (first + last) / 2;
            if (nums[mid] == target)
                return mid;
            if (target < nums[mid])
                last = mid - 1;
            else
                first = mid + 1;
        }
        else
        {
            return -1;
        }

        return binaryIndexOf(nums, target, first, last);
    }

    public static int binaryIndexOf(String[] words, String target)
    {
        int first = 0;
        int last = words.length-1;
        while (first <= last)
        {
            int mid = (first + last) / 2;
            if (words[mid].equals(target))
                return mid;
            if (target.compareTo(words[mid])<0)
                last = mid - 1;
            else
                first = mid + 1;
        }

        return -1;
    }



}

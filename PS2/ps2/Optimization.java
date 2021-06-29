public class Optimization {
    /**
     * the data in the array increases (or decreases)
     * from one end until it reaches its maximum (or minimum), and then decreases (or increases) until
     * it reaches the other end.
     *
     * More precisely, if the data array has size n, there is some index j such that the subarray dataArray[0..j]
     * is sorted (either increasing or decreasing) and the subarray dataArray[j..n-1] is sorted (either
     * increasing or decreasing).
     *
     * For example, the following are possible input arrays:
     * [2, 5, 7, 9, 15, 23, 8, 6]
     * [73, 42, 13, 5, -17, -324]
     * [100, 42, 17, 3, 8, 92, 234]
     *
     * Use the most efficient algorithm you can. State the running time of your algorithm.
     * Answer: Binary Search
     *
     * What is the best way to signal an error, e.g., if the data array is empty?
     * From the lecture, it is important to understand the specifications of the binary search implementation.
     * For the purpose of this problem set, the best way to signal an error would to be return a null.
     *
     * //////////////////////
     * // IMPLEMENTATION DESCRIPTION
     * /////////////////////
     *
     * // PRECONDITION
     *  Array is of size n
     *  Array is partially sorted
     *
     * // POSTCONDITION
     *  If there are no duplicate elements in the array: max
     */
    public static int searchMax(int[] dataArray) {
        // TODO:
        int start = 0;
        int end = dataArray.length - 1;
        int mid = 0;
        int max = dataArray[end];
        while(start < end) {
            mid = start + (end - start) / 2;
            if (dataArray[mid] > max) {
                start = mid - 1; // Reduce 1 for edge case
                max = dataArray[mid];
            }
            else end = mid;
        }
        return max;
    }
}
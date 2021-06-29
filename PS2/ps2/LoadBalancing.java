import java.util.stream.IntStream;

public class LoadBalancing {
    /**
     * The total load of a processor is the sum of the job sizes, e.g., in the above case the load of processor
     * 2 is:
     * jobSize[4] + jobSize[5] + jobSize[6] + jobSize[7]
     * Your
     *
     * given a specific target load, whether p processors is sufficient.
     *
     * Should return true if it is possible to schedule the jobs on p processors in such a way
     * that no processor has more load than queryLoad.
     *
     * //////////////////////
     * // IMPLEMENTATION DESCRIPTION
     * /////////////////////
     *
     * Approach to the problem is to analyze the best/worst case scenarios:
     *
     * Best case scenario:
     * 1 processor is assigned 1 job
     *
     * Worst case scenario:
     * 1 processor is assigned ALL jobs
     *
     * // PRECONDITION
     *  p number of processors more than 0
     *  jobSize array is partially sorted
     *
     * // POSTCONDITION
     *  return false if total query load exceeds queryLoad benchmark
     *
     */
    static boolean feasibleLoad(int jobSize[], int queryLoad, int p) {
        // TODO:
        int index = 0;
        int end = jobSize.length - 1; // total number of jobs
        int totalQueryLoad = 0;
        int processorCount = 1;
        while (index <= end) {
            totalQueryLoad = jobSize[index] + totalQueryLoad;
            if (jobSize[index] > queryLoad) return false;
            else if (totalQueryLoad > queryLoad)  {
                processorCount = processorCount + 1;
                totalQueryLoad = jobSize[index]; // cannot be set to 0 due to job not taken off the job Array
            }
            else {}
            index = index + 1;
        }
        return processorCount <= p;
    }

    static int findLoad(int jobSize[], int p) {
        // TODO:
        int start = 0;
        int end = totalQueryLoad(jobSize); // Let the end condition be the total query load. Assigning it to p - 1
        // would be useless because the main objective of the method is based off the job size.
        int mid = 0;
        while(start < end) {
            mid = start + (end - start) / 2;
            if (feasibleLoad(jobSize, mid, p)) end = mid;
            else start = mid + 1;
        }
        return start;
    }
    
    private static int totalQueryLoad(int jobSize[]) {
        int sum = 0;
        for (int job : jobSize) {
            sum += job;
        }
        return sum;
    }
}
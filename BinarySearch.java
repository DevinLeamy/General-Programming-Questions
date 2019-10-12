//TODO Rewrite

/*
    Binary Search is a fundamental algorithm used to efficiently locate a target value in a sorted array of values.
    The algorithm is composed of a cycle, an action followed by a check, and runs in tell the desired value is located in the array.
    The algorithm starts by comparing the midpoint, the value at the middle of the search field, with the target value. If the value is equal to the target,
    then the target has been found and it's respective index is returned. Otherwise, if the value is greater then target value the algorithm will
    then limit it's search field to the first half of the array. The inverse occurs if the value is smaller then the target. This check
    and compress cycle continues in tell the value is located.
 */

public class BinarySearch {
    public static void main(String[] args){
        //Tests array of sorted values
        int[] A = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
        //Target
        int T = 3;
        //Start of the search field
        int startI = 0;
        //End of the search field
        int end = A.length - 1;
        //Will hold the median value of the search field
        int med;
        while (true){
            //Gets index of the median value
            int index = (startI + end) / 2;
            //Gets median value
            med = A[index];
            System.out.println("Midpoint: " + med);
            //Case 1: If median value is equals to target then break from the loop
            if (med == T){
                System.out.println("Target Index: " + index);
                break;
            //Case 2: If median value is greater than the target value then shift search field to the last half
            } else if (med < T){
                startI = index;
            //Case 3: If median value is smaller than the target value then shift the search field to the first half
            } else {
                end = index;
            }
        }
        System.out.println("Target: " + med);
    }
}

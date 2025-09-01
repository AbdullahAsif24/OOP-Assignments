public class BinarySearch{
    public static void main(String[] args) {
        // precondition: the array must be physically sorted for binary search to work
        int[] array = {23, 24,34, 45, 56, 67, 78, 89, 90, 91};
        int target = 67; // the number to search for
        int si = 0; // starting index   
        int li = array.length-1; // last index
        int mi = (si + li) /2;  // middle index

        while (si<=li) {
            mi = (si + li) /2;
            if (target > array[mi]) {
                si = mi + 1;
            }else if (target < array[mi]) {
                li = mi - 1;
            }else {
                break;
            }
        }

        if (si<=li) {
            System.out.println("Target found at position " + mi);
        }else {
            System.out.println("Not found");
        }
    }
}
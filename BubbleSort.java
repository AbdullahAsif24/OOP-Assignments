
public class BubbleSort {
    public static void main(String[] args) {
        int[] array = {23,8,51,89,90,5,3,7,125};
        int temp;
        int noOfChange = 1;
        
        // ascending order
        while (noOfChange > 0) {
            noOfChange = 0;
            for (int i = 0; i < array.length-1; i++) {
                if (array[i] > array[i+1]) {
                    noOfChange++;

                    temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;               
                }
            }
        }

        // display array
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
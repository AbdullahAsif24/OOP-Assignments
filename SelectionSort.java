public class SelectionSort {
    public static void main(String[] args) {
        // =========== in-place sorting ================
        int[] array = {23,8,51,89,90,5,3,7,125};
        int smallest = array[0];
        int temp;

        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                if (array[i] > array[j]) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j]  = temp;
                }
            }
        }
        
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
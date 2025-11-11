import java.util.*;

public class Arraylist_parityBit_calculation {

    public static void main(String[] args) {
        // 1 2 3 4 5
        int[] msgBit = { 2, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 };

        ArrayList hammingBits = new ArrayList<Integer>();
        int z = 0;
        for (int i = 1; i < msgBit.length; i = (int) (Math.pow(2, z))) {

            if (i < msgBit.length) {

                int sum = 0;

                for (int k = i; k < msgBit.length; k += (2 * i)) {

                    int m = k;
                    while (m < (k + i) && m < msgBit.length) {

                        if (m < msgBit.length) {
                            sum += msgBit[m];
                        }

                        m++;
                    }

                }
                z++;

                hammingBits.add(getHammingBit("odd", sum));

            }
        }

        for (int i = 0; i < hammingBits.size(); i++) {
            System.out.println("h" + (i + 1) + " = " + hammingBits.get(i));
        }
    }

    public static int getHammingBit(String parityScheme, int sum) {
        int hammingBit;
        if (parityScheme.equalsIgnoreCase("ODD")) {
            int r = sum % 2;
            if (r != 0)
                hammingBit = 0;
            else
                hammingBit = 1;
        } else {
            int r = sum % 2;
            if (r != 0)
                hammingBit = 1;
            else
                hammingBit = 0;
        }
        return hammingBit;
    }
}

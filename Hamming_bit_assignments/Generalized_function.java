package Hamming_bit_assignments;


public class Generalized_function {

    public class HammingAlgo {
        public static void main(String[] args) {
            int[] inp = { 0, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0 };
            // int tot = totalParityBit(inp);

            // int[] msgBit = { 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0 };
            // int error = bitsChecker(msgBit);
            // System.out.println(error);

            int[] placed = paritybitPlacer(inp);
            for (int i : placed) {
                System.out.print(i + " ");
            }
            int error = bitsChecker(placed);
            System.out.println("error " + error);

        }

        public static int totalParityBit(int[] inp) {
            int lengthOfInp = inp.length;

            int r = 0;

            while (Math.pow(2, r) < (lengthOfInp + r + 1)) {
                r++;
            }

            return r;
        }

        public static int[] paritybitPlacer(int[] inp) {
            int lengthOfInp = inp.length;
            int totalparityBit = totalParityBit(inp);

            int[] placedArr = new int[totalparityBit + lengthOfInp];

            // Step 1: Place data bits correctly
            int z = 0;
            for (int i = 0; i < placedArr.length; i++) {
                if (isPowerOfTwo(i + 1)) {
                    placedArr[i] = 0; // parity placeholder
                } else {
                    placedArr[i] = inp[z++];
                }
            }

            // Step 2: Calculate parity bits (even parity)
            for (int i = 1; i <= placedArr.length; i *= 2) {
                int sum = 0;
                for (int j = 1; j <= placedArr.length; j++) {
                    if ((j & i) != 0) {
                        sum += placedArr[j - 1];
                    }
                }
                placedArr[i - 1] = sum % 2;
            }

            return placedArr;
        }

        private static boolean isPowerOfTwo(int n) {
            return (n & (n - 1)) == 0;
        }

        public static int bitsChecker(int[] msgBits) {
            int lengthOfMsg = msgBits.length;

            int parityBits = (int) (Math.ceil(Math.log(lengthOfMsg) / Math.log(2))); // approximate parity count

            int errorPos = 0;

            for (int i = 0; i <= parityBits; i++) {

                int parityPos = (int) Math.pow(2, i); // 1,2,4,8,...
                int sum = 0;

                for (int j = 1; j < lengthOfMsg; j++) {
                    if ((j & parityPos) != 0) {
                        sum += msgBits[j - 1];
                    }
                }

                if (sum % 2 != 0) {
                    errorPos += parityPos;
                }
            }

            return errorPos;
        }

        

    }
}

package Hamming_bit_assignments;

public class H2{

//Defining a function for the Hamming Bits (to be called later in main method)
 public static int getHammingBit(String parityScheme,int[] msg, int sum, int h2){
     
     //Printing the length of int[] msg
     System.out.println(msg.length);
     
     //i = 2 because h2 starts at index 2
     //i < msg.length to stay in bounds of the array
     //i+= 4 : incrementing by 4 since h2 takes 2 after leaving 2
     for(int i = 2; i <  msg.length; i += 4){  
         
         //adding the 3rd element to the sum
         sum += msg[i];
         
    //i+1 because we want the 4th element(the one next to our current one which is 3rd)
         if(i+1 < msg.length)
         
         //adding the 4th element to the sum
         sum += msg[i+1];
         
     }
     
        //Print the sum value
         System.out.println(sum);
     
     // if Parity Scheme is odd then calculate remainder. If not 0 then odd, else even
     if(parityScheme.equalsIgnoreCase("ODD")){
         int rem = sum % 2;
         if(rem != 0)
         h2 = 0;
         else
         h2 = 1;
     }
     
     return h2;
     
 }
 
 //main method
     public static void main(String[] args){
         
         //message bits, parityScheme for odd/even situation, sum and h2
         int[] msg = {2,9,9,0,9,1,1,0,9,0,0,0,1,1,1,1,9,0};
         String parityScheme = "ODD";
         int sum = 0;
         int h2 = 0;
         
         //calling the function to get h2
         System.out.println(getHammingBit(parityScheme, msg, sum, h2));
     }

 }   


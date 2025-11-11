package Hamming_bit_assignments;

//Main class
public class H3{

//Defining a function for the Hamming Bits (to be called later in main method)
 public static int getHammingBit(String parityScheme,int[] msg, int h3){
     
     //Printing the length of int[] msg
   int sum = 0;
     System.out.println(msg.length);
     
     //i = 4 because h3 starts at index 4
     //i < msg.length to stay in bounds of the array
     //i+= 8 : incrementing by 8 since h3 takes 4 after leaving 4
     for(int i = 4; i <  msg.length; i += 8){  
         
         //adding the 4th element to the sum
         sum += msg[i];
         
         //i+1,i+2,i+3 because for h3 we want 4 values, i and the next 3 values to it
         //adding the 2nd, 3rd and 4th element after the 1st one to the sum
         if(i+1 < msg.length)
         sum += msg[i+1];
         
         if(i+2 < msg.length)
         sum += msg[i+2];
         
         if(i+3 < msg.length)
         sum+= msg[i+3];
     }
     
        //Print the sum value
         System.out.println(sum);
     
     // if Parity Scheme is odd then calculate remainder. If not 0 then odd, else even
     if(parityScheme.equalsIgnoreCase("ODD")){
         int rem = sum % 2;
         if(rem != 0)
         h3 = 0;
         else
         h3 = 1;
     }
     
     return h3;
     
 }
 
 //main method
     public static void main(String[] args){
         
         //message bits, parityScheme for odd/even situation, sum and h3
         int[] msg = {2,9,9,0,9,1,1,0,9,0,0,0,1,1,1,1,9,0};
         String parityScheme = "ODD";
         int sum = 0;
         int h3 = 0;
         
         //calling the function to get h2
         System.out.println(getHammingBit(parityScheme, msg, h3));
     }

 }   



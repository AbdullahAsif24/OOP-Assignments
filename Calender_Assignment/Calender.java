import java.time.LocalDate;
import java.util.Scanner;

public class Calender{
    public static void main(String[] args) {
        System.out.println("Enter the month number (1-12):");
        Scanner sc = new Scanner(System.in);

        int month = sc.nextInt();

        System.out.println("Enter the year:");
        int year = sc.nextInt();

        LocalDate firstDate = LocalDate.of(year, month, 1);

        System.out.println("Su  Mo  Tu  We  Th  Fr  Sa");

        String firstTimeSpace;

        if (firstDate.getDayOfWeek().toString().equals("SUNDAY")) {
            firstTimeSpace = " ";
        }else {
            firstTimeSpace = " ".repeat((firstDate.getDayOfWeek().getValue() * 4));
        }

        System.out.print(firstTimeSpace + " 1  ");

        for (int i = 2; i <= firstDate.lengthOfMonth(); i++) {
            LocalDate date =  firstDate.plusDays(i-1);

            if (date.getDayOfWeek().toString().equals("SUNDAY")) {
                System.out.println();
            }

            if (i >1 && i < 10) {
                System.out.print(" " + i + "  ");
                
            }else {
                System.out.print(i + "  ");

            }

        }
    }
}
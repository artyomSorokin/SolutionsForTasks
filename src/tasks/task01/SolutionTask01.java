package tasks.task01;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SolutionTask01 {

    public static void main(String[] args) {
        int quantity = enter();
        System.out.println(processing(quantity));
    }

    static int enter(){
        //create input stream
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int number = 0;
        try {
            //parse String value
            number = Integer.parseInt(reader.readLine());
        } catch (Exception e) {
            System.out.println("Sorry, this is not number");
            //recursion
            enter();
        }
        return number;
    }

    static long processing (int n) {

        //create array for our values
        long [] mas = new long [n+1];

        //first member of array always is 1
        mas[0] = 1;
        //compute member of array from 1 to n
        for (int i=1; i<=n; ++i)
        {
            mas[i]=0;
            for (int j=0; j<i; j++)
                //formula Catalana
                mas[i] +=  mas[j]*mas[i-1-j];
        }

        //return our value
        return mas[n];
    }
}

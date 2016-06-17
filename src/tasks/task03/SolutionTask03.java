package tasks.task03;


import java.math.BigInteger;

public class SolutionTask03 {
    public static void main(String[] args) {
        //compute factorial 100
        BigInteger resultOfFact = fact(100);
        //compute sum
        computeSum(resultOfFact);
    }

    //compute fact 100
    private static BigInteger fact(long n) {
        BigInteger result = BigInteger.ONE;
        for (long i = 2; i <= n; i++)
            result = result.multiply(BigInteger.valueOf(i));
        return result;
    }

    //compute sum of number of factorial 100
    private static int computeSum(BigInteger resultOfFact){

        //convert BigInteger to String
        String line = resultOfFact.toString();
        int sum = 0;

        //loop of string
        for(int i = 0; i< line.length(); i++){
            //substring number
            String str = line.substring(i,i+1);
            //parse this number to int
            int number = Integer.parseInt(str);
            //compute sum
            sum = sum + number;
        }

        //return result
        return sum;
    }
}

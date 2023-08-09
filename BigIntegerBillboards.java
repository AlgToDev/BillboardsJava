import java.util.Arrays;
import java.util.List;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.math.BigInteger;

public class BigIntegerBillboards {



public static long billboards(int k, List<Integer> revenue) {

    int[] revenue_arr = revenue.stream().mapToInt(Integer::intValue).toArray();
    int N = revenue_arr.length;
    
    BigInteger total = BigInteger.ZERO;
    for (int r : revenue_arr) {
        total = total.add(BigInteger.valueOf(r));
    }

    BigInteger[] dp = new BigInteger[N];
    
    if (k == N) {
        return total.longValue();
    } 
    
    for (int i = 0; i < k + 1; i++) {
        dp[i] = BigInteger.valueOf(revenue_arr[i]);
    }
    
    BigInteger min_value = dp[0];
    int min_index = 0;
    
    for (int i = k + 1; i < N; i++) {
        if (i - min_index >= k) {
            min_value = dp[i - (k + 1)];
            min_index = i - (k + 1);

            for (int j = i - k; j < i; j++) {
                if (dp[j].compareTo(min_value) < 0) {

                    min_value = dp[j];
                    min_index = j;
                }
            }
        }
        
        dp[i] = min_value.add(BigInteger.valueOf(revenue_arr[i]));
        
        if (dp[i].compareTo(min_value) < 0) {
            min_value = dp[i];
            min_index = i;
        }
    }
    
    // minimum revenue loss between N - (k + 1) and N - 1 (end of array)
    BigInteger min_rev_loss = dp[N - (k + 1)];
    for (int i = N - k; i < N; i++) {
        if (dp[i].compareTo(min_rev_loss) < 0) {
            min_rev_loss = dp[i];
        }
    }
    
    BigInteger res= total.subtract(min_rev_loss);
    return res.longValue();

}







public static void main(String[] args) {
    // Test case
    //int k = 5;
    //int[] revenue = {6, 7, 12, 13, 14};


    Scanner scanner = new Scanner(System.in);
    int k = scanner.nextInt();
    int n = scanner.nextInt();
    int[] revenue = new int[n];
    for(int i = 0; i < n; i++){
        revenue[i] = scanner.nextInt();
    }
    scanner.close();


    ArrayList<Integer> revenue_list = new ArrayList<>();
    for(int i = 0; i < revenue.length; i++){
        revenue_list.add(revenue[i]);
    }

    System.out.println(Billboards.billboards(k, revenue_list));
}

}
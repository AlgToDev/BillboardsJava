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
    //dp[i] represents the minimum loss when we remove revenue i and the positions before optimally to respect the k constraint. That is, the smallest amount of money that company has to lose by removing k billboards in a row from revenue[0, i]. 
    
    if (k == N) {
        return total.longValue();
    } 
    
    for (int i = 0; i < k + 1; i++) {
        dp[i] = BigInteger.valueOf(revenue_arr[i]);
    }
    
    BigInteger min_val = dp[0];
    int min_idx = 0;
    
    // dp[i] = min(dp[i - k - 1], ..., dp[i - 1]) + revenue[i]

    //The double for loop computes the minimum min(dp[i - k - 1 : i])
    //O(nk)
    for (int i = k + 1; i < N; i++) {
        if (i - min_idx >= k) {
            min_val = dp[i - (k + 1)];
            min_idx = i - (k + 1);

            for (int j = i - k; j < i; j++) {
                if (dp[j].compareTo(min_val) < 0) {

                    min_val = dp[j];
                    min_idx = j;
                }
            }
        }
        //dp[i] = min(dp[i - k - 1], ..., dp[i - 1]) + revenue[i]
        dp[i] = min_val.add(BigInteger.valueOf(revenue_arr[i]));

        System.out.println("dp[" + i + "] = " + dp[i]);
        
        //update min_val and min_index
        if (dp[i].compareTo(min_val) < 0) {
            min_val = dp[i];
            min_idx = i;
        }
    }
    
    // minimum revenue loss between N - (k + 1) and N - 1 (end of array)
    BigInteger min_rev_loss = dp[N - (k + 1)];
    for (int i = N - k; i < N; i++) {
        if (dp[i].compareTo(min_rev_loss) < 0) {
            min_rev_loss = dp[i];
        }
    }

    //print revenue
    System.out.println("revenue = " + Arrays.toString(revenue_arr));
    //print dp
    System.out.println("dp = " + Arrays.toString(dp));

    //print total
    System.out.println("total = " + total);

    //print min_rev_loss
    System.out.println("min_rev_loss = " + min_rev_loss);
    
    BigInteger res= total.subtract(min_rev_loss);
    return res.longValue();

}







public static void main(String[] args) {
    // Test case
    //int k = 2;
    //int[] revenue = {6, 7, 12, 13, 14};


    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int k = scanner.nextInt();
    int[] revenue = new int[n];
    for(int i = 0; i < n; i++){
        revenue[i] = scanner.nextInt();
    }
    scanner.close();


    ArrayList<Integer> revenue_list = new ArrayList<>();
    for(int i = 0; i < revenue.length; i++){
        revenue_list.add(revenue[i]);
    }

    System.out.println(BigIntegerBillboards.billboards(k, revenue_list));
}

}

/*    
6 2
1
2
3
1
6
10


6 2
1
2
3
3
6
10

7 2
1
2
3
3
6
10
12


*/ 
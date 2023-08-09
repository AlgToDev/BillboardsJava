
def billboards(k, revenue):
    N = len(revenue)
    
    total = sum(revenue)

    dp = [0] * N
    
    if k == N:
        return total
    else:
        # Initialize the dp array with the first k + 1 revenues
        for i in range(k + 1):
            dp[i] = revenue[i]
        
        # Keep track of the minimum value and index in the sliding window of size k + 1
        min_value = min(dp[:k + 1])
        min_index = dp.index(min_value)
        
        # Update the dp array using the recurrence relation
        # dp[i] = min(dp[i - k - 1], ..., dp[i - 1]) + revenue[i]
        for i in range(k + 1, N):
            # If the minimum value is outside the window, find a new one
            if i - min_index > k:
                min_value = min(dp[i - k - 1 : i])
                min_index = dp.index(min_value)
            
            # Add the revenue of the current billboard to the minimum value in the window
            dp[i] = min_value + revenue[i]
            
            # Update the minimum value and index if needed
            if dp[i] < min_value:
                min_value = dp[i]
                min_index = i
        
        # Return the total revenue minus the minimum value in the last window
        return total - min(dp[N - k - 1 :])
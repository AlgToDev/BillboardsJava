def billboards(k, revenue):
    N = len(revenue)
    
    total = sum(revenue)
    
    if k == N:
        return total
    else:
        x = revenue[: k + 1]
        
        min_value = min(x)
        min_index = x.index(min_value)
        
        for i in range(k + 1, N):
            if i - min_index >= k:
                min_value = min(x[i - (k + 1) : i + 1])
                min_index = x.index(min_value)
            
            x.append(min_value + revenue[i])
            
            if x[i] < min_value:
                min_value = x[i]
                min_index = i
        
        return total - min(x[N - (k + 1) :])
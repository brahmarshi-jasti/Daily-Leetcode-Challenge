import java.util.*;

class Solution {

  static int dp[];
  static class Work {
    int startTime;
    int endTime;
    int profit;
    Work(int startTime, int endTime, int profit) {
      this.startTime = startTime;
      this.endTime = endTime;
      this.profit = profit;
    }
  }

  static int nearestCompatibleJob(Work arr[], int i) {
    int x = arr[i - 1].startTime;
    int l = 0;
    int r = i - 1;

    while (l <= r) {
      int mid = (l + r) / 2;
      if (arr[mid].endTime <= x && arr[mid + 1].endTime > x) {
        return mid;
      } else if (arr[mid].endTime <= x) {
        l = mid + 1;
      } else {
        r = mid - 1;
      }
    }
    return -1;
  }












  static int maxProfit(Work arr[], int n) {

    if (dp[n] >= 0) return dp[n];
    if (n == 0) {
      return dp[0];
    }
    int compatibleJobIndex = nearestCompatibleJob(arr, n);
    int profitJobN = arr[n - 1].profit;
    int inclusionCaseProfit = 0;

    if (compatibleJobIndex != -1)
      inclusionCaseProfit =  profitJobN + maxProfit(arr, compatibleJobIndex + 1);
    else {
      inclusionCaseProfit = profitJobN ;
    }

    int exclusionCaseProfit = maxProfit(arr, n - 1);
    if (dp[n] == -1)
      dp[n] = Math.max(inclusionCaseProfit, exclusionCaseProfit);

    return dp[n];
  }

  public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {

    int len = profit.length;
    Work arr[] = new Work[len];
    for (int i = 0; i < len ; i++) {
      arr[i] = new Work(startTime[i], endTime[i], profit[i]);
    }

    Arrays.sort(arr, new Comparator<Work> () {
      public int compare(Work w1, Work w2) {
        return w1.endTime - w2.endTime;
      }
    });
    
    int n = arr.length;
    dp = new int[n + 1];
    dp[0] = 0;
    for (int i = 1; i <= n; i++) {
      dp[i] = -1;
    }
    return maxProfit(arr, n);
  }
}

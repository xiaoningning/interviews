package interviews.arrays;

import java.util.Stack;

/**
 * Given an island represented as an array of integer heights, return the amount of rain it can
 * contain.
 * 
 * For instance, given that island, the amount of rain is 12 (3 missing X).
 *        _       _   _
 *       | |X X X| |_| |
 *      _| |X X X|     |_   _
 *    _|   |X X _|       |_| |_
 *  _|     |_ X|               |_
 * |         |_|                 |
 * [0 1 2 4 1 0 2 4 3 4 2 1 2 1 0]
 *
 * @author Francois Rousseau
 */
public class Rainforest {
  /**
   * Let n = length(heights).
   * Time complexity:  O(n^2)
   * Space complexity: O(1)
   */
  public static int bruteforce(int[] heights) {
    int rain = 0;
    for(int i = 0; i < heights.length; i++) {
      int left = max(heights, 0, i - 1);
      int right = max(heights, i + 1, heights.length - 1);
      if(left > heights[i] && right > heights[i]) {
        rain += Math.min(left, right) - heights[i];
      }
    }
    return rain;
  }

  /**
   * Find the maximum element in heights[lo...hi] in O(hi - lo) time.
   */
  private static int max(int[] heights, int lo, int hi) {
    int max = 0;
    for(int i = lo; i <= hi; i++) {
      if(heights[i] > max) {
        max = heights[i];
      }
    }
    return max;
  }

  /**
   * Let n = length(heights).
   * Time complexity:  O(n), elements are pushed and popped at most once each
   * Space complexity: O(n)
   */
  public static int stack(int[] heights) {
    int rain = 0;
    Stack<Integer> stack = new Stack<Integer>();
    for(int i = 0; i < heights.length; i++) {
      while(!stack.isEmpty() && heights[i] > heights[stack.peek()]) {
        int pop = stack.pop();
        if(stack.isEmpty()) {
          break;
        }
        int height = Math.min(heights[stack.peek()], heights[i]) - heights[pop];
        rain += (i - stack.peek() - 1) * height;
      }
      stack.push(i);
    }
    return rain;
  }
}
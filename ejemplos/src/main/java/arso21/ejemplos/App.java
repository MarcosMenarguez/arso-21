package arso21.ejemplos;

import java.util.Arrays;

public class App {


  public void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }

  public int partition(int[] nums, int start, int end) {
    int key = nums[start];
    int i = start + 1;
    for (int k = start + 1; k <= end ; k++) {
      if (nums[k] <= key) {
        if (i != k)
          swap(nums, i, k);
        i++;
      }
    }
    if (start != i - 1)
      swap(nums,start ,i-1);
    return i-1;
  }

  public void quicksort(int[] nums, int start, int end) {
    if (start <= end) {
      int index = partition(nums, start, end);
      quicksort(nums, start, index - 1);
      quicksort(nums, index + 1, end);
    }
  }

  public void quicksort(int[] nums) {
    quicksort(nums, 0, nums.length - 1);
  }

  public static void main(String[] args) {
    int[] nums = {1, 3, 5, 4, 7, 0};
    new App().quicksort(nums);
    System.out.println("nums = " + Arrays.toString(nums));
  }

}

package arso21.ejemplos;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestApp {

  @Test
  public void testQsort() {
    //given
    Random random = new Random();
    int[] nums = new int[20];
    for (int j = 0; j < 20; j++) {
      nums[j] = random.nextInt(100);
    }
    int[] nums1 = Arrays.copyOf(nums, nums.length);
    Arrays.sort(nums1);
    //when
    new App().quicksort(nums);
    //then
    Assertions.assertArrayEquals(nums, nums1);
  }

}

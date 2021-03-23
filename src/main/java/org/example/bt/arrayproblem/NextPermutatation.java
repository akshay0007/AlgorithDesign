package org.example.bt.arrayproblem;

public class NextPermutatation {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        NextPermutatation nextPermutatation = new NextPermutatation();
        nextPermutatation.nextPermutation(nums);
    }

    public void nextPermutation(int[] nums) {
        int preIndex = 0;
        int firstItem = nums[preIndex];
        for (int index = 1; index < nums.length; index++) {
            if (nums[index] > firstItem) {
                swapElement(preIndex, index, nums);
            }
        }
    }

    private void swapElement(int srcIndex, int dstIndex, int[] nums) {
        int temp = nums[srcIndex];
        nums[srcIndex] = nums[dstIndex];
        nums[dstIndex] = temp;
    }

}

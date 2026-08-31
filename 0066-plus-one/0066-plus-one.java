class Solution {
    public int[] plusOne(int[] digits) {
        int carry = 1; // Hume +1 karna hai
        for (int i = digits.length - 1; i >= 0; i--) {
            if (carry == 0) {
                break;
            }
            int sum = digits[i] + carry;
            if (sum == 10) {
                digits[i] = 0;
                carry = 1;
            } 
            else {
                digits[i] = sum;
                carry = 0;
            }
        }
        if (carry == 1) 
        {
            int[] ans = new int[digits.length + 1];
            ans[0] = 1;
            return ans;
        } 
        else {
            return digits;
        }
    }
}
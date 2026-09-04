class Solution {
    public boolean uniformArray(int[] nums1) {

        boolean hasOdd = false;
        boolean hasEven = false;

        for (int num : nums1) {
            if (num % 2 == 0)
                hasEven = true;
            else
                hasOdd = true;
        }

        // Agar sirf ek hi parity hai
        if (!hasOdd || !hasEven)
            return true;

        // Dono parity present hain
        // Odd number ko even banane ke liye difference chahiye
        // aur even number ko odd banane ke liye bhi.
        // Possible only when minimum element is odd.
        
        int min = nums1[0];

        for (int num : nums1) {
            min = Math.min(min, num);
        }

        return min % 2 == 1;
    }
}
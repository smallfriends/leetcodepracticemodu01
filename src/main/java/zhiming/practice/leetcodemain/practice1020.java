package zhiming.practice.leetcodemain;

import java.util.LinkedList;
import java.util.Stack;

public class practice1020 {
    public static void main(String[] args) {

    }
}

class Solution02 {        //旋转数组的最小数字
    public int minArray(int[] numbers) {
        int low = 0;
        int high = numbers.length - 1;
        while(low < high) {
            int pivot = low + (high - low) / 2;
            if(numbers[pivot] > numbers[high]) {
                low = pivot + 1;
            } else if(numbers[pivot] < numbers[high]) {
                high = pivot;
            } else {
                high -= 1;
            }
        }
        return numbers[low];
    }
}

class Solution {
    public int cuttingRope(int n) {
        if(n <= 3) {
            return n - 1;
        }
        //n = 3*a + b
        int res = 0;
        int a = n / 3;
        int b = n % 3;
        double tmp = 0;
        switch (b) {
            case 0:
                tmp = 0;
                break;
            case 1:
                tmp = 4 / 3.0;
                break;
            case 2:
                tmp = 2;
                break;
        }
        tmp = tmp == 0 ? 1 : tmp;
        res = (int) (Math.pow(3, a) * tmp);
        return res;
    }
}

class CQueue {
    private Stack<Integer> s1;
    private Stack<Integer> s2;

    public CQueue() {
        this.s1 = new Stack<Integer>();
        this.s2 = new Stack<Integer>();
    }

    public void appendTail(int value) {
        s1.push(value);
    }

    public int deleteHead() {
        if(s2.empty()) {
            while(!s1.empty()) {
                Integer tmp = s1.pop();
                s2.push(tmp);
            }
        }
        if(s2.empty()) {
            return -1;
        } else {
            Integer res = s2.pop();
            return res;
        }
    }
}

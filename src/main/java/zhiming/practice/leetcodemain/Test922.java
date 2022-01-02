package zhiming.practice.leetcodemain;

import java.util.Stack;

public class Test922 {
    public static void main(String[] args) {
        int[] pushed = {1,2,3,4,5};
        int[] popped = {4,5,3,2,1};
        System.out.println(validateStackSequences(pushed, popped));

    }

    static boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> tmp = new Stack<Integer>();
        int j = 0;
        for(int i = 0; i < pushed.length; i++) {
            if(tmp.empty()) {
                tmp.push(pushed[i]);
            } else if(tmp.peek() == popped[j]) {
                tmp.pop();
                j++;
                i--;
            } else {
                tmp.push(pushed[i]);
            }
        }
        for(; j < popped.length; j++) {
            if(tmp.peek() == popped[j]) {
                tmp.pop();
            } else {
                return false;
            }
        }
        return tmp.empty();
    }
}



class MinStack {
    private Stack<Integer> A, B;

    /** initialize your data structure here. */
    public MinStack() {
        A = new Stack<Integer>();
        B = new Stack<Integer>();
    }

    public void push(int x) {
        A.push(x);
        if(B.empty()) {
            B.push(x);
        } else if(x <= B.peek()) {
            B.push(x);
        } else {
            B.push(B.peek());
        }
    }

    public void pop() {
        A.pop();
        B.pop();
    }

    public int top() {
        return A.peek();
    }

    public int min() {
        return B.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */

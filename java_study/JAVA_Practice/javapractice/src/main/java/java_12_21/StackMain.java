package java_12_21;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class StackMain {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("꽹가리");
        stack.push("고릴라");
        stack.push("아르헨티나 월드컵 우승");
stack.pop();
stack.pop();
        System.out.println(stack);


        Queue<String> que = new PriorityQueue<>();
        que.offer("que");
        que.offer("들어온다");
        que.offer("가ㅏ나다");
        System.out.println(que);
    }
}

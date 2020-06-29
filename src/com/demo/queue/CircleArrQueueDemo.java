package com.demo.queue;

import java.util.Scanner;

/**
 * @author Li Luopu
 * @version 1.0
 * @date 2020/6/28 15:10
 * 数组模拟环形队列
 */
public class CircleArrQueueDemo {
    public static void main(String[] args) {
        CircleArr queue = new CircleArr(4);
        char key = ' ';
        //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入要存入的数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': //取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }

        }
        System.out.println("程序退出");
    }

    static class CircleArr {
        private int maxSize; //表示队列的最大容量
        //front 指向队列的第一个元素，也就是说arr[front]=队列的第一个元素
        //front的初始值为0
        private int front;
        //rear 指向队列的最后一个元素的后一个位置，因为要空出一个空间作为约定
        //rear的初始值为0
        private int rear;
        private int[] arr; //该数组模拟队列 用于存放数据

        public CircleArr(int arrMaxSize) {
            maxSize = arrMaxSize;
            arr = new int[maxSize];
            front = 0;
            rear = 0;
        }

        //判断是否满
        public boolean isFull() {
            return (rear + 1) % maxSize == front;
        }

        //判断是否为空
        public boolean isEmpty() {
            return rear == front;
        }

        //添加数据
        public void addQueue(int n) {
            //判断是否满
            if (isFull()) {
                System.out.println("满了");
                return;
            }
            //直接将数据加入
            arr[rear] = n;
            //将rear 后移，这里必须考虑取模
            rear = (rear + 1) % maxSize;
        }

        //获取队列的数据
        public int getQueue() {
            //判断队列是否为空
            if (isEmpty()) {
                //通过一场抛出
                throw new RuntimeException("空。没有数据");
            }
            //这里分析front是指向队列的第一个元素
            //1,先把front对应的值保留到一个临时变量
            //2,将front后移,考虑取模
            //3,将临时保存的变量返回
            int value = arr[front];
            front = (front + 1) % maxSize;
            return value;
        }

        //显示队列所有数据
        public void showQueue() {
            //遍历
            //先判断是否为空
            if (isEmpty()) {
                System.out.println("队列空，没有数据");
            }
            //从front开始遍历
            for (int i = front; i < front + size(); i++) {
                System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
            }
        }

        //求出当前队列有效个数
        public int size() {
            return (rear + maxSize - front) % maxSize;
        }

        //输出队列头元素
        public int headQueue() {
            if (isEmpty()) {
                throw new RuntimeException("null");
            }
            return arr[front];
        }

    }
}
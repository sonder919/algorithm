package com.demo.queue;

import java.util.Scanner;

/**
 * @author Li Luopu
 * @version 1.0
 * @date 2020/4/21 14:36
 */
public class ArrQueueDemo {
    public static void main(String[] args) {
        ArrQueue queue=new ArrQueue(3);
        char key=' ';
        //接收用户输入
        Scanner scanner=new Scanner(System.in);
        boolean loop=true;
        //输出一个菜单
        while (loop){
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入要存入的数");
                    int value=scanner.nextInt();
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
}
    class ArrQueue{
        private int maxSize; //表示队列的最大容量
        private int front; //队列头
        private int rear; //队列尾
        private int[] arr; //该数组模拟队列 用于存放数据

        //创建队列的构造器
        public ArrQueue(int arrMaxSize){
            maxSize=arrMaxSize;
            arr=new int[maxSize];
            front=-1; //指向队列头部，指向队列头部数据的前一个位置
            rear=-1; //指向队列尾部，也就是尾部的数据
        }
        //判断队列是否存满
        public boolean isFull(){
            return rear==maxSize-1;
        }
        //判断队列是否为空
        public boolean isEmpty(){
            return rear==front;
        }

        //向队列添加数据
        public void addQueue(int n){
            if (isFull()){
                System.out.println("队列已满，不能存放数据");
                return;
            }
            rear++; //后移
            arr[rear]=n;
        }
        //从队列中取出数据
        public int getQueue(){
            if (isEmpty()){
                throw new RuntimeException("队列空，不能取数据");
            }
            front++; //头部后移
            return arr[front];
        }
        //展示队列头部数据
        public int headQueue(){
            if (isEmpty()){
                throw new RuntimeException("队列空");
            }
            return arr[front+1];
        }
        //展示队列所有数据
        public void showQueue(){
            if (isEmpty()){
                System.out.println("队列空，没数据");
                return;
            }
            for (int i=0;i<arr.length;i++){
                System.out.printf("arr[%d]=%d\n", i, arr[i]);
            }
        }
    }


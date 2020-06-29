package com.demo.sparsearray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author Li Luopu
 * @version 1.0
 * @date 2020/4/16 10:54
 */
public class SparseArray {
    public static void main(String[] args) throws Exception {
        //先创建一个二维数组记录棋盘11*11的规格
        int chess[][] =new int[11][11];
        //0代表没棋子，1代表黑子，2代表蓝子
        chess[0][1]=1;
        chess[1][3]=2;
        chess[2][4]=1;
        chess[1][2]=2;
        chess[3][2]=1;
        chess[1][4]=2;
        chess[2][5]=1;
        chess[1][6]=2;
        chess[3][7]=1;
        chess[1][1]=2;
        System.out.println("这是二维数组棋盘：-------------------------");
        for (int[] row:chess){
            for (int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }


        //将二维数组转换成稀疏数组
        int sum = 0;
        //第一步：循环遍历二维数组，得到非零数的个数sum
        for (int i=0;i<11;i++){
            for (int j=0;j<11;j++){
                if (chess[i][j]!=0){
                    sum++;
                }

            }
        }
        //第二步：创建稀疏数组
        int sparseArr[][]=new int[sum+1][3];
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=sum;
        //第三步: 遍历二维数组将非0的值存放到sparseArr中
        int count=0; //计数器，用于记录第几个非0的
        for (int i=0;i<11;i++){
            for (int j=0;j<11;j++){
                if (chess[i][j]!=0){
                    count++;
                    sparseArr[count][0]=i;
                    sparseArr[count][1]=j;
                    sparseArr[count][2]=chess[i][j];
                }

            }
        }
        System.out.println();
        System.out.println("得到的稀疏数组：-------------------------");
        for (int i=0;i<sparseArr.length;i++){
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }




        //将稀疏数组用io写进文件中，实现存档
        File file=new File("E:\\work11\\algorithm\\chess.txt");
        FileWriter fileWriter=new FileWriter(file);
        for (int[] rows:sparseArr){
            for (int datas:rows){
                fileWriter.write(datas+"\t");
            }
            fileWriter.write("\r\n");
        }
        fileWriter.close();
        System.out.println("存档成功");

        //读取存档，复原棋盘
        BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
        String line;
        int row=0;
        //创建新的稀疏数组接收数据
        int sparseNewArr[][]=new int[sum+1][3];
        while ((line =bufferedReader.readLine())!=null){
            String[] temps=line.split("\t");
            for (int i=0;i<temps.length;i++){
                sparseNewArr[row][i] = Integer.parseInt(temps[i]);//?

            }
            row++;
        }
        bufferedReader.close();
        System.out.println("读取出来的稀疏数组为：");
        for (int[] rows : sparseNewArr) {
            for (int data : rows) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //将稀疏数组恢复成二维数组
        //1、将稀疏数组的第一行数据赋给二维数组得到整个棋盘
        int chessAr2[][]=new int[sparseArr[0][0]][sparseArr[0][1]];
        //2、将稀疏数组后面几行的数据 赋给原始的二维数组即可
            for (int i=1;i<sparseArr.length;i++){
            chessAr2[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }
        //输出二维数组
            System.out.println("恢复成二维数组-------------------------");
            for (int[] row1:chessAr2){
            for (int data:row1){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}

/*
* https://codeforces.com/problemset/problem/581/D
*
* I dont know what to do with this
* Krishn sent me ts
* Thanks big Quad
* Apparently its rated 1700
* */

import java.util.Scanner;
import java.util.function.Predicate;
public class Billboard{
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args){
        Predicate<Double> isWholeNumber = (x) -> x == Math.floor(x);
        int[][] ads = {{0,0},{0,0},{0,0}};
        int areaSum = 0;
        for(int[] ad: ads){
            for(int i = 0; i < 2; i++){
                ad[i] = input.nextInt();
            }
            areaSum += (ad[0] * ad[1]);
        }
        if(isWholeNumber.test(Math.sqrt(areaSum))){
            int billBoardSide = (int) Math.sqrt(areaSum);
            System.out.println(billBoardSide);

            int[][] billboard = new int[billBoardSide][billBoardSide];

        }else{
            System.out.println(-1);
        }
    }
}
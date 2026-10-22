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
        int[][] ads = {{0,0,65},{0,0,66},{0,0,67}}; // X Y CHARACTER
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

            char[][] billboard = new char[billBoardSide][billBoardSide];

            int[] singleDimensionLargestAd = ads[0];
            int largestDimensionItem = ads[0][0];

            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 2; j++){
                    if(ads[i][j] > largestDimensionItem){
                        largestDimensionItem = ads[i][j];
                        singleDimensionLargestAd = ads[i];
                        break;
                    }
                }
            }

            if(singleDimensionLargestAd[1] < singleDimensionLargestAd[0]){
                singleDimensionLargestAd[0] += singleDimensionLargestAd[1];
                singleDimensionLargestAd[1] = singleDimensionLargestAd[0] - singleDimensionLargestAd[1];
                singleDimensionLargestAd[0] -= singleDimensionLargestAd[1];
            }

            int[] pointer = {0,0};
            for(int i = 0; i < singleDimensionLargestAd[0]; i++){
                for(int j = 0; j< singleDimensionLargestAd[1]; j++){
                    billboard[i][j] = (char) singleDimensionLargestAd[2];
                    pointer[0] = i;
                    pointer[1] = j;
                }
            }

            pointer[0] = (pointer[0] % billBoardSide);
            pointer[1] = pointer[1] % billBoardSide;

            for(char[] chRow : billboard){
                for(char ch : chRow){
                    System.out.print(ch);
                }
                System.out.println();
            }

        }else{
            System.out.println(-1);
        }
    }
}
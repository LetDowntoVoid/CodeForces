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
        long start = System.nanoTime();

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

            char[][] billboard = new char[billBoardSide][billBoardSide];

            int[] singleDimensionLargestAd = ads[0];
            int largestDimensionItem = ads[0][0];

            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 2; j++){
                    if(ads[i][j] > largestDimensionItem){
                        largestDimensionItem = ads[i][j];
                        singleDimensionLargestAd = ads[i];
                    }
                }
            }

            if(largestDimensionItem != billBoardSide){
                System.out.println(-1);
                return;
            }

            if(singleDimensionLargestAd[1] < singleDimensionLargestAd[0]){
                singleDimensionLargestAd[0] += singleDimensionLargestAd[1];
                singleDimensionLargestAd[1] = singleDimensionLargestAd[0] - singleDimensionLargestAd[1];
                singleDimensionLargestAd[0] -= singleDimensionLargestAd[1];
            }


            int XPointer = 0;
            int YPointer = 0;
            for(int i = 0; i < singleDimensionLargestAd[0]; i++){
                for(int j = 0; j < singleDimensionLargestAd[1]; j++){
                    billboard[i][j] = (char) singleDimensionLargestAd[2];
                    XPointer = i;
                    YPointer = j;
                }
            }
            XPointer = (XPointer + 1) % billBoardSide;
            YPointer = (YPointer + 1) % billBoardSide;

            int startIndex = (ads[0] != singleDimensionLargestAd) ? 0 : 1;
            int[] remLargestAreaAd = ads[startIndex];
            for(int[] ad : ads) {
                if (
                        ad[2] != singleDimensionLargestAd[2] &&
                                (remLargestAreaAd[0] * remLargestAreaAd[1]) < (ad[0] * ad[1]) //this is disgusting
                ) {
                    remLargestAreaAd = ad;
                }
            }

            int remH = billBoardSide - XPointer;
            int a = remLargestAreaAd[0];
            int b = remLargestAreaAd[1];

            // flush -> fits in the leftover strip AND touches an edge of it
            boolean asIsFlush = a <= remH && b <= billBoardSide && (a == remH || b == billBoardSide);
            boolean rotFlush  = b <= remH && a <= billBoardSide && (b == remH || a == billBoardSide);

            if(!asIsFlush && rotFlush){
                remLargestAreaAd[0] = b;
                remLargestAreaAd[1] = a;
            } else if(!asIsFlush){
                System.out.println(-1);
                return;
            }

            for(int i = 0; i < remLargestAreaAd[0]; i++){
                for(int j = 0; j< remLargestAreaAd[1]; j++){
                    billboard[i + XPointer][j + YPointer] = (char) remLargestAreaAd[2];
                }
            }

            char unusedChar = (char) (ads[0][2] + ads[1][2] + ads[2][2]
                                    - singleDimensionLargestAd[2]
                                    - remLargestAreaAd[2]);

            // fill remaining array with unusedChar its confirmed that its meant to be fully filled
            for(int i = 0; i < billBoardSide; i++){
                for(int j = 0; j < billBoardSide; j++){
                    if(billboard[i][j] == '\0'){
                        billboard[i][j] = unusedChar;
                    }
                }
            }



            if(validate(billboard, ads)){
                System.out.println(billBoardSide);
                for(char[] chRow : billboard){
                    for(char ch : chRow){
                        System.out.print(ch);
                    }
                    System.out.println();
                }
            }else{
                System.out.println(-1);
            }

        }else{
            System.out.println(-1);
        }

        Runtime rt = Runtime.getRuntime();
        System.err.println("Memory: " + (rt.totalMemory() - rt.freeMemory()) / 1024 + " KB");
        System.err.println("Time: " + (System.nanoTime() - start) / 1_000_000 + " ms");
    }

    public static boolean validate(char[][] billboard, int[][] ads) {
        int n = billboard.length;
        int[] minR = {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE};
        int[] maxR = {-1, -1, -1};
        int[] minC = {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE};
        int[] maxC = {-1, -1, -1};


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < billboard[i].length; j++) {
                int k = billboard[i][j] - 'A';

                if (k < 0 || k > 2) {
                    return false; // unfilled or unexpected char
                }

                minR[k] = Math.min(minR[k], i);
                maxR[k] = Math.max(maxR[k], i);
                minC[k] = Math.min(minC[k], j);
                maxC[k] = Math.max(maxC[k], j);
            }
        }

        for (int k = 0; k < 3; k++) {
            if (maxR[k] == -1) {
                return false;// letter never appears
            }
            char expected = (char) ('A' + k);
            for (int i = minR[k]; i <= maxR[k]; i++) {
                for (int j = minC[k]; j <= maxC[k]; j++) {
                    if (billboard[i][j] != expected) {
                        return false;
                    }
                }
            }

            int h = maxR[k] - minR[k] + 1;
            int w = maxC[k] - minC[k] + 1;
            boolean dimsMatch = (h == ads[k][0] && w == ads[k][1])
                                    || (h == ads[k][1] && w == ads[k][0]);
            if (!dimsMatch) {
                return false;
            }
        }
        return true;
    }
}
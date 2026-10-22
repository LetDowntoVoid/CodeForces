import java.util.Scanner;
public class A617 {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args){
        int distance = input.nextInt();
        int[] steps = {5,4,3,2,1};
        int acc = 0;
        for(int step : steps){
            acc += distance / step;
            distance %= step;
        }
        System.out.println(acc);
    }
}

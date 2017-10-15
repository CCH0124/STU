import java.util.Scanner;
class matrix{
    static Scanner sc = new Scanner(System.in);
    // input matrix
    public static int[][] input() {
        System.out.println("input matrix:");
        int column = sc.nextInt();
        int row = sc.nextInt();
        int arr[][] = new int[column][row];
        return arr;
    }

    // input value
    public static int[][] inputValue(int a[][]) {
        System.out.println("input matrix value:");
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[i].length; j++)
                a[i][j] = sc.nextInt();
        return a;
    }
}
class symmetric extends matrix{
    private static final String SYMMETRIC = "symmetric";
    private static final String SKEW_SYMMETRIC = "skew_symmetric"; 
    
    public symmetric(){ //建構方法
        System.out.println("Judgment SYMMETRIC And SKEW_SYMMETRIC");
    }
    
    private static boolean bl_skewSymmetric(int a[][]){
        boolean bl = true;
        for(int i=0; i<a.length; i++){
            for(int j=0; j<a[i].length; j++){
                if(i==j && a[i][j] == 0){
                    continue;
                }else if(a[i][j] == (-1*a[j][i])) {
                    continue;
                }else{
                    bl = false;
                    break;
                }
            }
        }
        return bl;
    }

    private static boolean bl_symmetric(int a[][]){
        boolean bl = true;
        for(int i=0; i<a.length; i++){
            for(int j=0; j<a[i].length; j++){
                if(i==j && (a[i][j] == 0 || a[i][j] !=0)){
                    continue;
                }else if(a[i][j] == a[j][i]) {
                    continue;
                }else{
                    bl = false;
                    break;
                }
            }
        }
        return bl;
    }

    public static String isSymmetric(int arr[][]){
        if(bl_symmetric(arr) && bl_skewSymmetric(arr)) // 矩陣值都為 0 兩者都是
            return SYMMETRIC +" and "+SKEW_SYMMETRIC;
        else if(bl_symmetric(arr))
            return SYMMETRIC;
        else if(bl_skewSymmetric(arr))
            return SKEW_SYMMETRIC;
        else
            return "Nothing";   
    }

}

public class multiplication extends matrix{

    // Message
    private static final String FAIL = "Condition does not match";
    private static final String TRUE = "Condition match";

    public multiplication(){ //建構方法
        System.out.println("Matrix multiplication");
    }
    // 矩陣相乘
    public static int[][] multiplication(int a[][],int b[][]){
        int c[][]= new int[a.length][b[0].length];
        int i=0,j=0,k=0;
        for(i=0; i<c.length; i++){
            for(j=0; j<c[i].length; j++){
                for(k=0; k<a[i].length; k++){
                    c[i][j] += a[i][k]*b[k][j];
                }
            }
        }
        return c;
    }
    // 結過印出
    public static void display(int a[][]){
        System.out.println("result:");
        for(int arr[] : a){
            for(int arr1 : arr){
                System.out.printf("%-4d",arr1);
            }
            System.out.printf("\n");
        }
    }

    // 判斷 matrix_multiplication A[row]==B[row] condition
    public static String ismatrix(int a[][],int b[][]){
        inputValue(a);
        inputValue(b);
        if(a[0].length == b.length){
            display(multiplication(a,b));
            return TRUE;
        }else{
            return FAIL;
        }
    }

    public static void main(String[] args) {
        multiplication test = new multiplication();
        System.out.println(test.ismatrix(input(), input()));
        symmetric test2 = new symmetric();
        System.out.println(test2.isSymmetric(inputValue(input())));
    }
}

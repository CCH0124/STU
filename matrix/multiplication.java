import java.util.LinkedList;
import java.util.List;
import java.util.Collections;
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
    
    public symmetric(){
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
        if(bl_symmetric(arr) && bl_skewSymmetric(arr))
            return SYMMETRIC +" and "+SKEW_SYMMETRIC;
        else if(bl_symmetric(arr))
            return SYMMETRIC;
        else if(bl_skewSymmetric(arr))
            return SKEW_SYMMETRIC;
        else
            return "Nothing";   
    }

}
/* ############################################################################################################################################### */
// class  Gauss_Jordan extends matrix_multiplication{
    
//     public static List<int[]> store(int [][]matrix){
//         List<int[]> list = new LinkedList<>();
//         for(int i=0; i<matrix.length; i++)
//             list.add(matrix[i]);
//         return list;
//     }
    
//     // exchange
//     public static List<int []> col_Change(int[][]matrix, int a,int b){
//         Collections.swap(store(matrix), a, b);
//         return list;
//     }
//     // *
//     public static List<int []> col_multiple(int[][]matrix,int cola,int colb,int num){
//         List<int[]> list = new LinkedList<>();
//         list = store(matrix);

//         int a [] = (int [])list.get(cola);
//         int b [] = (int [])list.get(colb);
//         for(int j=0; j<b.length ; j++){
//             b[j] = a[j] * num + b[j];
//         }
//         return list;
//     }
//     // /
//     public static List<int []> col_div(int[][]matrix,int cola,int colb,int num){
//         List<int[]> list = new LinkedList<>();
//         list = store(matrix);

//         int a [] = (int [])list.get(cola);
//         int b [] = (int [])list.get(colb);
//         for(int j=0; j<b.length ; j++){
//             b[j] = a[j] / num + b[j];
//         }
//         return list;
//     }

    
//     public static void display(LinkedList<int []> list){
//         for(int i=0; i<list.size(); i++){
//             int b [] = (int [])list.get(i);  
//             for(int j=0; j<b.length ; j++){
//                 System.out.printf("%4d",b[j]);
//             }
//             System.out.println();
//         }

//     }

// }
/*#########################################################################################################################*/
public class multiplication extends matrix{

    // Message
    private static final String FAIL = "Condition does not match";
    private static final String TRUE = "Condition match";

    public multiplication(){
        System.out.println("Matrix multiplication");
    }
    // 
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
    // 
    public static void display(int a[][]){
        System.out.println("result:");
        for(int arr[] : a){
            for(int arr1 : arr){
                System.out.printf("%-4d",arr1);
            }
            System.out.printf("\n");
        }
    }

    // judgment matrix_multiplication A[row]==B[row]condition
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
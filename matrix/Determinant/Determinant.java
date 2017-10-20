import java.util.ArrayList;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

abstract class AbstractMatrix_assign { // 為定義完成的類別
    private int matrix[][]; 
    private Scanner sc;
    protected abstract int assign(int[][] matrix, int row, int col); // 為定義完成的方法
    protected int odd(int row, int col) { // 傳 + - (規則)
        if ((row + col) % 2 == 0)
            return 1;
        else
            return -1;
    }
    public int[][] setMatrix(int [][]matrix){
        return this.matrix = matrix;
    }
    
    public int[][] getMatrix() {
        return matrix;
    }
    protected int[][] input(int length){ // 輸入陣列的值
        sc = new Scanner(System.in);
        System.out.println("input " + length + "*" + length + " Value:");
        int arr [][] = new int[length][length];
        
        
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[i].length; j++){
                arr[i][j] = sc.nextInt();
            }
        }
        sc.close();
        return setMatrix(arr);
    }
}
    /********************************************************
    * 計算餘因子                                             *
    * int[][] matrix 帶入陣列                                *
    * row 提供要計算的列                                      *
    * col 提供要計算的行                                      *
    *********************************************************/
class Cofactors extends AbstractMatrix_assign{
    public Cofactors() { // 建構方法
        System.out.println("This is the cofactors");
    }

    protected int assign(int[][] matrix, int row, int col) { // 餘因子計算
        List<Integer> list = new ArrayList<>();
        if ((row < 0 || row > matrix.length) || (col < 0 || col > matrix.length)) // 此方陣為 3*3 ，row、col 不得超過方陣大小
            return 0;

        for (int i = 0; i < matrix.length; i++) {
            if (i == (row - 1)) // 程式 index 從 0，使用者從 1
                continue;
            for (int j = 0; j < matrix[i].length; j++) {
                if (j == (col - 1)) // 程式 index 從 0，使用者從 1
                    continue;
                list.add(matrix[i][j]);
            }
        }
        System.out.println("matrix[" + (row) + "][" + (col) + "]:");
        return odd(row, col) * (list.get(0) * list.get(list.size() - 1) - list.get(1) * list.get(2));
    }
    
}
    /********************************************************
    * 計算行列式                                             *
    * int[][] matrix 帶入陣列                                *
    * row 提供要計算的列                                      *
    * col 提供要計算的行                                      *
    *********************************************************/
public class Determinant extends AbstractMatrix_assign{

    public Determinant(){
        System.out.println("This is the determinant");
    }

    protected int assign(int [][]matrix, int row,int col){ // 計算餘因子
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<matrix.length; i++){
            if(i==(row-1)) continue;
            for(int j=0; j<matrix[i].length; j++){
                if(j==(col-1)) continue;
                list.add(matrix[i][j]);
            }
        }
        return odd(row,col) * (list.get(0)*list.get(list.size()-1) - list.get(1)*list.get(2));
    }

    private void display(Map<String,Integer> map){ // 顯示
        for (Object key : map.keySet()) {
            System.out.println(key + " : " + map.get(key));
        }
    }
    /************************************************
    * detA 儲存從列到行，餘因子
    * detB 儲存從行到列，餘因子
    * sumA 每列餘因子相加
    * sumB 每行餘因子相加
    */
    public int determinant(int length){ // 行列式計算
        
        input(length); // 呼叫方法
        int matrix [][] = getMatrix();
  
        Map<String,Integer> map = new TreeMap<>(); // TreeMap 可排序，依據 key
        int detA = 0, detB = 0, i, j,sumA = 0,sumB = 0 ;
        for(i=1; i<=matrix.length; i++){
            sumA = 0;
            sumB = 0;
            for(j=1; j<=matrix[i-1].length; j++){
                detA = assign(matrix,i, j) * matrix[i-1][j-1];
                sumA += detA;
                detB = assign(matrix,j, i) * matrix[j-1][i-1];              
                sumB += detB;
                map.put(("row>col " + (i - 1) + (j - 1) + "=>" + assign(matrix, i, j) + "*" + matrix[i - 1][j - 1]
                        + " = " + detA).toString(), sumA);
                map.put(("col>row " + (j - 1) + (i - 1) + "=>" + assign(matrix, j, i) + "*" + matrix[j - 1][i - 1]
                        + " = " + detB).toString(), sumB);
                   
            }
        }
        display(map);
        map.clear(); // 清空 Map 儲存的值
        return sumA;
    }

    public static void main(String[] args) {
        Determinant a = new Determinant();
        System.out.println(a.determinant(3));
        Cofactors c = new Cofactors();
        int [][] arr = {{0,2,1},{3,-1,2},{4,0,1}};
        System.out.println(c.assign(arr , 2, 3));

    }
}

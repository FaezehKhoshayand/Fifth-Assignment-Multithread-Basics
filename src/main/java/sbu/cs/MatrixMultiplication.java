package sbu.cs;

import java.util.ArrayList;
import java.util.List;

public class MatrixMultiplication {

    // You are allowed to change all code in the BlockMultiplier class
    public static class BlockMultiplier implements Runnable
    {
        List<List<Integer>> tempMatrixProduct;
        List<List<Integer>> AMatrix;
        List<List<Integer>> BMatrix;
        int row;
        int col;
        public BlockMultiplier(List<List<Integer>> AMatrix, List<List<Integer>> BMatrix, int row, int col) {
            // TODO
            this.AMatrix = AMatrix;
            this.BMatrix = BMatrix;
            this.row = row;
            this.col = col;
        }

        @Override
        public void run() {
            tempMatrixProduct = new ArrayList<>();
            /*
            TODO
                Perform the calculation and store the final values in tempMatrixProduct
            */
            for(int i = 0; i < AMatrix.size()/2; i++) {
                tempMatrixProduct.add(new ArrayList<>());
                for(int j = 0; j < BMatrix.get(0).size()/2; j++) {
                    tempMatrixProduct.get(i).add(0);
                }
            }
            for(int i = row; i < row + (AMatrix.size()/2); i++) {
                for(int j = col ; j < col + (BMatrix.get(0).size()/2); j++) {
                    int temp = 0;
                    for(int k = 0; k < BMatrix.size(); k++) {
                        temp += AMatrix.get(i).get(k) * BMatrix.get(k).get(j);
                    }
                    tempMatrixProduct.get(i - row).set(j - col, temp);
                }
            }
        }
    }

    /*
    Matrix A is of the form p x q
    Matrix B is of the form q x r
    both p and r are even numbers
    */
    public static List<List<Integer>> ParallelizeMatMul(List<List<Integer>> matrix_A, List<List<Integer>> matrix_B)
    {
        /*
        TODO
            Parallelize the matrix multiplication by dividing tasks between 4 threads.
            Each thread should calculate one block of the final matrix product. Each block should be a quarter of the final matrix.
            Combine the 4 resulting blocks to create the final matrix product and return it.
         */
        return null;
    }

    public static void main(String[] args) {
        // Test your code here
    }
}

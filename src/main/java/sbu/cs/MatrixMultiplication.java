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
    public static List<List<Integer>> ParallelizeMatMul(List<List<Integer>> matrix_A, List<List<Integer>> matrix_B) throws InterruptedException {
        /*
        TODO
            Parallelize the matrix multiplication by dividing tasks between 4 threads.
            Each thread should calculate one block of the final matrix product. Each block should be a quarter of the final matrix.
            Combine the 4 resulting blocks to create the final matrix product and return it.
         */
        BlockMultiplier[] a = new BlockMultiplier[4];
        Thread[] thread = new Thread[4];
        a[0] = new BlockMultiplier(matrix_A,  matrix_B,0,0);
        thread[0] = new Thread(a[0]);
        thread[0].start();
        a[1] = new BlockMultiplier(matrix_A,  matrix_B,0, matrix_B.get(0).size()/2);
        thread[1] = new Thread(a[1]);
        thread[1].start();
        a[2] = new BlockMultiplier(matrix_A,  matrix_B, matrix_A.size()/2,0);
        thread[2] = new Thread(a[2]);
        thread[2].start();
        a[3] = new BlockMultiplier(matrix_A,  matrix_B,matrix_A.size()/2,matrix_B.get(0).size()/2);
        thread[3] = new Thread(a[3]);
        thread[3].start();
        for(Thread temp : thread) {
            try {
                temp.join();
            } catch (InterruptedException e) {
                System.out.println(e.getStackTrace());
            }
        }
        a[0].tempMatrixProduct.addAll(a[2].tempMatrixProduct);
        for (int i = 0; i < matrix_A.size()/2; i++) {
            a[0].tempMatrixProduct.get(i).addAll(a[1].tempMatrixProduct.get(i));
        }
        for (int i = 0; i < matrix_A.size()/2; i++) {
            a[2].tempMatrixProduct.get(i).addAll(a[3].tempMatrixProduct.get(i));
        }
        return a[0].tempMatrixProduct;
    }

    public static void main(String[] args) {
        // Test your code here
    }
}

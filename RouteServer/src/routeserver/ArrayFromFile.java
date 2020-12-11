package routeserver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ArrayFromFile {

    static int[][] getMatrix() throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader("src/routeserver/matrix.txt")));
        int rows = 170;
        int columns = 170;
        int[][] matrixAdj = new int[rows][columns];
        while (sc.hasNextLine()) {
            for (int i = 0; i < matrixAdj.length; i++) {
                String[] line = sc.nextLine().trim().split(" ");
                for (int j = 0; j < line.length; j++) {
                    matrixAdj[i][j] = Integer.parseInt(line[j]);
                }
            }
        }

        return matrixAdj;
    }
}

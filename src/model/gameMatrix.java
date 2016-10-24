package model;

import java.util.ArrayList;

/**
 * DO THE DOCUMENTATION YOU LAZY ASS
 * Created by Hugues on 24/10/2016.
 */
public class gameMatrix {
    public static int defaultSize = 4;
    public static int emptySquareMarker = 0;


    private int[][] squaresArray;


    public gameMatrix(int size) {

        // If the size is incorrect, put the default one
        if (size <= 0) size = defaultSize;

        squaresArray = new int[size][size];
        int num = 1;
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                squaresArray[i][j] = num;
                num+=1;
            }
        }

        //Put the last square as a null (0)
        squaresArray[size][size] = emptySquareMarker;
    }

    /**
     * Returns the value inside the square (x,y)
     * @param x : x coordinate
     * @param y : y coordinate
     * @return the value
     */
    public int getValueOfSquare(int x, int y){
        try{
            return squaresArray[x][y];
        }
        catch (java.lang.ArrayIndexOutOfBoundsException e){
            System.out.println("ERROR : " + e.getMessage());
        }
        return -5;
    }

    /**
     * Tells if the (x,y) square is empty
     * @param x : x coordinate
     * @param y : y coordinate
     * @return true if empty
     */
    public boolean isSquareEmpty(int x, int y){
        try{
            return (squaresArray[x][y] != emptySquareMarker);
        }
        catch (java.lang.ArrayIndexOutOfBoundsException e){
            System.out.println("ERROR : " + e.getMessage());
        }
        return false;
    }




}

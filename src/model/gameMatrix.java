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
    private int size;


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

        this.size = size;
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

    /**
     * Switch two squares (only if one of them is the empty one)
     * @param x1 : x coordinate of the first square
     * @param y1 : y coordinate of the first square
     * @param x2 : x coordinate of the second square
     * @param y2 : y coordinate of the second square
     * @return true if the switch was made
     */
    public boolean switchSquares(int x1, int y1, int x2, int y2){
        try{
            // Do the switch only if one of the squares is the empty one
             if(squaresArray[x2][y2] == emptySquareMarker || squaresArray[x1][y1] == emptySquareMarker){
                 int temp = squaresArray[x1][y1];
                 squaresArray[x2][y2] = squaresArray[x1][y1];
                 squaresArray[x1][y1] = temp;

                 return true;
             }
            return false;
        }
        catch (java.lang.ArrayIndexOutOfBoundsException e){
            System.out.println("ERROR : " + e.getMessage());
        }
        return  false;
    }

    /**
     * Checks the integrity of the grid.
     * The grid is correct if every value is unique and within the bounds
     * @return
     */
    public boolean checkIntegrity(){

        int min = 1;
        int max = this.size * this.size - 1;

        ArrayList<Integer> valuesList = new ArrayList<>(max + 1);

        boolean integrityChecked = true;

        // Return false ..
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                //... If the value is not within bounds ( 1 to max)
                if ((squaresArray[i][j] < min && squaresArray[i][j] != emptySquareMarker)
                        || squaresArray[i][j] > max) {
                    return  false;
                }
                //... If the value is already existing
                if (valuesList.contains((Integer)squaresArray[i][j])) return false;
            }
        }


        return  true;
    }




}

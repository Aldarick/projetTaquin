package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.StringJoiner;

/**
 * DO THE DOCUMENTATION YOU LAZY ASS
 * Created by Hugues on 24/10/2016.
 */
public class GameGrid {
    public static int defaultSize = 4;
    public static int emptySquareMarker = 0;


    private int[][] squaresArray;
    private int size;


    public GameGrid(int size) {

        // If the size is incorrect, put the default one
        if (size <= 0) size = defaultSize;
        this.size = size;

        squaresArray = new int[size][size];
        this.initialize();

    }

    /**
     * Initializes the grid (fill it and shuffle it)
     */
    private void initialize(){
        int num = 1;
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                this.squaresArray[i][j] = num;
                num+=1;
            }
        }

        //Put the last square as an empty one
        this.squaresArray[size-1][size-1] = emptySquareMarker;

        this.shuffle();
    }


    /**
     * @return the size of the grid
     */
    public int getSize(){return this.size;}

    /**
     * Returns the value inside the square (x,y)
     * @param x : x coordinate
     * @param y : y coordinate
     * @return the value
     */
    public int getValueOfSquare(int x, int y){
        try{
            return squaresArray[y][x];
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
        x-=1;y-=1;
        try{
            return (squaresArray[y][x] == emptySquareMarker);
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
        x1-=1;x2-=1;y1-=1;y2-=1;
        try{
            // Do the switch only if one of the squares is the empty one
             if(squaresArray[y2][x2] == emptySquareMarker || squaresArray[y1][x1] == emptySquareMarker){
                 int temp = squaresArray[y2][x2];
                 squaresArray[y2][x2] = squaresArray[y1][x1];
                 squaresArray[y1][x1] = temp;

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
     * @return true if the integrity is checked
     */
    public boolean checkIntegrity(){

        int min = 1;
        int max = this.size * this.size - 1;

        ArrayList<Integer> valuesList = new ArrayList<>(max + 1);

        boolean integrityChecked = true;
        int value;

        // Return false ..
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                value = squaresArray[i][j];
                //... If the value is not within bounds ( 1 to max)
                if ((value < min && value != emptySquareMarker)
                        || value > max) {
                    return  false;
                }
                //... If the value is already existing
                if (valuesList.contains(value)) return false;

                valuesList.add(value);
            }
        }


        return  true;
    }

    /**
     * Shuffles the grid
     */
    public void shuffle(){

        shuffleGrid(this.squaresArray, this.size, new Random());
    }

    /** Shuffles a 2D array with the same number of columns for each row. */
    private static void shuffleGrid(int[][] matrix, int columns, Random rnd) {
        int size = matrix.length * columns;
        for (int i = size; i > 1; i--)
            swap(matrix, columns, i - 1, rnd.nextInt(i));
    }

    /**
     * Swaps two entries in a 2D array, where i and j are 1-dimensional indexes, looking at the
     * array from left to right and top to bottom.
     */
    private static void swap(int[][] matrix, int columns, int i, int j) {
        int tmp = matrix[i / columns][i % columns];
        matrix[i / columns][i % columns] = matrix[j / columns][j % columns];
        matrix[j / columns][j % columns] = tmp;
    }


    /**
     * @return the grid a s a string
     */
    public String toString() {
        String str = "";
        for (int i = 0; i < size; i++) {
            str += "| ";
            for (int j = 0; j < size; j++) {
                str+= String.format("%4d", squaresArray[i][j]) ;
                str+= " | ";
            }
            if (i < size - 1)str+=System.lineSeparator();
        }
        return str;
    }


}

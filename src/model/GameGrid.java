package model;

import controller.MainController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * DO THE DOCUMENTATION YOU LAZY ASS
 * Created by Hugues on 24/10/2016.
 */
public class GameGrid {
    public static int emptyTileMarker = 0;


    private int[][] tilesArray;
    private int size;
    private Tile empty;


    public GameGrid(int size, int numberOfMoves) {

        // If the size is incorrect, put the default one
        if (size <= 0) size = MainController.defaultSize;
        this.size = size;

        tilesArray = new int[size][size];
        this.initialize();
        this.shuffle(numberOfMoves);

    }

    // Default constructors
    public GameGrid(int size) {
        this(size, MainController.defaultRandomMovesNumber);
    }
    public GameGrid() {
        this(MainController.defaultSize, MainController.defaultRandomMovesNumber);
    }



    /**
     * Initializes the grid (fill it and shuffle it)
     */
    private void initialize(){
        int num = 1;
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                this.tilesArray[i][j] = num;
                num+=1;
            }
        }

        //Put the last square as an empty one
        this.empty = new Tile(size-1, size-1);
        this.tilesArray[empty.getX()][empty.getY()] = emptyTileMarker;
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
            return tilesArray[y][x];
        }
        catch (java.lang.ArrayIndexOutOfBoundsException e){
            System.out.println("ERROR : " + e.getMessage());
        }
        return -5;
    }

    /**
     * @return : the empty tile
     */
    public Tile emptyTile(){
        return this.empty;
    }


    /**
     * @return : the Tiles which are valid moves (neighbours of the empty tile)
     */
    public List<Tile> validMoves(){

        ArrayList<Tile> validMovesList = new ArrayList<>();

        // We don't need to iterate through the whole array, since we know where the empty tile is. So we just put
        // in the list the neighbours of the tile.
        // Iterating through the whole array would be very costly for a big array.
        for(int deltaX=-1; deltaX<2; deltaX++) {
            for(int deltaY=-1; deltaY<2; deltaY++) {
                Tile tp = new Tile(empty.getX() + deltaX, empty.getY() + deltaY);
                if( isValidMove(tp) ) {
                    validMovesList.add(tp);
                }
            }
        }
        return validMovesList;

    }

    /**
     * @param tile : the tile to test
     * @return : true if the tile given as a parameter is a valid move
     */
    public boolean isValidMove(Tile tile) {
        return !(tile.getX() < 0 || tile.getX() >= this.size
                || tile.getY() < 0 || tile.getY() >= this.size) && this.empty.isNeighbour(tile);
    }

    /**
     * Move a tile (swap it with the empty one)
     * @param tile : the tile to move
     * @return : true if the tile could be moved
     */
    public boolean move(Tile tile) {
        if( !isValidMove(tile) ) {
            return false;
        }
        this.switchTiles(empty, tile);
        empty = tile;
        return true;
    }



    /**
     * Switch two squares (only if one of them is the empty one)
     * @param x1 : x coordinate of the first square
     * @param y1 : y coordinate of the first square
     * @param x2 : x coordinate of the second square
     * @param y2 : y coordinate of the second square
     * @return true if the switch was made
     */
    @Deprecated
    private boolean switchTiles(int x1, int y1, int x2, int y2){
        try{
                 int temp = tilesArray[x2][y2];
                 tilesArray[x2][y2] = tilesArray[x1][y1];
                 tilesArray[x1][y1] = temp;
                 return true;
        }
        catch (java.lang.ArrayIndexOutOfBoundsException e){
            System.out.println("ERROR : " + e.getMessage());
        }
        return  false;
    }

    /**
     * Switch two tiles
     * (Should not be used without exceptions check first)
     * @param t1 : the first tile
     * @param t2 : the second tile
     */
    private void switchTiles(Tile t1, Tile t2){
        int tmp = tilesArray[t1.getX()][t1.getY()];
        tilesArray[t1.getX()][t1.getY()] = tilesArray[t2.getX()][t2.getY()];
        tilesArray[t2.getX()][t2.getY()] = tmp;
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

        int value;

        // Return false ..
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                value = tilesArray[i][j];
                //... If the value is not within bounds ( 1 to max)
                if ((value < min && value != emptyTileMarker)
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
     * Shuffles the grid with a number of random moves. The more moves we give, the more "shuffled" the grid
     * will be
     * @param numberOfMoves : the number of random moves to make
     */
    public void shuffle(int numberOfMoves){

        // If we shuffle randomly, we have the risk of getting an unsolvable grid. To avoid this, we initialize the
        // grid (this gives the solved one) and make a number of random moves. Each move will be a valid one, therefore
        // the final shuffled grid will be a solved one

        this.initialize();
        for(int i=0; i<numberOfMoves; i++) {
            // We get the list of all the possible moves
            List<Tile> possible = this.validMoves();

            // We decide randomly which move to do
            int rnd =  (int) (Math.random() * possible.size());
            Tile rndMove = possible.get(rnd);

            // We move the tile
            this.move(rndMove);
        }

    }



    /**
     * @return the grid a s a string
     */
    public String toString() {
        String str = "";
        for (int i = 0; i < size; i++) {
            str += "| ";
            for (int j = 0; j < size; j++) {
                str+= String.format("%4d", tilesArray[i][j]) ;
                str+= " | ";
            }
            if (i < size - 1)str+=System.lineSeparator();
        }
        return str;
    }

    /**
     * @return an arrayList containing the values from top to bottom, left to right
     */
    public ArrayList<Integer> toArrayList(){
        ArrayList<Integer> myInt = new ArrayList<>(size*size);



        for(int i = 0; i <size; i++){
            for(int j=0; j<size;j++){
                myInt.add(tilesArray[i][j]);
            }
        }
        System.out.println("Array :" + myInt.toString());

        return myInt;
    }

    /*
    /**
     * @return : an array containing the x and y of the empty square
     */
    /*
    public int[] coordinatesOfEmpty() {
        int x = -5;
        int y = -5;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(tilesArray[j][i] == emptyTileMarker){
                    x = j;
                    y = i;
                }
            }
        }
        int coord[] = new int[2];
        coord[0] = x;
        coord[1] = y;
        return  coord;
    }
    */
    /*
    /**
     * Shuffles the grid
     */
    /*
    private void shuffle(){

        shuffleGrid(this.tilesArray, this.size, new Random());
    }*/

    /*
    /** Shuffles a 2D array with the same number of columns for each row. */
    /*
    private static void shuffleGrid(int[][] matrix, int columns, Random rnd) {
        int size = matrix.length * columns;
        for (int i = size; i > 1; i--)
            swap(matrix, columns, i - 1, rnd.nextInt(i));
    }*/

    /*
    /**
     * Swaps two entries in a 2D array, where i and j are 1-dimensional indexes, looking at the
     * array from left to right and top to bottom.
     */
    /*
    private static void swap(int[][] matrix, int columns, int i, int j) {
        int tmp = matrix[i / columns][i % columns];
        matrix[i / columns][i % columns] = matrix[j / columns][j % columns];
        matrix[j / columns][j % columns] = tmp;
    }/*

    /**
     * @return : the empty tile
     */


}

package controller;
import model.GameGrid;
import view.MainView;

import java.awt.*;

/**
 * Coucou <3
 * Created by Hugues on 24/10/2016.
 */
public class MainController {
    public static int defaultSize = 4;

    private MainView view;
    private GameGrid gameGrid;

    public MainController(int size){
        gameGrid = new GameGrid(size);
        System.out.println(gameGrid.toString());

        this.view = new MainView(gameGrid.toArrayList(), size, this);;

    }

    /**
     * Requests a swap in the given coordinates with the empty square
     * @param x : the x coordinate
     * @param y : the y coordinate
     */
    public void requestSwap(int x, int y){
        int emptyX, emptyY;
        int coord[] = this.gameGrid.coordinatesOfEmpty();
        emptyX = coord[0];
        emptyY = coord[1];

        System.out.println("Requesting swap between : (" + x + " ; " + y + ") and (" + emptyX + " ; " + emptyY + ")");

        if(this.coordAreNeighbours(x,y,emptyX,emptyY)){
            System.out.println("Swapping");
            if(this.gameGrid.switchSquares(x,y,emptyX,emptyY)){
                System.out.println("Swapping successful");
            }else {
                System.out.println("Swapping failed!!!");
            }


            this.view.swapButtons(x,y,emptyX,emptyY);
            System.out.println(this.gameGrid.toString());
        }

    }

    /**
     * Tells if two coordinates of those of neighbours
     * @param x1 : the x coordinate of the first button
     * @param y1 : the y coordinate of the first button
     * @param x2 : the x coordinate of the second button
     * @param y2 : the y coordinate of the second button
     * @return true if they are neighbours' coordinates
     */
    public boolean coordAreNeighbours(int x1, int y1, int x2, int y2){

        return (x1 == x2 && (Math.abs(y1-y2) == 1)) || (y1 == y2 && (Math.abs(x1-x2) == 1));

    }












    public static void main(String[] args) throws Exception {
        int size = 4;
        MainController controller = new MainController(size);
    }
}



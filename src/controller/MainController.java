package controller;
import model.GameGrid;
import model.Tile;
import view.MainView;

/**
 * Coucou <3
 * Created by Hugues on 24/10/2016.
 */
public class MainController {
    public static int defaultSize = 4;
    public static int defaultRandomMovesNumber = 80;

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
        Tile empty = this.gameGrid.emptyTile();


        System.out.println(" --- Test ---");
        int value = this.gameGrid.valueOf(new Tile(x,y));
        System.out.println("   Value of tile : " + value);
        Tile coord = this.gameGrid.whereIs(value);
        System.out.println("   Coordinates of " + value + " : (" + coord.getX() + " ; " + coord.getY() + ")");
        System.out.println(" ------------");
        System.out.println("Requesting swap between : (" + x + " ; " + y + ") and (" + empty.getX() + " ; " + empty.getY() + ")");

        if(this.gameGrid.move(new Tile(x,y))){
            System.out.println("Swapping");
            this.view.swapButtons(x,y,empty.getX(),empty.getY());
            System.out.println(this.gameGrid.toString());

            // Check if we won
            if (this.gameGrid.isWon()) {
                System.out.println("Win");
                this.view.disableButtons(this.gameGrid.getSize());
            }
        }
        else {
            System.out.println("Incorrect move");
        }
        System.out.println("========================");

    }


    public static void main(String[] args) throws Exception {
        int size = 4;
        MainController controller = new MainController(size);
    }
}



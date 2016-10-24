package controller;
import model.GameGrid;
/**
 * Coucou <3
 * Created by Hugues on 24/10/2016.
 */
public class MainController {







    public static void main(String[] args) throws Exception {

        GameGrid matrix = new GameGrid(4);
        System.out.println(matrix.toString());

        System.out.println("Is (4,4) empty?");
        System.out.println(matrix.isSquareEmpty(4,4));
        System.out.println("Is (3,4) empty?");
        System.out.println(matrix.isSquareEmpty(3,4));

        System.out.println("SWAP");
        matrix.switchSquares(4,4,3,4);
        System.out.println(matrix.toString());

    }


}

package view;
import controller.MainController;
import javafx.event.ActionEvent;
import model.GameGrid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static java.lang.Math.sqrt;

/**
 * Heeeeeey
 * Created by Hugues on 24/10/2016.
 */
public class MainView extends JFrame implements ActionListener {


    private SquareButton[][] buttonsGrid;
    private MainController controller;

    public MainView(ArrayList<Integer> grid, int size, MainController controller){
        this.controller = controller;
        buttonsGrid = new SquareButton[size][size];
        JPanel pan = new JPanel();
        this.setTitle("Jeu du taquin");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(pan);

        // Initialize the window
        this.initialize(grid, size);


        // Show the window
        this.setVisible(true);

    }


    private void initialize(ArrayList<Integer> grid, int size){

        GridLayout gl = new GridLayout(size, size);
        this.setLayout(gl);

        for(int i=0;i < size*size;i++){
            int x = i/size;
            int y = i%size;
            SquareButton btn = new SquareButton(((grid.get(i)).toString()), x, y);
            this.getContentPane().add(btn);
            this.buttonsGrid[i/size][i%size] = btn;
            btn.addActionListener(this);


            if (btn.getLabel().equals(Integer.toString(GameGrid.emptySquareMarker))) btn.setEmpty();
        }
    }

    /**
     * Swaps the text of two buttons
     * @param btn1 : the first button
     * @param btn2 : the second button
     */
    public void swapButtons(Button btn1, Button btn2){
        String txt = btn1.getLabel();
        btn2.setLabel(btn1.getLabel());
        btn1.setLabel(txt);
    }

    /**
     * Swaps the text of two buttons.
     * /!\ The second button must be the empty one /!\
     * @param x1 : the x coordinate of the first button
     * @param y1 : the y coordinate of the first button
     * @param x2 : the x coordinate of the second button
     * @param y2 : the y coordinate of the second button
     */
    public void swapButtons(int x1, int y1, int x2, int y2){

        String txt = this.buttonsGrid[x2][y2].getLabel();
        this.buttonsGrid[x2][y2].setLabel(this.buttonsGrid[x1][y1].getLabel());
        this.buttonsGrid[x1][y1].setLabel(txt);

        this.buttonsGrid[x2][y2].setFilled();
        this.buttonsGrid[x1][y1].setEmpty();
    }



    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        SquareButton btn = ((SquareButton)(e.getSource()));
        System.out.println("Button : " + btn.getLabel());
        this.controller.requestSwap(btn.getSquareX(), btn.getSquareY());
    }
}





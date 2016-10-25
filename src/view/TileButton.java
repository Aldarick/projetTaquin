package view;

import javax.swing.*;
import java.awt.*;

/**
 * pouet
 * Created by Hugues on 24/10/2016.
 */
class TileButton extends JButton {

    private int squareX, squareY;

    TileButton(String label, int squareX, int squareY) throws HeadlessException {
        super(label);
        this.squareX = squareX;
        this.squareY = squareY;
    }

    /**
     * @return : the x coordinate in the grid of the button
     */
    int getSquareX() {
        return squareX;
    }

    /**
     * @return : the y coordinate in the grid of the button
     */
    int getSquareY() {
        return squareY;
    }

    @Override
    public String getLabel() {
        return super.getLabel();
    }

    void setEmpty(){
        this.setBackground(Color.lightGray);
    }
    void setFilled(){
        this.setBackground(null);
    }
}

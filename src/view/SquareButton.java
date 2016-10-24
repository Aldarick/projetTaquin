package view;

import java.awt.*;

/**
 * pouet
 * Created by Hugues on 24/10/2016.
 */
class SquareButton extends Button {

    private int squareX, squareY;

    SquareButton(String label, int squareX, int squareY) throws HeadlessException {
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

    public void setEmpty(){
        this.setBackground(Color.lightGray);
    }
    public void setFilled(){
        this.setBackground(null);
    }
}

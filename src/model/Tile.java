package model;

/**
 * poupidoupida!
 * Created by Hugues on 25/10/2016.
 */
public class Tile {

    private int x;
    private int y;

    public Tile(int x, int y) {
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Tells if this tile is the neighbour of the one given as a parameter
     * @param tile : the tile to test
     * @return true if they are neighbours
     */
    public boolean isNeighbour(Tile tile){
        int x1 = this.getX();
        int y1 = this.getY();

        int x2 = tile.getX();
        int y2 = tile.getY();
        return (x1 == x2 && (Math.abs(y1-y2) == 1)) || (y1 == y2 && (Math.abs(x1-x2) == 1));
    }


}

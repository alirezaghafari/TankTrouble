package tankGame;

public class Square {
    private int xCoordinate;
    private int yCoordinate;//so the point is (xCoord,yCoord)

    //Basic constructor:
    public Square(int a, int b) {
        this.xCoordinate = a;
        this.yCoordinate = b;
    }

    //Getter and Setter methods:
    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public String toString() {
        return "" + xCoordinate + "," + yCoordinate;
    }

    public boolean equals(Square square) {
        return this.getXCoordinate() == square.getXCoordinate() && this.getYCoordinate() == square.getYCoordinate();
    }
}



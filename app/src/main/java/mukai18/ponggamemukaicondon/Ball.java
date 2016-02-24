package mukai18.ponggamemukaicondon;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;


/**
 * Created by mukai18 on 2/21/2016.
 */
public class Ball {


    public static final int INIT_SIZE = 50;
    public static final int MIN_SIZE = 3;
    public static final int MAX_SIZE = 100;

    protected float x; // x-coord
    protected float y; // y-coord
    protected float vx;
    protected float vy;
    private int size = INIT_SIZE; // all spots begin at size 20
    protected Paint myPaint; // how the spot is drawn
    protected int maxX; // width of the screen (pixels)
    protected int maxY; // height of the screen (pixels)
    protected int minX;
    protected int minY;


    /**
     * gives the spot a random colored paint
     */
    protected void setRandomPaint() {
        int color = Color.rgb((int) (Math.random() * 256),
                (int) (Math.random() * 256), (int) (Math.random() * 256));
        myPaint = new Paint();
        myPaint.setColor(color);
    }

    /**
     * this ctor initializes a spot with random values
     */
    public Ball() {
        // place a spot in a random location
        x = (int) (Math.random() * 500) + 5;
        y = (int) (Math.random() * 500) + 5;
        vx = vy = 0;
        setRandomPaint();
    }

    /**
     * this ctor creates a spot at a specified location
     */
    public Ball(int initX, int initY) {
        // place a spot in a random location
        x = initX;
        y = initY;
        vx = vy = 0;
        setRandomPaint();
    }

    /**
     * changes the spot's color
     */
    public void setColor(int newColor) {
        myPaint.setColor(newColor);
    }

    /**
     * retrieves the spot's color
     */
    public int getColor() {
        return myPaint.getColor();
    }

    /**
     * accessors
     */
    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    /**
     * setPos
     * <p/>
     * sets the spot's x,y position.
     * <p/>
     * CAVEAT:  Caller is responsible for giving valid values!
     *
     * @param newX, newY  the new x,y position
     */
    public void setPos(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int newSize) {
        if ((newSize >= MIN_SIZE)
                && (newSize <= MAX_SIZE)) {
            this.size = newSize;
        }
    }

    /**
     * adjusts the spot's position by a given delta. If the change would move
     * the spot outside of a given boundary, it is stopped at that boundary
     *
     * @param deltaXY   how much to change the spots x,y position by
     * @param maxXY     the maximum allowed value for x,y position
     * @param minXY     the minimum allowed value for x,y position
     */
    public void moveSpot(float aX, float aY, int maxX, int maxY, int minX, int minY) {


        //apply the requested change
        vx += aX;
        vy += aY;

        vx *= .99f;
        vy *= .99f;

        x += vx;
        y += vy;



        // adjust the spot if it goes off an edge
        if (x + getSize() > maxX) {
            x = maxX - getSize();
            vx = -.8f * vx;
        }
        if (y + getSize() > maxY) {
            y = maxY - getSize();
            vy = -.8f * vy;
        }
        if (x - getSize() < minX) {
            x = getSize();
            vx = -.8f * vx;
        }
        if (y - getSize() < minY) {
            y = getSize();
            vy = -.8f * vy;
        }

    }//moveSpot


    /**
     * determines whether this spot overlaps another one
     *
     * @param other the other spot that I may overlap
     * @return true if this spot overlaps the given spot
     */
    public boolean overlaps(Ball other) {
        //Determine the distance between the two spots
        float xDist = Math.abs(other.x - this.x);
        float yDist = Math.abs(other.y - this.y);
        float dist = (int) Math.sqrt(xDist * xDist + yDist * yDist);

        return dist <= this.getSize() + other.getSize();
    }//overlaps



    /**
     * a spot knows how to draw itself on a given canvas
     *
     * @param canvas is the canvas to draw upon
     */
    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, getSize(), myPaint);
    }
}


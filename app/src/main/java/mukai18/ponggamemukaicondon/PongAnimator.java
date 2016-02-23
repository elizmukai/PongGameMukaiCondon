package mukai18.ponggamemukaicondon;

import android.graphics.*;
import android.view.MotionEvent;


/**
 * class that animates a ball repeatedly moving diagonally on
 * simple background
 *
 * @author Teresa Condon
 * @author Liz Mukai
 *
 * @version 2/26/16
 */
public class PongAnimator implements Animator {

    // instance variables
    private int count = 0; // counts the number of logical clock ticks
    private boolean goBackwards = false; // whether clock is ticking backwards
    private int x = 0;
    private int y = 0;
    private int radius = 60;
    private int height = 0;
    private int width = 0;

    /**
     * Interval between animation frames: .03 seconds (i.e., about 33 times
     * per second).
     *
     * @return the time interval between frames, in milliseconds.
     */
    public int interval() {
        return 30;
    }

    /**
     * The background color: a light blue.
     *
     * @return the background color onto which we will draw the image.
     */
    public int backgroundColor() {
        // create/return the background color
        return Color.rgb(227, 206, 246);
        // external citation from color codes
    }

    /**
     * Tells the animation whether to go backwards.
     *
     * @param b true iff animation is to go backwards.
     */
    public void goBackwards(boolean b) {
        // set our instance variable
        goBackwards = b;
    }

    /**
     * Action to perform on clock tick
     *
     * @param g the graphics object on which to draw
     */
    public void tick(Canvas g) {
        // bump our count either up or down by one, depending on whether
        // we are in "backwards mode".
        if (goBackwards) {
            count--;
        }
        else {
            count++;
        }

        drawBalls(g);

        drawWalls(g);

        drawPaddle(g);

    }

    public void drawBalls(Canvas g) {

        // Determine the pixel position of our ball.  Multiplying by 15
        // has the effect of moving 15 pixel per frame.  Modding by 600
        // (with the appropriate correction if the value was negative)
        // has the effect of "wrapping around" when we get to either end
        // (since our canvas size is 600 in each dimension).
        width = g.getWidth();
        height = g.getHeight();

        x = (count*15)%(g.getWidth());
        if (x < 0) x += g.getWidth();

        y = (count*15)%(g.getHeight());
        if (y < 0) y += g.getHeight();

        // Draw the ball in the correct position.
        Paint redPaint = new Paint();
        redPaint.setColor(Color.RED);
        g.drawCircle(x, y, radius, redPaint);
        redPaint.setColor(0xff0000ff);
    }

    public void drawWalls(Canvas g) {

        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.FILL);
        g.drawRect(0f, 0f, 80f, g.getHeight()- 80f, paint);

        g.drawRect(0f, 0f, g.getWidth(), 80f, paint);

        g.drawRect(g.getWidth() - 80f, 0f, g.getWidth(), g.getHeight() - 80f, paint);
    }

    public void drawPaddle(Canvas g) {
        Paint paddle = new Paint();
        paddle.setColor(0xFF2EFE64);

        //external citation for color from http://html-color-codes.info/

        g.drawRect((g.getWidth()/2) - 300, g.getHeight() - 80,(g.getWidth()/2) + 300, g.getHeight(),paddle);
    }

    /**
     * Tells that we never pause.
     *
     * @return indication of whether to pause
     */
    public boolean doPause() {
        if ((y+radius > height && (x +radius < (width/2) - 300) | (y + radius > height && (x-radius > (height/2)) | (x+radius < width)))) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Tells that we never stop the animation.
     *
     * @return indication of whether to quit.
     */
    public boolean doQuit() {

        return false;
    }

    /**
     * reverse the ball's direction when the screen is tapped
     */
    public void onTouch(MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            goBackwards = !goBackwards;
        }
    }



}//class TextAnimator

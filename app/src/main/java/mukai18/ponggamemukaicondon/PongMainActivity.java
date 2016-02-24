package mukai18.ponggamemukaicondon;

import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import java.util.Random;

/**
 * PongMainActivity
 *
 * This is the activity for the Pong game. It attaches a mukai18.ponggamemukaicondon.PongAnimator to
 * an mukai18.ponggamemukaicondon.AnimationSurface.
 *
 * @author Andrew Nuxoll
 * @author Steven R. Vegdahl
 * @version July 2013
 *
 */
public class PongMainActivity extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{

    /**
     * creates an mukai18.ponggamemukaicondon.AnimationSurface containing a PongAnimator.
     */

    Button addBalls = null;
    ToggleButton level = null;
    PongAnimator ball = new PongAnimator();
    Ball nBall = new Ball();
    public int paddleX;
    public int paddleY;
    public int paddleW;
    public int paddleH;
    public float vx;
    public float vy;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pong_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Connect the animation surface with the animator
        AnimationSurface mySurface = (AnimationSurface) this.findViewById(R.id.animationSurface);
        mySurface.setAnimator(new PongAnimator());

        vx = nBall.velX();
        vy = nBall.velY();
        nBall.setVelocity(vx,vy);


        addBalls = (Button)findViewById(R.id.moreBalls);
        addBalls.setOnClickListener(this);

        level = (ToggleButton)findViewById(R.id.pickLevel);
        level.setOnCheckedChangeListener(this);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!level.isChecked()) {
            paddleX = ((ball.maxX +80)/2) - 300;
            paddleH = ball.maxY -80;
            paddleW = ((ball.maxX+80)/2) + 300;
            paddleY = ball.maxY;
        }
        else {
            paddleX = ((ball.maxX +80)/2) - 150;
            paddleH = ball.maxY -80;
            paddleW = ((ball.maxX+80)/2) + 150;
            paddleY = ball.maxY;
        }
    }

    public int getPaddleH() {
        return paddleH;
    }

    public int getPaddleW() {
        return paddleW;
    }

    public int getPaddleY() {
        return paddleY;
    }

    public int getPaddleX() {
        return paddleX;
    }

}

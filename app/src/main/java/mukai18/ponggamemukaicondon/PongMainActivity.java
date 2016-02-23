package mukai18.ponggamemukaicondon;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

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
public class PongMainActivity extends Activity implements View.OnClickListener{

    /**
     * creates an mukai18.ponggamemukaicondon.AnimationSurface containing a PongAnimator.
     */

    Button addBalls = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pong_main);

        // Connect the animation surface with the animator
        AnimationSurface mySurface = (AnimationSurface) this.findViewById(R.id.animationSurface);
        mySurface.setAnimator(new PongAnimator());


        addBalls = (Button)findViewById(R.id.moreBalls);
        addBalls.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}

package dpain.com.drinkstonight;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class ViewResults extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_results);

        //Retrieves value from file to display else displays the current value
        SharedPreferences pref = getSharedPreferences(getString(R.string.preference_count_file),0);
        int displayCount = pref.getInt(getString(R.string.prefText), 0);
        String label;

        //Set-up new string to display count value
        if (displayCount==10) {
            label = "You've had " + displayCount + " drinks. Okay time to Stop!";
        }else if (displayCount==20) {
            label = displayCount + " drinks damn!";
        }else if (displayCount==40) {
            label = "It's " + displayCount + " drinks if you were wondering";
        }else if (displayCount==100) {
            label = "You've had " + displayCount + " drinks I think it's about time you decided not to KILL YOURSELF!";
        }else label = "You've had " + displayCount + " drink(s) so far today";

        //Pass the value to textView already on the activity
        TextView resultViewHolder = (TextView) findViewById(R.id.resultview);
        resultViewHolder.setText(String.valueOf(label));

        //Testing
        TextView testViewHolder = (TextView) findViewById(R.id.testview);
        testViewHolder.setText(String.valueOf("--testing--"));

    }

    //Create a new session with count set to zero
    public void buttonNew(View view) {
        SharedPreferences pref = this.getSharedPreferences(getString(R.string.preference_count_file),0);
        SharedPreferences.Editor prefEdit = pref.edit();

        prefEdit.putInt(getString(R.string.prefText), 0);
        prefEdit.apply();
        vibrate();

        finish();
    }

    //Clear all sessions and 'count' values
    public void buttonReset(View view) {
        SharedPreferences pref = this.getSharedPreferences(getString(R.string.preference_count_file),0);
        SharedPreferences.Editor prefEdit = pref.edit();

        prefEdit.putInt(getString(R.string.prefText), 0);
        prefEdit.apply();

        vibrate();

        finish();

        /*Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);*/
    }

    void vibrate()
    {
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(20);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }
}

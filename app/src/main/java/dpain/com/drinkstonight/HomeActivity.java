package dpain.com.drinkstonight;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class HomeActivity extends Activity {

    //public variables
    public final static String ARRAY_COUNTER = "dpain.com.drinkstonight.COUNTER";
    public Button notifCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);

        //Retrieves value from file to display else displays the current value
        int displayCount;
        SharedPreferences pref = this.getSharedPreferences(getString(R.string.preference_count_file),0);
        displayCount = pref.getInt(getString(R.string.prefText), 0);

        View count = menu.findItem(R.id.action_siren).getActionView();
        notifCount = (Button) count.findViewById(R.id.notif_count);
        notifCount.setText(String.valueOf(displayCount));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_siren:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        invalidateOptionsMenu();
        return super.onPrepareOptionsMenu(menu);
    }

    //count each button press
    //Updates settings bar to displays the updated count
    //Vibrates
    public void buttonOnClick(View view) {
        SharedPreferences pref = getSharedPreferences(getString(R.string.preference_count_file),0);
        SharedPreferences.Editor prefEdit = pref.edit();

        int counter = pref.getInt(getString(R.string.prefText), 0);
        counter += 1;
        invalidateOptionsMenu();

        prefEdit.putInt(getString(R.string.prefText), counter);
        prefEdit.apply();

        vibrate();

        Intent notifier = new Intent(this, ViewResults.class);

        //Check count and open new Activity as per condition
        if (counter==10) {
            startActivity(notifier);
        }else if (counter==20) {
            startActivity(notifier);
        }else if (counter==40) {
            startActivity(notifier);
        }else if (counter==100) {
            startActivity(notifier);
        }
    }

    public static void refresh(Activity activity)
    {
        activity.invalidateOptionsMenu();
    }

    //Vibration on Click function
    void vibrate()
    {
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(20);
    }

    //Pass an intent with the current count value and start the activity
    // Important for the feed counter
    public void buttonResultPass(View view) {
        Intent intent = new Intent(this, ViewResults.class);
        startActivity(intent);
    }

}

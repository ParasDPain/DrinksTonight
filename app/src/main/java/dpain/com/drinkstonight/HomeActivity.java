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
    static Button notifCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    //count each button press
    //Updates settings bar to displays the updated count
    //Vibrates
    public void buttonOnClick(View view) {
        SharedPreferences pref = this.getSharedPreferences(getString(R.string.preference_count_file),0);
        SharedPreferences.Editor prefEdit = pref.edit();

        int counter = pref.getInt(getString(R.string.prefText), 0);
        counter += 1;
        invalidateOptionsMenu();

        prefEdit.putInt(getString(R.string.prefText), counter);
        prefEdit.commit();

        vibrate(20);
    }

    //Vibration on Click function
    public void vibrate(int duration)
    {
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(duration);
    }

    //Pass an intent with the current count value and start the activity
    public void buttonResultPass(View view) {
        Intent intent = new Intent(this, ViewResults.class);
        intent.putExtra(ARRAY_COUNTER, 0);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);

        //Retrieves value from file to display else displays the current value
        int displayCount = 0;
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
}

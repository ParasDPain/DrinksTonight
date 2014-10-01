package dpain.com.drinkstonight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import static dpain.com.drinkstonight.HomeActivity.*;


public class ViewResults extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_results);

        //Receive intent and the extra data
        Intent intent = getIntent();
        int j = 0;
        int importedCounter = intent.getIntExtra(ARRAY_COUNTER, j);

        //Set-up new string to display count value
        String label = "You've had " + importedCounter + " drink(s) so far today";

        //Create new label, configure, pass the string and display
        TextView resultLabel = new TextView(this);
        resultLabel.setTextSize(25);
        resultLabel.setText(label);
        setContentView(resultLabel);

    }

    //Create a new session with count set to zero
    public void buttonNew(View view) {
    }

    //Clear all sessions and count values
    public void buttonReset(View view) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.view_results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }
}

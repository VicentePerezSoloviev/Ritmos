package soloviev.perez.vicente.ritmos;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class crono extends AppCompatActivity {

    ArrayList<String> list = new ArrayList<>();
    ArrayAdapter<String> adapter;
    Boolean isStarted = false;
    Date startDate = null;
    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS", Locale.ENGLISH);
    TextView timeLabel;
    Timer timer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crono);

        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        setContentView(R.layout.crono);

        timeLabel = findViewById(R.id.textView);

        adapter = new RemovableArrayAdapter(this, list);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        updateTimerUI();

        //actualizo nombre de actionBar para idioma

        setTitle(R.string.cronometro);
    }

    private void resetDate() {
        startDate = new Date();
    }

    private void updateTimerUI() {
        FloatingActionButton startStopButton = findViewById(R.id.startStop);
        FloatingActionButton pickTimeButton = findViewById(R.id.add);

        if (isStarted) {
            startStopButton.setImageResource(R.drawable.ic_stop_white_24dp);
        } else {
            startStopButton.setImageResource(R.drawable.ic_play_arrow_white_24dp);
        }
        pickTimeButton.setEnabled(isStarted);
    }

    private void startTimer() {
        timer = new Timer();
        final crono self = this;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (startDate == null || dateFormat == null) {
                    return;
                }
                final long currentTimestamp = new Date().getTime();
                final long startTimestamp = startDate.getTime();

                self.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        self.updateTimerLabel(
                                dateFormat.format(
                                        new Date(currentTimestamp - startTimestamp)
                                )
                        );
                    }
                });
            }
        }, 0, 50L);
    }

    public void updateTimerLabel(String text) {
        timeLabel.setText(text);
    }

    private void stopTimer() {
        timer.cancel();
        timer = null;
    }

    public void toggleStart(View view) {
        isStarted = !isStarted;
        if (isStarted) {
            clear(null);
            resetDate();
            startTimer();
        } else {
            startDate = null;
            stopTimer();
        }
        updateTimerUI();
    }

    public void onFABOnClick(View view) {
        long currentTimestamp = new Date().getTime();
        long startTimeStamp = startDate.getTime();
        String dateStr = dateFormat.format(new Date(currentTimestamp - startTimeStamp));

        adapter.add(dateStr);
        Snackbar
                .make(view, "Added time: " + dateStr, Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int lastIndex, size = list.size();

                        if (size == 0) {
                            return;
                        }

                        lastIndex = size - 1;

                        list.remove(lastIndex);
                        adapter.notifyDataSetChanged();
                    }
                })
                .show();
    }

    public void clear(View item) {
        adapter.clear();
        timeLabel.setText("00:00:00.000");
    }

}




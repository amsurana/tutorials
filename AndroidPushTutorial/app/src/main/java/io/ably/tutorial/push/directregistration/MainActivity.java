package io.ably.tutorial.push.directregistration;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    
    public static final int SUCCESS = 0;
    public static final int FAILURE = 1;
    public static final int UPDATE_LOGS = 2;
    
    
    private TextView rollingLogs;
    private Button stepsButton;
    private StringBuilder logs = new StringBuilder();
    
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    stepsButton.setText((String) msg.obj);
                    stepsButton.setEnabled(true);
                    break;
                case FAILURE:
                    stepsButton.setEnabled(true);
                    break;
                case UPDATE_LOGS:
                    rollingLogs.setText(logs.toString());
                    break;
            }
            return false;
        }
    });
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rollingLogs = findViewById(R.id.rolling_logs);
        stepsButton = findViewById(R.id.steps);
    }
    
    /**
     * This method is called when button is clicked.
     *
     * @param view
     */
    public void performAction(View view) {
    
    }
    
    private void logMessage(String message) {
        Log.i(MainActivity.class.getSimpleName(), message);
        logs.append(message);
        logs.append("\n");
        handler.sendMessage(handler.obtainMessage(UPDATE_LOGS));
    }
}

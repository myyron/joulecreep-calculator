package org.cogz.altrax.joulecreepcalculator;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private final DecimalFormat df = new DecimalFormat("0.000");
    private final DecimalFormat df2 = new DecimalFormat("0.00");

    private EditText standardBbEditText;
    private EditText standardFpsEditText;
    private EditText standardJouleEditText;
    private EditText playerBbEditText;
    private EditText playerFpsEditText;
    private EditText playerJouleEditText;
    private EditText creepDifferenceEditText;
    private EditText creepPercentageEditText;
    private EditText creepFpsEditText;

    private TextView fpsLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        addListener(standardBbEditText, standardFpsEditText, standardJouleEditText);
        addListener2(standardFpsEditText, standardBbEditText, standardJouleEditText);
        addListener(playerBbEditText, playerFpsEditText, playerJouleEditText);
        addListener2(playerFpsEditText, playerBbEditText, playerJouleEditText);
        addListener3(standardJouleEditText, playerJouleEditText);
        addListener4(playerJouleEditText, standardJouleEditText);
    }

    private void addListener(final EditText editText, final EditText editText2, final EditText editText3) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (editText.getText().toString().isEmpty() || editText.getText().toString().equals(".")) {
                    editText3.setText("");
                    return;
                }
                if (!editText2.getText().toString().isEmpty()) {
                    editText3.setText(df.format(computeJoule(editText, editText2)));
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }

    private void addListener2(final EditText editText, final EditText editText2, final EditText editText3) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (editText.getText().toString().isEmpty() || editText.getText().toString().equals(".")) {
                    editText3.setText("");
                    return;
                }
                if (!editText2.getText().toString().isEmpty()) {
                    editText3.setText(df.format(computeJoule(editText2, editText)));
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }

    private void addListener3(final EditText editText, final EditText editText2) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (editText.getText().toString().isEmpty()) {
                    creepDifferenceEditText.setText("");
                    creepPercentageEditText.setText("");
                    creepFpsEditText.setText("");
                    return;
                }
                if (!editText2.getText().toString().isEmpty()) {
                    double joule1 = Double.parseDouble(editText.getText().toString());
                    double joule2 = Double.parseDouble(editText2.getText().toString());
                    populateCreep(joule1, joule2);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }

    private void addListener4(final EditText editText, final EditText editText2) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (editText.getText().toString().isEmpty()) {
                    creepDifferenceEditText.setText("");
                    creepPercentageEditText.setText("");
                    creepFpsEditText.setText("");
                    return;
                }
                if (!editText2.getText().toString().isEmpty()) {
                    double joule1 = Double.parseDouble(editText2.getText().toString());
                    double joule2 = Double.parseDouble(editText.getText().toString());
                    populateCreep(joule1, joule2);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }

    private void populateCreep(double joule1, double joule2) {
        double standardFps = Double.parseDouble(standardFpsEditText.getText().toString());
        double difference = joule2 - joule1;
        double percentage = difference / joule1;
        double fps = (standardFps * percentage) + standardFps;
        creepDifferenceEditText.setText(df2.format(difference));
        creepPercentageEditText.setText(df2.format(percentage * 100));
        creepFpsEditText.setText(df2.format(fps));
        fpsLabel.setText("Player new FPS in " + standardBbEditText.getText() + " BB");
    }

    private double computeJoule(EditText bbEditText, EditText fpsEditText) {
        double bb = Double.parseDouble(bbEditText.getText().toString());
        double fps = Double.parseDouble(fpsEditText.getText().toString());
        double mass = bb / 1000;
        double velocity = Math.pow((fps * .3048), 2);
        return .5 * mass * velocity;
    }

    private void init() {
        standardBbEditText = findViewById(R.id.standardBbEditText);
        standardFpsEditText = findViewById(R.id.standardFpsEditText);
        standardJouleEditText = findViewById(R.id.standardJouleEditText);
        playerBbEditText = findViewById(R.id.playerBbEditText);
        playerFpsEditText = findViewById(R.id.playerFpsEditText);
        playerJouleEditText = findViewById(R.id.playerJouleEditText);
        creepDifferenceEditText = findViewById(R.id.creepDifferenceEditText);
        creepPercentageEditText = findViewById(R.id.creepPercentageEditText);
        creepFpsEditText = findViewById(R.id.creepFpsEditText);

        fpsLabel = findViewById(R.id.fpsLabel);

        standardBbEditText.setText("");
        standardFpsEditText.setText("");
        standardJouleEditText.setText("");
        playerBbEditText.setText("");
        playerFpsEditText.setText("");
        playerJouleEditText.setText("");
        creepDifferenceEditText.setText("");
        creepPercentageEditText.setText("");
        creepFpsEditText.setText("");
    }
}

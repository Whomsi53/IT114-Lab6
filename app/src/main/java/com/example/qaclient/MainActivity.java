
package com.example.qaclient;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    // Recursive method to calculate the principal after y years
    public double principal(int y, double p_orig, double i_rate) {

        if (y == 0) {
            return p_orig;
        }

        return principal(y - 1, p_orig, i_rate) * (1.0 + i_rate);
    }

    // Calculate and display the principal and accrued interest
    public void calculatePrincipal(View view) {

        EditText etPrincipal = findViewById(R.id.edit_principal);
        EditText etYears = findViewById(R.id.edit_years);
        EditText etRate = findViewById(R.id.edit_rate);
        TextView tvResult = findViewById(R.id.text_result);

        try {
            double original = Double.parseDouble(
                    etPrincipal.getText().toString());

            int years = Integer.parseInt(
                    etYears.getText().toString());

            double rate = Double.parseDouble(
                    etRate.getText().toString()) / 100.0;

            if (original < 0 || years < 0 || rate < 0) {
                tvResult.setText("Please enter non-negative values.");
                return;
            }

            double finalPrincipal = principal(years, original, rate);
            double interest = finalPrincipal - original;

            tvResult.setText(String.format(Locale.US,
                    "Principal after %d years is $%.2f\nAccrued interest: $%.2f",
                    years, finalPrincipal, interest));

        } catch (NumberFormatException e) {
            tvResult.setText("Please enter valid values in all fields.");
        }
    }
}

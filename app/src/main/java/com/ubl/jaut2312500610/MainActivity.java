package com.ubl.jaut2312500610;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etWeight, etHeight, etAge;
    private RadioGroup rgGender;
    private Button btnCalculate;
    private LinearLayout layoutResult;
    private TextView tvBmiValue, tvBmiCategory, tvUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi komponen UI
        etWeight = findViewById(R.id.et_weight);
        etHeight = findViewById(R.id.et_height);
        etAge = findViewById(R.id.et_age);
        rgGender = findViewById(R.id.rg_gender);
        btnCalculate = findViewById(R.id.btn_calculate);
        layoutResult = findViewById(R.id.layout_result);
        tvBmiValue = findViewById(R.id.tv_bmi_value);
        tvBmiCategory = findViewById(R.id.tv_bmi_category);
        tvUserInfo = findViewById(R.id.tv_user_info);

        // Menambahkan aksi klik pada tombol hitung BMI
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ambil input berat badan, tinggi badan, umur dan gender
                String weightStr = etWeight.getText().toString();
                String heightStr = etHeight.getText().toString();
                String ageStr = etAge.getText().toString();

                // Validasi input
                if (weightStr.isEmpty() || heightStr.isEmpty() || ageStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all details", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Mengubah string input menjadi double dan integer
                double weight = Double.parseDouble(weightStr);
                double height = Double.parseDouble(heightStr); // Height in cm
                int age = Integer.parseInt(ageStr);

                // Validasi input tinggi badan, berat badan dan umur
                if (height <= 0 || weight <= 0 || age <= 0) {
                    Toast.makeText(MainActivity.this, "Please enter valid positive values", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Menghitung BMI (mengkonversi tinggi dari cm ke m)
                double bmi = weight / Math.pow(height / 100, 2);

                // Menentukan kategori BMI
                String category = getBmiCategory(bmi);

                // Mendapatkan pilihan gender
                int selectedGenderId = rgGender.getCheckedRadioButtonId();
                RadioButton selectedGender = findViewById(selectedGenderId);
                String gender = (selectedGender != null) ? selectedGender.getText().toString() : "Not Specified";

                // Menampilkan hasil BMI dan kategori
                tvBmiValue.setText(String.format("BMI: %.2f", bmi));
                tvBmiCategory.setText("Category: " + category);
                tvUserInfo.setText("Age: " + age + " years\nGender: " + gender);

                // Menampilkan layout hasil BMI
                layoutResult.setVisibility(View.VISIBLE);
            }
        });
    }

    // Fungsi untuk menentukan kategori BMI
    private String getBmiCategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Normal weight";
        } else if (bmi >= 25 && bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obesity";
        }
    }
}

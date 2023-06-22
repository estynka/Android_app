package com.example.bmikontrolor;

import static java.lang.Math.pow;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText_Weight;
    EditText editText_Height;
    TextView textView_Result;
    Button button_calculate;
    TextView textView_bmiCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_Weight = findViewById(R.id.editText_Weight);
        editText_Height = findViewById(R.id.editText_Height);
        textView_Result = findViewById(R.id.textView_Result);
        button_calculate = findViewById(R.id.button_calculate);
        textView_bmiCategories=findViewById(R.id.textView_bmiCategories);
    }
    private void showErrorToast(){
        Toast.makeText(getApplicationContext(),getString(R.string.input_error), Toast.LENGTH_LONG).show();
        editText_Height.setText("");
        editText_Weight.setText("");
        textView_Result.setText("");
        textView_bmiCategories.setText("");
    }
    private int indexBmi(double result){
        int index = 0;

        if(result < 18.5){ return index;
        } else if (result < 25) { index = 1;
        } else if (result < 30) { index = 2;
        } else if (result < 35) { index = 3;
        } else if (result < 40) { index = 4;
        } else if (result < 45) { index = 5;
        } else if (result >=45) { index = 6;
        }
        return index;
    }
    public void calculateBmi(View view){

        double weight;
        double height;
        double result;

        try{
            weight = Double.parseDouble(editText_Weight.getText().toString());
            height = Double.parseDouble(editText_Height.getText().toString());
            String[] aBmiCatgories = getResources().getStringArray(R.array.array_bmiCategories);

            if (weight > 500 || height > 250){
                showErrorToast();
                return;
            }
            result = weight/pow(height/100, 2);

            if (Double.isNaN(result) || Double.isInfinite(result)){
                showErrorToast();
                return;
            }
            textView_Result.setText(getString(R.string.output_result, result));
            textView_bmiCategories.setText(aBmiCatgories[indexBmi(result)]);

        }catch (NumberFormatException e){
            showErrorToast();
        }
    }
}

package com.example.selfcheckout;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SupermarketActivity extends AppCompatActivity {
    private static String TAG = "SupermarketActivity";
    Button startShoppingSessionButton;
    private static final int pic_id = 123;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation_page_for_supermarket);
        startShoppingSessionButton =  (Button) findViewById(R.id.startShoppingSessionButton);
        startShoppingSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(SupermarketActivity.this, ScanProductsActivity.class);
                startActivity(myIntent);

            }
        });


    }


}




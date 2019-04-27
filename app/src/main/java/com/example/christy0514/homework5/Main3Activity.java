package com.example.christy0514.homework5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main3Activity extends AppCompatActivity {

    private Button button;
    private EditText edName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        button = (Button) findViewById(R.id.button6); //search
        edName = (EditText) findViewById(R.id.editText4);
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                String Name = edName.getText().toString();
                Intent intent = new Intent(Main3Activity.this,Main4Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name",Name);
                intent.putExtras(bundle);
                Log.i("Test",Name);
                startActivity(intent);
            }
        });
    }
}

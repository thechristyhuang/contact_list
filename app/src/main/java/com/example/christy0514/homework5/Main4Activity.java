package com.example.christy0514.homework5;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main4Activity extends AppCompatActivity {
    private final static String CALL = "android.intent.action.CALL";
    private Button button;
    private Button button1;
    private Button button2;
    private Button button3;
    private EditText edName;
    private EditText edPhone;
    private EditText edText;
    private MyDBHelper helper;
    private long id;
    private String name1;
    String name;
    String phone;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        button = (Button) findViewById(R.id.button1); //完成
        button1 = (Button) findViewById(R.id.button); //取消
        button2 = (Button) findViewById(R.id.button2); //刪除
        button3 = (Button) findViewById(R.id.button3); //call
        edName = (EditText) findViewById(R.id.editText);
        edPhone = (EditText) findViewById(R.id.editText2);
        edText = (EditText) findViewById(R.id.editText3);
        helper = new MyDBHelper(this);
        name1 = getIntent().getExtras().getString("name");
        Contact contact1 = helper.findByName(name1);
       edName.setText(contact1.getName());
        edPhone.setText(contact1.getPhone());
        edText.setText(contact1.getText());
        button.setOnClickListener(new Button.OnClickListener() {                //完成
            @Override
            public void onClick(View v) {
                name = edName.getText().toString();
                phone = edPhone.getText().toString();
                text = edText.getText().toString();
                //Log.d("ADD",id+"");
                Contact contact = new Contact(id,name,phone,text);
                long id1 = helper.update(contact);

                Intent intent = new Intent(Main4Activity.this,MainActivity.class);
                startActivity(intent);

                Log.d("ADD",id1+"");
            }
        });
        button1.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {                                       //取消
                Intent intent = new Intent(Main4Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {                                       //刪除
                id = getIntent().getExtras().getLong("_id");
                long count = helper.deleteById(id);
                Intent intent = new Intent(Main4Activity.this,MainActivity.class);
                startActivity(intent);
                Log.d("ADD",count+"");
            }
        });
        button3.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {                   //call
                phone = edPhone.getText().toString();
                Log.i("PHONE",phone);
                Intent call = new Intent(CALL, Uri.parse("tel:" + phone ));
                startActivity(call);
            }
        });
    }
}

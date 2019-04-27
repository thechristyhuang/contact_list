package com.example.christy0514.homework5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity  {

    private FragmentManager manager;
    private FragmentTransaction transaction;

    //private static final int SHOW_EDIT = 0;
    private Object[] listViewData = {"新增聯絡人",Fragment2.class,
            "瀏覽",BlankFragment.class,
            "查詢",Main3Activity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        CharSequence[] list1 = new CharSequence[listViewData.length/2];
        for(int i = 0;i < list1.length;i++){
            list1[i] = (String)listViewData[i*2];
        }

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list1);
        ListView list = (ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
//透過下方程式碼，取得Activity中執行的個體。
        manager = getFragmentManager();


        list.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(
                    AdapterView<?> parent, View view, int position, long id) {
                transaction = manager.beginTransaction();
                switch(position){
                    case 0:
                        //startActivity(new Intent(MainActivity.this,MainActivity.class));
                        Fragment2 fragment1 = new Fragment2();
                        transaction.replace(R.id.center, fragment1, "fragment1");
                        transaction.addToBackStack("fragment1");
                        break;
                    case 1:
                        BlankFragment fragment2 = new BlankFragment();
                        transaction.replace(R.id.center, fragment2, "fragment2");
                        transaction.addToBackStack("fragment2");
                        break;
                    case 2:
                        Intent intent = new Intent(MainActivity.this,Main3Activity.class);    //查詢
                        startActivity(intent);
                        break;

                }
                transaction.commit();
            }
        });
    }

}

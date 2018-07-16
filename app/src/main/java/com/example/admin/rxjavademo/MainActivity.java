package com.example.admin.rxjavademo;

import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.admin.rxjavademo.Activity.CompletableExampleActivity;
import com.example.admin.rxjavademo.Activity.CustomTypeActivity;
import com.example.admin.rxjavademo.Activity.FilterExampleActivity;
import com.example.admin.rxjavademo.Activity.JustExampleActivity;
import com.example.admin.rxjavademo.Activity.MultipleObserverActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnJustExample,btnDisposable,btnFilterDemo,btnMultipleObserver,btnCustomDataType,btnCompletableExample,btnSingleAndSingleObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getIds();
        setListnter();

    }

    private void setListnter() {
        try {
            btnJustExample.setOnClickListener(onClickListener);
            btnDisposable.setOnClickListener(onClickListener);
            btnFilterDemo.setOnClickListener(onClickListener);
            btnCustomDataType.setOnClickListener(onClickListener);
            btnMultipleObserver.setOnClickListener(onClickListener);
            btnCompletableExample.setOnClickListener(onClickListener);
            btnSingleAndSingleObserver.setOnClickListener(onClickListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                switch (view.getId()){
                    case R.id.btnJustExample:
                        Intent intent = new Intent(MainActivity.this, JustExampleActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.btnDisposable:
                        Intent intentDisposable = new Intent(MainActivity.this, JustExampleActivity.class);
                        startActivity(intentDisposable);
                        break;
                    case R.id.btnFilterDemo:
                        Intent intentFilter = new Intent(MainActivity.this, FilterExampleActivity.class);
                        startActivity(intentFilter);
                        break;
                    case R.id.btnMultipleObserver:
                        Intent intentMultipleObserver = new Intent(MainActivity.this, MultipleObserverActivity.class);
                        startActivity(intentMultipleObserver);
                        break;
                    case R.id.btnCustomDataType:
                        Intent intentCustomType = new Intent(MainActivity.this, CustomTypeActivity.class);
                        startActivity(intentCustomType);
                        break;
                    case R.id.btnCompletableExample:
                        Intent intentCompletable = new Intent(MainActivity.this, CompletableExampleActivity.class);
                        startActivity(intentCompletable);
                        break;
                    case R.id.btnSingleAndSingleObserver:
                        Intent intentSingleObserver = new Intent(MainActivity.this, CompletableExampleActivity.class);
                        startActivity(intentSingleObserver);
                        break;



                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };


    private void getIds() {
        try {
            btnJustExample = findViewById(R.id.btnJustExample);
            btnDisposable = findViewById(R.id.btnDisposable);
            btnFilterDemo = findViewById(R.id.btnFilterDemo);
            btnMultipleObserver = findViewById(R.id.btnMultipleObserver);
            btnCustomDataType = findViewById(R.id.btnCustomDataType);
            btnCompletableExample = findViewById(R.id.btnCompletableExample);
            btnSingleAndSingleObserver = findViewById(R.id.btnSingleAndSingleObserver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

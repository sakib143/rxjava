package com.example.admin.rxjavademo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.admin.rxjavademo.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class JustExampleActivity extends AppCompatActivity {

    private static final String TAG = "==>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just_example);

        // observable
        Observable<List<String>> animalsObservable = getAnimalsObservable();

        // observer
        Observer<List<String>> animalsObserver = getAnimalsObserver();

        // observer subscribing to observable
        animalsObservable
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(animalsObserver);
    }


    private Observer<List<String>> getAnimalsObserver() {
        return new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(List<String> s) {
                Log.d(TAG, "Name: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "All items are emitted!");
            }
        };
    }

    private Observable<List<String>> getAnimalsObservable() {
        List<String> myList = new ArrayList<String>();
        for (int i = 0; i <50 ; i++) {
            myList.add("Student "+i);
        }
        return Observable.just (myList);
    }

}

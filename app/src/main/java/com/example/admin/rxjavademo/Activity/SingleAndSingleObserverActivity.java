package com.example.admin.rxjavademo.Activity;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.rxjavademo.Model.NotesModel;
import com.example.admin.rxjavademo.R;

import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class SingleAndSingleObserverActivity extends AppCompatActivity {

    private String TAG = " ==> ";
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_and_single_observer);

        Single<NotesModel> noteObservable = getNoteObservable();

        noteObservable
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<NotesModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(NotesModel note) {
                        Log.e(TAG, "onSuccess: " + note.getNote());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }
                });

        noteObservable
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<NotesModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(NotesModel note) {
                        Log.e(TAG, "onSuccess: " + note.getNote());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }
                });


    }

//    }

    private Single<NotesModel> getNoteObservable() {
        return Single.create(new SingleOnSubscribe<NotesModel>() {
            @Override
            public void subscribe(SingleEmitter<NotesModel> emitter) {
                try {
                    NotesModel note = new NotesModel(1, "Buy milk!");
                    emitter.onSuccess(note);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


}

package com.example.admin.rxjavademo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.admin.rxjavademo.Model.NotesModel;
import com.example.admin.rxjavademo.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class CustomTypeActivity extends AppCompatActivity {

    private CompositeDisposable disposable = new CompositeDisposable();
    private String TAG = " ==>";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_type);

        disposable.add(getNotesObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<NotesModel, NotesModel>() {
                    @Override
                    public NotesModel apply(NotesModel note){
                        // Making the note to all uppercase
                        note.setNote(note.getNote().toUpperCase());
                        return note;
                    }
                })
                .subscribeWith(getNotesObserver()));
    }

    private Observable<NotesModel> getNotesObservable() {
        final List<NotesModel> notes = prepareNotes();

        return Observable.create(new ObservableOnSubscribe<NotesModel>() {
            @Override
            public void subscribe(ObservableEmitter<NotesModel> emitter) throws Exception {
                for (NotesModel note : notes) {
                    if (!emitter.isDisposed()) {
                        emitter.onNext(note);
                    }
                }

                if (!emitter.isDisposed()) {
                    emitter.onComplete();
                }
            }
        });
    }


    private List<NotesModel> prepareNotes() {
        List<NotesModel> notes = new ArrayList<>();
        notes.add(new NotesModel(1, "buy tooth paste!"));
        notes.add(new NotesModel(2, "call brother!"));
        notes.add(new NotesModel(3, "watch narcos tonight!"));
        notes.add(new NotesModel(4, "pay power bill!"));
        return notes;
    }


    private DisposableObserver<NotesModel> getNotesObserver() {
        return new DisposableObserver<NotesModel>() {

            @Override
            public void onNext(NotesModel note) {
                Log.e(TAG, "Note: " + note.getNote());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "All notes are emitted!");
            }
        };
    }

}

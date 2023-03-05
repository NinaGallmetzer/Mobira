package swe.mobira.entities.birdrecords;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import swe.mobira.entities.ringingrecord.RingingRecordViewModel;

public class BirdRecordViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private int mParam;

    public BirdRecordViewModelFactory(Application application, int param) {
        mApplication = application;
        mParam = param;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new BirdRecordViewModel(mApplication, mParam);
    }
}
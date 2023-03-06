package swe.mobira.entities.birdrecord;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class BirdRecordViewModelFactory implements ViewModelProvider.Factory {
    private final Application mApplication;
    private final int mParam;

    public BirdRecordViewModelFactory(Application application, int param) {
        mApplication = application;
        mParam = param;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new BirdRecordViewModel(mApplication, mParam);
    }
}
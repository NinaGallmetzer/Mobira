package swe.mobira.entities.ringingrecord;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


public class RingingRecordViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private int mParam;


    public RingingRecordViewModelFactory(Application application, int param) {
        mApplication = application;
        mParam = param;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new RingingRecordViewModel(mApplication, mParam);
    }
}
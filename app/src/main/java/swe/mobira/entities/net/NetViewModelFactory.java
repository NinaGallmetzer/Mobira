package swe.mobira.entities.net;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class NetViewModelFactory implements ViewModelProvider.Factory {
    private final Application mApplication;
    private final int mParam;

    public NetViewModelFactory(Application application, int param) {
        mApplication = application;
        mParam = param;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new NetViewModel(mApplication, mParam);
    }
}
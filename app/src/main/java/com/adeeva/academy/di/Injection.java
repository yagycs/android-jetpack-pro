package com.adeeva.academy.di;

import android.content.Context;

import com.adeeva.academy.data.source.AcademyRepository;
import com.adeeva.academy.data.source.remote.RemoteDataSource;
import com.adeeva.academy.utils.JsonHelper;

public class Injection {
    public static AcademyRepository provideRepository(Context context) {

        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance(new JsonHelper(context));

        return AcademyRepository.getInstance(remoteDataSource);
    }
}
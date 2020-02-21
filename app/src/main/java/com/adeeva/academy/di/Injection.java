package com.adeeva.academy.di;

import android.content.Context;

import com.adeeva.academy.data.AcademyRepository;
import com.adeeva.academy.data.source.local.LocalDataSource;
import com.adeeva.academy.data.source.local.room.AcademyDatabase;
import com.adeeva.academy.data.source.remote.RemoteDataSource;
import com.adeeva.academy.utils.AppExecutors;
import com.adeeva.academy.utils.JsonHelper;

public class Injection {
    public static AcademyRepository provideRepository(Context context) {

        AcademyDatabase database = AcademyDatabase.getInstance(context);

        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance(new JsonHelper(context));
        LocalDataSource localDataSource = LocalDataSource.getInstance(database.academyDao());
        AppExecutors appExecutors = new AppExecutors();

        return AcademyRepository.getInstance(remoteDataSource, localDataSource, appExecutors);
    }
}
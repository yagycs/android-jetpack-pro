package com.adeeva.academy.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.adeeva.academy.data.AcademyRepository;
import com.adeeva.academy.di.Injection;
import com.adeeva.academy.ui.academy.AcademyViewModel;
import com.adeeva.academy.ui.bookmark.BookmarkViewModel;
import com.adeeva.academy.ui.detail.DetailCourseViewModel;
import com.adeeva.academy.ui.reader.CourseReaderViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;

    private final AcademyRepository mAcademyRepository;

    private ViewModelFactory(AcademyRepository academyRepository) {
        mAcademyRepository = academyRepository;
    }

    public static ViewModelFactory getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.provideRepository(context));
                }
            }
        }
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(AcademyViewModel.class)) {
            return (T) new AcademyViewModel(mAcademyRepository);
        } else if (modelClass.isAssignableFrom(DetailCourseViewModel.class)) {
            return (T) new DetailCourseViewModel(mAcademyRepository);
        } else if (modelClass.isAssignableFrom(BookmarkViewModel.class)) {
            return (T) new BookmarkViewModel(mAcademyRepository);
        } else if (modelClass.isAssignableFrom(CourseReaderViewModel.class)) {
            return (T) new CourseReaderViewModel(mAcademyRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}

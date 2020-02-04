package com.adeeva.academy.ui.bookmark;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.adeeva.academy.data.source.local.entity.CourseEntity;
import com.adeeva.academy.data.AcademyRepository;

import java.util.List;

public class BookmarkViewModel extends ViewModel {
    private AcademyRepository academyRepository;

    public BookmarkViewModel(AcademyRepository mAcademyRepository) {
        this.academyRepository = mAcademyRepository;
    }

    LiveData<List<CourseEntity>> getBookmarks() {
        return academyRepository.getBookmarkedCourses();
    }
}
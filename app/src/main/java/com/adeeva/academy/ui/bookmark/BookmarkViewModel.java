package com.adeeva.academy.ui.bookmark;

import androidx.lifecycle.ViewModel;

import com.adeeva.academy.data.CourseEntity;
import com.adeeva.academy.data.source.AcademyRepository;
import com.adeeva.academy.utils.DataDummy;

import java.util.List;

public class BookmarkViewModel extends ViewModel {
    private AcademyRepository academyRepository;

    public BookmarkViewModel(AcademyRepository mAcademyRepository) {
        this.academyRepository = mAcademyRepository;
    }

    List<CourseEntity> getBookmarks() {
        return academyRepository.getBookmarkedCourses();
    }
}
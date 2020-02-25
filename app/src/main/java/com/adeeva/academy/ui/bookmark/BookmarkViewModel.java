package com.adeeva.academy.ui.bookmark;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.adeeva.academy.data.source.local.entity.CourseEntity;
import com.adeeva.academy.data.AcademyRepository;

import java.util.List;

public class BookmarkViewModel extends ViewModel {
    private AcademyRepository academyRepository;

    public BookmarkViewModel(AcademyRepository mAcademyRepository) {
        this.academyRepository = mAcademyRepository;
    }

    public LiveData<PagedList<CourseEntity>> getBookmarks() {
        return academyRepository.getBookmarkedCourses();
    }

    void setBookmark(CourseEntity courseEntity){
        final boolean newState = !courseEntity.isBookmarked();
        academyRepository.setCourseBookmark(courseEntity, newState);
    }
}
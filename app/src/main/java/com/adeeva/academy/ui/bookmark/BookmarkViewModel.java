package com.adeeva.academy.ui.bookmark;

import androidx.lifecycle.ViewModel;

import com.adeeva.academy.data.CourseEntity;
import com.adeeva.academy.utils.DataDummy;

import java.util.List;

public class BookmarkViewModel extends ViewModel {
    List<CourseEntity> getBookmarks() {
        return DataDummy.generateDummyCourses();
    }
}

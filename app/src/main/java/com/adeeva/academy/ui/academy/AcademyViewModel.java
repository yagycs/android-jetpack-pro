package com.adeeva.academy.ui.academy;

import androidx.lifecycle.ViewModel;

import com.adeeva.academy.data.CourseEntity;
import com.adeeva.academy.utils.DataDummy;

import java.util.List;

public class AcademyViewModel extends ViewModel {

    public List<CourseEntity> getCourses() {
        return DataDummy.generateDummyCourses();
    }
}

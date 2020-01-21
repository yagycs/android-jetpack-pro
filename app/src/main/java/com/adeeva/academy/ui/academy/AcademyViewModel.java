package com.adeeva.academy.ui.academy;

import com.adeeva.academy.data.CourseEntity;
import com.adeeva.academy.utils.DataDummy;

import java.util.List;

public class AcademyViewModel {

    public List<CourseEntity> getCourses(){
        return DataDummy.generateDummyCourses();
    }
}

package com.adeeva.academy.ui.detail;

import androidx.lifecycle.ViewModel;

import com.adeeva.academy.data.CourseEntity;
import com.adeeva.academy.data.ModuleEntity;
import com.adeeva.academy.utils.DataDummy;

import java.util.ArrayList;
import java.util.List;

public class DetailCourseViewModel extends ViewModel {
    private String courseId;

    public void setSelectedCourse(String courseId) {
        this.courseId = courseId;
    }

    public CourseEntity getCourse() {
        CourseEntity course = null;
        ArrayList<CourseEntity> courseEntities = DataDummy.generateDummyCourses();
        for (CourseEntity courseEntity : courseEntities) {
            if (courseEntity.getCourseId().equals(courseId)) {
                course = courseEntity;
            }
        }
        return course;
    }

    public List<ModuleEntity> getModules() {
        return DataDummy.generateDummyModules(courseId);
    }
}

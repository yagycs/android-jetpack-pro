package com.adeeva.academy.ui.detail;

import androidx.lifecycle.ViewModel;

import com.adeeva.academy.data.CourseEntity;
import com.adeeva.academy.data.ModuleEntity;
import com.adeeva.academy.utils.DataDummy;

import java.util.List;

public class DetailCourseViewModel extends ViewModel {

    private CourseEntity mCourse;
    private String courseId;

    public CourseEntity getCourse() {
        for (int i = 0; i < DataDummy.generateDummyCourses().size(); i++) {
            CourseEntity courseEntity = DataDummy.generateDummyCourses().get(i);
            if (courseEntity.getCourseId().equals(courseId)) {
                mCourse = courseEntity;
            }
        }
        return mCourse;
    }

    public List<ModuleEntity> getModules(){
        return DataDummy.generateDummyModules(getCourseId());
    }

    public void setCourseId(String courseId){
        this.courseId = courseId;
    }

    public String getCourseId(){
        return courseId;
    }
}

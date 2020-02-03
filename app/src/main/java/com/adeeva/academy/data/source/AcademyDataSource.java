package com.adeeva.academy.data.source;

import com.adeeva.academy.data.CourseEntity;
import com.adeeva.academy.data.ModuleEntity;

import java.util.List;

// Kelas interface untuk menghubungkan repository local dan remote
public interface AcademyDataSource {

    List<CourseEntity> getAllCourses();

    CourseEntity getCourseWithModules(String courseId);

    List<ModuleEntity> getAllModulesByCourse(String courseId);

    List<CourseEntity> getBookmarkedCourses();

    ModuleEntity getContent(String courseId, String moduleId);
}

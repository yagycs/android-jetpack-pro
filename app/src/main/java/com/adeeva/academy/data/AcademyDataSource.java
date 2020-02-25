package com.adeeva.academy.data;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.adeeva.academy.data.source.local.entity.CourseEntity;
import com.adeeva.academy.data.source.local.entity.CourseWithModule;
import com.adeeva.academy.data.source.local.entity.ModuleEntity;
import com.adeeva.academy.vo.Resource;

import java.util.List;

// Kelas interface untuk menghubungkan repository local dan remote
public interface AcademyDataSource {

    LiveData<Resource<PagedList<CourseEntity>>> getAllCourses();

    LiveData<Resource<CourseWithModule>> getCourseWithModules(String courseId);

    LiveData<Resource<List<ModuleEntity>>> getAllModulesByCourse(String courseId);

    LiveData<Resource<ModuleEntity>> getContent(String moduleId);

    LiveData<PagedList<CourseEntity>> getBookmarkedCourses();

    void setCourseBookmark(CourseEntity course, boolean state); //berfungsi untuk menambahkan course ke daftar bookmark

    void setReadModule(ModuleEntity module); //berfungsi untuk memperlihatkan module mana yang terakhir dibaca
}
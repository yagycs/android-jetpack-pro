package com.adeeva.academy.data.source;

import androidx.annotation.NonNull;

import com.adeeva.academy.data.ContentEntity;
import com.adeeva.academy.data.CourseEntity;
import com.adeeva.academy.data.ModuleEntity;
import com.adeeva.academy.data.source.remote.RemoteDataSource;
import com.adeeva.academy.data.source.remote.response.CourseResponse;
import com.adeeva.academy.data.source.remote.response.ModuleResponse;

import java.util.ArrayList;
import java.util.List;

// Kelas untuk menghubungkan kelas RemoteDataSource
public class AcademyRepository implements AcademyDataSource {

    private volatile static AcademyRepository INSTANCE = null;

    private final RemoteDataSource remoteDataSource;

    private AcademyRepository(@NonNull RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public static AcademyRepository getInstance(RemoteDataSource remotedata){
        if (INSTANCE == null){
            synchronized (AcademyRepository.class){
                if (INSTANCE == null){
                    INSTANCE = new AcademyRepository(remotedata);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public ArrayList<CourseEntity> getAllCourses() {
        List<CourseResponse> courseResponses = remoteDataSource.getAllCourses();
        ArrayList<CourseEntity> courseList = new ArrayList<>();
        for (CourseResponse response : courseResponses){
            CourseEntity course = new CourseEntity(
                    response.getId(),
                    response.getTitle(),
                    response.getDescription(),
                    response.getDate(),
                    false,
                    response.getImagePath());

            courseList.add(course);
        }
        return courseList;
    }

    @Override
    public CourseEntity getCourseWithModules(final String courseId) {
        List<CourseResponse> courseResponses = remoteDataSource.getAllCourses();
        CourseEntity course = null;
        for (CourseResponse response : courseResponses){
            if (response.getId().equals(courseId)){
                course = new CourseEntity(
                        response.getId(),
                        response.getTitle(),
                        response.getDescription(),
                        response.getDate(),
                        false,
                        response.getImagePath());
            }
        }
        return course;
    }

    @Override
    public ArrayList<ModuleEntity> getAllModulesByCourse(String courseId) {
        ArrayList<ModuleEntity> moduleList = new ArrayList<>();
        for (ModuleResponse response : moduleResponses){
            ModuleEntity course = new ModuleEntity(
                    response.getModuleId(),
                    response.getCourseId(),
                    response.getTitle(),
                    response.getPosition(),
                    false);

            moduleList.add(course);
        }
        return moduleList;
    }

    @Override
    public ArrayList<CourseEntity> getBookmarkedCourses() {
        List<CourseResponse> courseResponses = remoteDataSource.getAllCourses();
        ArrayList<CourseEntity> courseList = new ArrayList<>();
        for (CourseResponse response : courseResponses){
            CourseEntity course = new CourseEntity(
                    response.getId(),
                    response.getTitle(),
                    response.getDescription(),
                    response.getDate(),
                    false,
                    response.getImagePath());

            courseList.add(course);
        }
        return courseList;
    }

    @Override
    public ModuleEntity getContent(String courseId, String moduleId) {
        List<ModuleResponse> moduleResponses = remoteDataSource.getModules(courseId);
        ModuleEntity module = null;
        List<ModuleResponse> moduleResponses = remoteDataSource.getModules(courseId);
        for (ModuleResponse response : moduleResponses){
            if (response.getModuleId().equals(moduleId)){
                module = new ModuleEntity(
                        response.getModuleId(),
                        response.getCourseId(),
                        response.getTitle(),
                        response.getPosition(),
                        false);
                module.contentEntity = new ContentEntity(remoteDataSource.getContent(moduleId).getContent());
                break;
            }
        }
        return module;
    }
}

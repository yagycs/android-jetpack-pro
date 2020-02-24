package com.adeeva.academy.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.adeeva.academy.data.source.local.LocalDataSource;
import com.adeeva.academy.data.source.local.entity.ContentEntity;
import com.adeeva.academy.data.source.local.entity.CourseEntity;
import com.adeeva.academy.data.source.local.entity.CourseWithModule;
import com.adeeva.academy.data.source.local.entity.ModuleEntity;
import com.adeeva.academy.data.source.remote.ApiResponse;
import com.adeeva.academy.data.source.remote.RemoteDataSource;
import com.adeeva.academy.data.source.remote.response.ContentResponse;
import com.adeeva.academy.data.source.remote.response.CourseResponse;
import com.adeeva.academy.data.source.remote.response.ModuleResponse;
import com.adeeva.academy.utils.AppExecutors;
import com.adeeva.academy.vo.Resource;

import java.util.ArrayList;
import java.util.List;

public class FakeAcademyRepository implements AcademyDataSource {

    private volatile static AcademyRepository INSTANCE = null;

    private final RemoteDataSource remoteDataSource;
    private final LocalDataSource localDataSource;
    private final AppExecutors appExecutors;

   public FakeAcademyRepository(@NonNull RemoteDataSource remoteDataSource, @NonNull LocalDataSource localDataSource, AppExecutors appExecutors) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        this.appExecutors = appExecutors;
    }

    @Override
    public LiveData<Resource<List<CourseEntity>>> getAllCourses() {
        return new NetworkBoundResource<List<CourseEntity>, List<CourseResponse>>(appExecutors) {
            @Override
            public LiveData<List<CourseEntity>> loadFromDB() {
                return localDataSource.getAllCourses(); // untuk membaca getAllCourse dari LocalDataSource kemudian akan diteruskan ke method shouldFetch di bawah ini
            }

            @Override
            public Boolean shouldFetch(List<CourseEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            public LiveData<ApiResponse<List<CourseResponse>>> createCall() {
                return remoteDataSource.getAllCourses(); // karena data dari LocalDataSource null atau empty, maka akan dilakukab pengambilan data dari RemoteDataSource dan selanjutnya akan dilakukan proses inserting pada method bagian di bawah ini
            }

            @Override
            public void saveCallResult(List<CourseResponse> courseResponses) {
                ArrayList<CourseEntity> courseList = new ArrayList<>();
                for (CourseResponse response : courseResponses) {
                    CourseEntity course = new CourseEntity(response.getId(),
                            response.getTitle(),
                            response.getDescription(),
                            response.getDate(),
                            false,
                            response.getImagePath());

                    courseList.add(course);
                }

                localDataSource.insertCourses(courseList);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<List<CourseEntity>> getBookmarkedCourses() {
        return localDataSource.getBookmarkedCourses();
    }

    @Override
    public LiveData<Resource<CourseWithModule>> getCourseWithModules(final String courseId) {
        return new NetworkBoundResource<CourseWithModule, List<ModuleResponse>>(appExecutors) {
            @Override
            protected LiveData<CourseWithModule> loadFromDB() {
                return localDataSource.getCourseWithModules(courseId);
            }

            @Override
            protected Boolean shouldFetch(CourseWithModule courseWithModule) {
                return (courseWithModule == null || courseWithModule.mModules == null) || (courseWithModule.mModules.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<ModuleResponse>>> createCall() {
                return remoteDataSource.getModules(courseId);
            }

            @Override
            protected void saveCallResult(List<ModuleResponse> moduleResponses) {

                ArrayList<ModuleEntity> moduleList = new ArrayList<>();
                for (ModuleResponse response : moduleResponses) {
                    ModuleEntity course = new ModuleEntity(response.getModuleId(),
                            response.getCourseId(),
                            response.getTitle(),
                            response.getPosition(),
                            false);

                    moduleList.add(course);
                }

                localDataSource.insertModules(moduleList);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<List<ModuleEntity>>> getAllModulesByCourse(String courseId) {
        return new NetworkBoundResource<List<ModuleEntity>, List<ModuleResponse>>(appExecutors) {
            @Override
            protected LiveData<List<ModuleEntity>> loadFromDB() {
                return localDataSource.getAllModulesByCourse(courseId);
            }

            @Override
            protected Boolean shouldFetch(List<ModuleEntity> modules) {
                return (modules == null) || (modules.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<ModuleResponse>>> createCall() {
                return remoteDataSource.getModules(courseId);
            }

            @Override
            protected void saveCallResult(List<ModuleResponse> moduleResponses) {

                ArrayList<ModuleEntity> moduleList = new ArrayList<>();
                for (ModuleResponse response : moduleResponses) {
                    ModuleEntity course = new ModuleEntity(response.getModuleId(),
                            response.getCourseId(),
                            response.getTitle(),
                            response.getPosition(),
                            false);

                    moduleList.add(course);
                }

                localDataSource.insertModules(moduleList);

            }
        }.asLiveData();
    }


    @Override
    public LiveData<Resource<ModuleEntity>> getContent(String moduleId) {
        return new NetworkBoundResource<ModuleEntity, ContentResponse>(appExecutors) {
            @Override
            protected LiveData<ModuleEntity> loadFromDB() {
                return localDataSource.getModuleWithContent(moduleId);
            }

            @Override
            protected Boolean shouldFetch(ModuleEntity moduleEntity) {
                return (moduleEntity.contentEntity == null);
            }

            @Override
            protected LiveData<ApiResponse<ContentResponse>> createCall() {
                return remoteDataSource.getContent(moduleId);
            }

            @Override
            protected void saveCallResult(ContentResponse contentResponse) {

                localDataSource.updateContent(contentResponse.getContent(), moduleId);
            }
        }.asLiveData();
    }

    @Override
    public void setCourseBookmark(CourseEntity course, boolean state) {
        appExecutors.diskIO().execute(() -> localDataSource.setCourseBookmark(course, state));
    }

    @Override
    public void setReadModule(ModuleEntity module) {
        appExecutors.diskIO().execute(() -> localDataSource.setReadModule(module));
    }
}

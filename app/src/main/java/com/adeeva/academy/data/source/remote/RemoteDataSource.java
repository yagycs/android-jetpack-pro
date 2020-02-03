package com.adeeva.academy.data.source.remote;

import com.adeeva.academy.data.source.remote.response.ContentResponse;
import com.adeeva.academy.data.source.remote.response.CourseResponse;
import com.adeeva.academy.data.source.remote.response.ModuleResponse;
import com.adeeva.academy.utils.JsonHelper;

import java.util.List;

public class RemoteDataSource {

    private static RemoteDataSource INSTANCE;
    private JsonHelper jsonHelper;

    private RemoteDataSource(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public static RemoteDataSource getInstance(JsonHelper helper){
        if (INSTANCE == null){
            INSTANCE = new RemoteDataSource(helper);
        }
        return INSTANCE;
    }

    public List<CourseResponse> getAllCourses(){
        return jsonHelper.loadCourses();
    }

    public List<ModuleResponse> getModules(String courseId){
        return jsonHelper.loadModule(courseId);
    }

    public ContentResponse getContent(String moduleId){
        return jsonHelper.loadContent(moduleId);
    }
}

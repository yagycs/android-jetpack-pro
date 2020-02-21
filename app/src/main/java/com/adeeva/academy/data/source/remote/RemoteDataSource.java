package com.adeeva.academy.data.source.remote;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.adeeva.academy.data.source.remote.response.ContentResponse;
import com.adeeva.academy.data.source.remote.response.CourseResponse;
import com.adeeva.academy.data.source.remote.response.ModuleResponse;
import com.adeeva.academy.utils.EspressoIdlingResource;
import com.adeeva.academy.utils.JsonHelper;

import java.util.List;

public class RemoteDataSource {

    private static RemoteDataSource INSTANCE;
    private JsonHelper jsonHelper;

    private Handler handler = new Handler(); // handler berfungsi untuk memberikan waktu delay sesuai kebutuhan
    // Catatan:
    // Penggunaan Handler untuk delay, seperti yang dilakukan di atas merupakah hal yang tidak disarankan.
    // Karena proyek yang kita buat hanyalah simulasi, di mana data yang diperoleh disimulasikan berasal dari server atau API.
    // Maka dari itu, penggunaan Handler pada proyek Academy digunakan untuk mensimulasikan proses asynchonous yang terjadi.

    private final long SERVICE_LATENCY_IN_MILLIS = 2000;

    private RemoteDataSource(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public static RemoteDataSource getInstance(JsonHelper helper) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource(helper);
        }
        return INSTANCE;
    }

    public LiveData<ApiResponse<List<CourseResponse>>> getAllCourses() {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<CourseResponse>>> resultCourse = new MutableLiveData<>();
        handler.postDelayed(() -> {
            resultCourse.setValue(ApiResponse.success(jsonHelper.loadCourses()));
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
        return resultCourse;
    }

    public LiveData<ApiResponse<List<ModuleResponse>>> getModules(String courseId) {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<ModuleResponse>>> resultModules = new MutableLiveData<>();
        handler.postDelayed(() -> {
            resultModules.setValue(ApiResponse.success(jsonHelper.loadModule(courseId)));
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
        return resultModules;
    }

    public LiveData<ApiResponse<ContentResponse>> getContent(String moduleId) {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<ContentResponse>> resultContent = new MutableLiveData<>();
        handler.postDelayed(() -> {
            resultContent.setValue(ApiResponse.success(jsonHelper.loadContent(moduleId)));
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
        return resultContent;
    }
}
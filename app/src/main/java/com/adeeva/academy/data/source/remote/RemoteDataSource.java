package com.adeeva.academy.data.source.remote;

import android.os.Handler;

import com.adeeva.academy.data.source.remote.response.ContentResponse;
import com.adeeva.academy.data.source.remote.response.CourseResponse;
import com.adeeva.academy.data.source.remote.response.ModuleResponse;
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

    public void getAllCourses(LoadCoursesCallback callback) {
        handler.postDelayed(()-> callback.onAllCoursesReceived(jsonHelper.loadCourses()), SERVICE_LATENCY_IN_MILLIS);
    }

    public void getModules(String courseId, LoadModulesCallback callback) {
        handler.postDelayed(()-> callback.onAllModulesReceived(jsonHelper.loadModule(courseId)), SERVICE_LATENCY_IN_MILLIS);
    }

    public void getContent(String moduleId, LoadContentCallback callback) {
        handler.postDelayed(()-> callback.onContentReceived(jsonHelper.loadContent(moduleId)), SERVICE_LATENCY_IN_MILLIS);
    }

    public interface LoadCoursesCallback {
        void onAllCoursesReceived(List<CourseResponse> courseResponses);
    }

    public interface LoadModulesCallback {
        void onAllModulesReceived(List<ModuleResponse> moduleResponses);
    }

    public interface LoadContentCallback {
        void onContentReceived(ContentResponse contentResponse);
    }
}

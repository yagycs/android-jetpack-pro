package com.adeeva.academy.ui.reader;

import androidx.lifecycle.ViewModel;

import com.adeeva.academy.data.ContentEntity;
import com.adeeva.academy.data.ModuleEntity;
import com.adeeva.academy.data.source.AcademyRepository;
import com.adeeva.academy.utils.DataDummy;

import java.util.ArrayList;

public class CourseReaderViewModel extends ViewModel {

    private String courseId;
    private String moduleId;
    private AcademyRepository academyRepository;

    public CourseReaderViewModel(AcademyRepository mAcademyRepository) {
        this.academyRepository = mAcademyRepository;
    }

    public void setSelectedCourse(String courseId) {
        this.courseId = courseId;
    }

    public void setSelectedModule(String moduleId) {
        this.moduleId = moduleId;
    }

    public ArrayList<ModuleEntity> getModules() {
        return academyRepository.getAllModulesByCourse(courseId);
    }

    public ModuleEntity getSelectedModule() {
        return academyRepository.getContent(courseId, moduleId);
    }
}

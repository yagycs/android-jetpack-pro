package com.adeeva.academy.ui.academy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.adeeva.academy.data.source.local.entity.CourseEntity;
import com.adeeva.academy.data.AcademyRepository;
import com.adeeva.academy.vo.Resource;

import java.util.List;

public class AcademyViewModel extends ViewModel {

    private AcademyRepository academyRepository;

    public AcademyViewModel(AcademyRepository mAcademyRepository) {
        this.academyRepository = mAcademyRepository;
    }

    public LiveData<Resource<List<CourseEntity>>> getCourses() {
        return academyRepository.getAllCourses();
    }
}
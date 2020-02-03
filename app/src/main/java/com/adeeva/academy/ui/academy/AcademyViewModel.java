package com.adeeva.academy.ui.academy;

import androidx.lifecycle.ViewModel;

import com.adeeva.academy.data.CourseEntity;
import com.adeeva.academy.data.source.AcademyRepository;
import com.adeeva.academy.utils.DataDummy;

import java.util.List;

public class AcademyViewModel extends ViewModel {
    private AcademyRepository academyRepository;

    public AcademyViewModel(AcademyRepository mAcademyRepository) {
        this.academyRepository = mAcademyRepository;
    }

    public List<CourseEntity> getCourses() {
        return academyRepository.getAllCourses();
    }
}
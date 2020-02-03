package com.adeeva.academy.ui.academy;

import com.adeeva.academy.data.source.local.entity.CourseEntity;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AcademyViewModelTest {
    private AcademyViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new AcademyViewModel();
    }

    @Test
    public void getCourses() {
        List<CourseEntity> courseEntities = viewModel.getCourses();
        assertNotNull(courseEntities);
        assertEquals(5, courseEntities.size());
    }
}
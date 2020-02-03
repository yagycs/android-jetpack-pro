package com.adeeva.academy.ui.detail;

import com.adeeva.academy.data.source.local.entity.CourseEntity;
import com.adeeva.academy.data.source.local.entity.ModuleEntity;
import com.adeeva.academy.utils.DataDummy;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DetailCourseViewModelTest {
    private DetailCourseViewModel viewModel;
    private CourseEntity dummyCourse = DataDummy.generateDummyCourses().get(0);
    private String courseId = dummyCourse.getCourseId();

    @Before
    public void setUp() {
        viewModel = new DetailCourseViewModel();
        viewModel.setSelectedCourse(courseId);
    }

    @Test
    public void getCourse() {
        viewModel.setSelectedCourse(dummyCourse.getCourseId());
        CourseEntity courseEntity = viewModel.getCourse();
        assertNotNull(courseEntity);
        assertEquals(dummyCourse.getCourseId(), courseEntity.getCourseId());
        assertEquals(dummyCourse.getDeadline(), courseEntity.getDeadline());
        assertEquals(dummyCourse.getDescription(), courseEntity.getDescription());
        assertEquals(dummyCourse.getImagePath(), courseEntity.getImagePath());
        assertEquals(dummyCourse.getTitle(), courseEntity.getTitle());
    }

    @Test
    public void getModules() {
        List<ModuleEntity> moduleEntities = viewModel.getModules();
        assertNotNull(moduleEntities);
        assertEquals(7, moduleEntities.size());
    }
}
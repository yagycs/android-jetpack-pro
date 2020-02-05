package com.adeeva.academy.ui.bookmark;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.adeeva.academy.data.AcademyRepository;
import com.adeeva.academy.data.source.local.entity.CourseEntity;
import com.adeeva.academy.utils.DataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookmarkViewModelTest {
    private BookmarkViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private AcademyRepository academyRepository;

    @Mock
    private Observer<List<CourseEntity>> observer;

    @Before
    public void setUp() {
        viewModel = new BookmarkViewModel(academyRepository);
    }

    @Test
    public void getBookmark() {
        ArrayList<CourseEntity> dummyCourses = DataDummy.generateDummyCourses();
        MutableLiveData<List<CourseEntity>> courses = new MutableLiveData<>();
        courses.setValue(dummyCourses);

        when(academyRepository.getBookmarkedCourses()).thenReturn(courses);
        List<CourseEntity> courseEntities = viewModel.getBookmarks().getValue();
        verify(academyRepository).getBookmarkedCourses();
        assertNotNull(courseEntities);
        assertEquals(5, courseEntities.size());

        viewModel.getBookmarks().observeForever(observer);
        verify(observer).onChanged(dummyCourses); //pengujian pertama memastikan ada perubahan data di dalam LiveData sesuai yang diharapkan.
    }
}
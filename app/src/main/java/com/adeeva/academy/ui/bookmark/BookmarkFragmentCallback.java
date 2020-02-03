package com.adeeva.academy.ui.bookmark;

import com.adeeva.academy.data.source.local.entity.CourseEntity;

interface BookmarkFragmentCallback {
    void onShareClick(CourseEntity course);
}
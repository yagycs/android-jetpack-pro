package com.adeeva.academy.data.source.remote.response;

// Kelas untuk membaca JSON jadi Array
public class ContentResponse {

    private String moduleId;
    private String content;

    public ContentResponse(String mModuleId, String mContent) {
        this.moduleId = mModuleId;
        this.content = mContent;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

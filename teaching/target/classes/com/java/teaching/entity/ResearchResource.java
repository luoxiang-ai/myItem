package com.java.teaching.entity;

public class ResearchResource {
    private Integer id;

    private String details;

    private String content;

    private String press;

    private String treatise;

    private String patent;

    private String verticalTopic;

    private String horizontalIssues;

    private Integer tid;

    private String path;

    private Integer sid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details == null ? null : details.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press == null ? null : press.trim();
    }

    public String getTreatise() {
        return treatise;
    }

    public void setTreatise(String treatise) {
        this.treatise = treatise == null ? null : treatise.trim();
    }

    public String getPatent() {
        return patent;
    }

    public void setPatent(String patent) {
        this.patent = patent == null ? null : patent.trim();
    }

    public String getVerticalTopic() {
        return verticalTopic;
    }

    public void setVerticalTopic(String verticalTopic) {
        this.verticalTopic = verticalTopic == null ? null : verticalTopic.trim();
    }

    public String getHorizontalIssues() {
        return horizontalIssues;
    }

    public void setHorizontalIssues(String horizontalIssues) {
        this.horizontalIssues = horizontalIssues == null ? null : horizontalIssues.trim();
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }
}
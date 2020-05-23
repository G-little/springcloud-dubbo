package com.little.g.springcloud.admin.model;


import com.little.g.springcloud.admin.common.BaseEntity;

/**
 * 操作行为日志 一个操作行为可能会影响多张数据表.
 */
public class LoggerAction extends BaseEntity {

    private String staffName;

    private String moduleName;

    // @OperationType
    private Integer operationType;

    private String operationDescription;

    // 用来拼接详细的日志信息
    private String comment;

    /**
     * 创建时间
     */
    private Long gmtCreate;

    /**
     * 上次修改时间
     */
    private Long gmtModified;

    private String content;

    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public void setOperationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

}

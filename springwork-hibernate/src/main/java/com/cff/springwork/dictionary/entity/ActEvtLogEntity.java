package com.cff.springwork.dictionary.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "act_evt_log", schema = "cff")
public class ActEvtLogEntity {
    private long logNr;
    private String type;
    private String procDefId;
    private String procInstId;
    private String executionId;
    private String taskId;
    private Timestamp timeStamp;
    private String userId;
    private byte[] data;
    private String lockOwner;
    private Timestamp lockTime;
    private Byte isProcessed;

    @Id
    @Column(name = "LOG_NR_", nullable = false)
    public long getLogNr() {
        return logNr;
    }

    public void setLogNr(long logNr) {
        this.logNr = logNr;
    }

    @Basic
    @Column(name = "TYPE_", nullable = true, length = 64)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "PROC_DEF_ID_", nullable = true, length = 64)
    public String getProcDefId() {
        return procDefId;
    }

    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId;
    }

    @Basic
    @Column(name = "PROC_INST_ID_", nullable = true, length = 64)
    public String getProcInstId() {
        return procInstId;
    }

    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    @Basic
    @Column(name = "EXECUTION_ID_", nullable = true, length = 64)
    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    @Basic
    @Column(name = "TASK_ID_", nullable = true, length = 64)
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Basic
    @Column(name = "TIME_STAMP_", nullable = false)
    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Basic
    @Column(name = "USER_ID_", nullable = true, length = 255)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "DATA_", nullable = true)
    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Basic
    @Column(name = "LOCK_OWNER_", nullable = true, length = 255)
    public String getLockOwner() {
        return lockOwner;
    }

    public void setLockOwner(String lockOwner) {
        this.lockOwner = lockOwner;
    }

    @Basic
    @Column(name = "LOCK_TIME_", nullable = true)
    public Timestamp getLockTime() {
        return lockTime;
    }

    public void setLockTime(Timestamp lockTime) {
        this.lockTime = lockTime;
    }

    @Basic
    @Column(name = "IS_PROCESSED_", nullable = true)
    public Byte getIsProcessed() {
        return isProcessed;
    }

    public void setIsProcessed(Byte isProcessed) {
        this.isProcessed = isProcessed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActEvtLogEntity that = (ActEvtLogEntity) o;
        return logNr == that.logNr &&
                Objects.equals(type, that.type) &&
                Objects.equals(procDefId, that.procDefId) &&
                Objects.equals(procInstId, that.procInstId) &&
                Objects.equals(executionId, that.executionId) &&
                Objects.equals(taskId, that.taskId) &&
                Objects.equals(timeStamp, that.timeStamp) &&
                Objects.equals(userId, that.userId) &&
                Arrays.equals(data, that.data) &&
                Objects.equals(lockOwner, that.lockOwner) &&
                Objects.equals(lockTime, that.lockTime) &&
                Objects.equals(isProcessed, that.isProcessed);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(logNr, type, procDefId, procInstId, executionId, taskId, timeStamp, userId, lockOwner, lockTime, isProcessed);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }
}

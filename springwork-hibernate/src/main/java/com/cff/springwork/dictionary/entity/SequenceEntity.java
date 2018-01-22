package com.cff.springwork.dictionary.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sequence", schema = "cff")
public class SequenceEntity {
    private String sequenceName;
    private Integer value;

    @Id
    @Column(name = "sequence_name", nullable = false, length = 64)
    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    @Basic
    @Column(name = "value", nullable = true)
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SequenceEntity that = (SequenceEntity) o;
        return Objects.equals(sequenceName, that.sequenceName) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sequenceName, value);
    }
}

package ir.maleki.sideprojects.timetally.domain.base;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Entity {
    protected Long id;
    protected LocalDateTime createDate;
    protected LocalDateTime modifyDate;
    protected Integer version;

    protected Entity(Long id, LocalDateTime createDate, LocalDateTime modifyDate) {
        this.id = id;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.version = 1;
    }

    public Long id() {
        return id;
    }

    public LocalDateTime createDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public LocalDateTime modifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer version() {
        return version;
    }

    public void incrementVersion() {
        this.version = version + 1;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null || this.getClass() != that.getClass()) {
            return false;
        }
        return Objects.equals(id, ((Entity) that).id);
    }

}

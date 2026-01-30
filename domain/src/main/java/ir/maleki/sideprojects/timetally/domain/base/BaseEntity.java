package ir.maleki.sideprojects.timetally.domain.base;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;

import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(nullable = false, updatable = false)
    protected LocalDateTime createDate;
    @Column(nullable = false)
    protected LocalDateTime modifyDate;
    @Version
    protected Integer version;

    public BaseEntity() {
    }

    protected BaseEntity(Long id, LocalDateTime createDate, LocalDateTime modifyDate) {
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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null || this.getClass() != that.getClass()) {
            return false;
        }
        return Objects.equals(id, ((BaseEntity) that).id);
    }

}

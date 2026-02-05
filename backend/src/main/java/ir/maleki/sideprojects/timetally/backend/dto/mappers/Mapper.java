package ir.maleki.sideprojects.timetally.backend.dto.mappers;

import ir.maleki.sideprojects.timetally.backend.dto.Dto;
import ir.maleki.sideprojects.timetally.domain.base.BaseEntity;

public interface Mapper<E extends BaseEntity, D extends Dto> {
    D toDto(E entity);
}

package org.wrmList.waitingList.common.mapper;

import org.mapstruct.Mapper;

public interface BaseMapper<E, OT> {
    OT toOT(E e);
    E toE(OT ot);
}

package com.miciu.spring.app.converter;

import com.google.common.collect.BiMap;
import com.google.common.collect.EnumHashBiMap;
import com.miciu.spring.app.model.Profession;
import com.miciu.spring.app.model.Sector;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class ProfessionToSectorConverter extends BidirectionalConverter<Profession, Sector> {

    private BiMap<Profession, Sector> map;

    public ProfessionToSectorConverter() {
        map = EnumHashBiMap.create(Profession.class);
        map.put(Profession.NURSE, Sector.HEALTH);
        map.put(Profession.BANKER, Sector.FINANCE);
        map.put(Profession.BUS_DRIVER, Sector.TRANSPORTATION);
        map.put(Profession.SOLIDER, Sector.MILITARY);
    }


    @Override
    public Sector convertTo(Profession profession, Type<Sector> type, MappingContext mappingContext) {
        return map.get(profession);
    }

    @Override
    public Profession convertFrom(Sector sector, Type<Profession> type, MappingContext mappingContext) {
        return map.inverse().get(sector);
    }
}

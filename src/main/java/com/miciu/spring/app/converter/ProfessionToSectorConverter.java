package com.miciu.spring.app.converter;

import com.miciu.spring.app.model.Profession;
import com.miciu.spring.app.model.Sector;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class ProfessionToSectorConverter extends BidirectionalConverter<Profession, Sector> {


    @Override
    public Sector convertTo(Profession profession, Type<Sector> type, MappingContext mappingContext) {
        switch (profession) {
            case NURSE:
                return Sector.HEALTH;
            case BANKER:
                return Sector.FINANCE;
            case BUS_DRIVER:
                return Sector.TRANSPORTATION;
            case SOLIDER:
                return Sector.MILITARY;
            default:
                return Sector.FINANCE;
        }
    }

    @Override
    public Profession convertFrom(Sector sector, Type<Profession> type, MappingContext mappingContext) {
        switch (sector) {
            case HEALTH:
                return Profession.NURSE;
            case FINANCE:
                return Profession.BANKER;
            case TRANSPORTATION:
                return Profession.BUS_DRIVER;
            case MILITARY:
                return Profession.SOLIDER;
            default:
                return Profession.BANKER;

        }
    }
}

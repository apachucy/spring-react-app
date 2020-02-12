package com.miciu.spring.app.mapper;

import com.miciu.spring.app.converter.ProfessionToSectorConverter;
import com.miciu.spring.app.model.Profession;
import com.miciu.spring.app.model.Sector;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfessionToSectorConverterTest {
    private ProfessionToSectorConverter testObject;

    @Before
    public void before() {
        testObject = new ProfessionToSectorConverter();
    }

    @Test
    public void should_map_health_to_nurse() {
        //given
        Sector sector = Sector.HEALTH;
        Profession profession = null;
        //when
        profession = testObject.convertFrom(sector, null, null);
        //then
        assertThat(profession).isEqualTo(Profession.NURSE);
    }

    @Test
    public void should_map_finance_to_banker() {
        //given
        Sector sector = Sector.FINANCE;
        Profession profession = null;
        //when
        profession = testObject.convertFrom(sector, null, null);
        //then
        assertThat(profession).isEqualTo(Profession.BANKER);
    }

    @Test
    public void should_map_transportation_to_bus_driver() {
        //given
        Sector sector = Sector.TRANSPORTATION;
        Profession profession = null;
        //when
        profession = testObject.convertFrom(sector, null, null);
        //then
        assertThat(profession).isEqualTo(Profession.BUS_DRIVER);
    }

    @Test
    public void should_map_military_to_solider() {
        //given
        Sector sector = Sector.MILITARY;
        Profession profession = null;
        //when
        profession = testObject.convertFrom(sector, null, null);
        //then
        assertThat(profession).isEqualTo(Profession.SOLIDER);
    }

    @Test
    public void should_map_nurse_to_health() {
        //given
        Sector sector = null;
        Profession profession = Profession.NURSE;
        //when
        sector = testObject.convertTo(profession, null, null);
        //then
        assertThat(sector).isEqualTo(Sector.HEALTH);
    }

    @Test
    public void should_map_banker_to_finance() {
        //given
        Sector sector = null;
        Profession profession = Profession.BANKER;
        //when
        sector = testObject.convertTo(profession, null, null);
        //then
        assertThat(sector).isEqualTo(Sector.FINANCE);
    }

    @Test
    public void should_map_bus_driver_to_transportation() {
        //given
        Sector sector = null;
        Profession profession = Profession.BUS_DRIVER;
        //when
        sector = testObject.convertTo(profession, null, null);
        //then
        assertThat(sector).isEqualTo(Sector.TRANSPORTATION);
    }

    @Test
    public void should_map_solider_to_military() {
        //given
        Sector sector = null;
        Profession profession = Profession.SOLIDER;
        //when
        sector = testObject.convertTo(profession, null, null);
        //then
        assertThat(sector).isEqualTo(Sector.MILITARY);
    }
}

package com.miciu.spring.app.mapper;

import com.google.common.collect.BiMap;
import com.google.common.collect.EnumHashBiMap;
import com.miciu.spring.app.converter.ProfessionToSectorConverter;
import com.miciu.spring.app.model.Profession;
import com.miciu.spring.app.model.Sector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfessionToSectorConverterTest {
    private ProfessionToSectorConverter testObject;
    private BiMap<Profession, Sector> map;

    @BeforeEach
    public void before() {
        testObject = new ProfessionToSectorConverter();
        map = EnumHashBiMap.create(Profession.class);
        map.put(Profession.NURSE, Sector.HEALTH);
        map.put(Profession.BANKER, Sector.FINANCE);
        map.put(Profession.BUS_DRIVER, Sector.TRANSPORTATION);
        map.put(Profession.SOLIDER, Sector.MILITARY);
    }


    @ParameterizedTest
    @EnumSource(Sector.class)
    public void should_convert_sector_to_profession(Sector sector) {
        //given
        Profession profession = null;
        //when
        profession = testObject.convertFrom(sector, null, null);
        //then
        assertThat(profession).isEqualTo(map.inverse().get(sector));
    }

    @ParameterizedTest
    @EnumSource(Profession.class) // six numbers
    public void should_convert_profession_to_sector(Profession profession) {
        //given
        Sector sector = null;
        //when
        sector = testObject.convertTo(profession, null, null);
        //then
        assertThat(sector).isEqualTo(map.get(profession));
    }

    /*@Test
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
    }*/
}

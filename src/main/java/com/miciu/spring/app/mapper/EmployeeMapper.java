package com.miciu.spring.app.mapper;

import com.miciu.spring.app.converter.ProfessionToSectorConverter;
import com.miciu.spring.app.entity.EmployeeEntity;
import com.miciu.spring.app.model.EmployeeDto;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class EmployeeMapper {
    private final MapperFacade mapperFacade;

    @Autowired
    public EmployeeMapper(MapperFacade mapperFacade) {
        this.mapperFacade = mapperFacade;
    }

    public EmployeeDto map(EmployeeEntity employeeEntity) {
        return mapperFacade.map(employeeEntity, EmployeeDto.class);
    }

    public EmployeeEntity map(EmployeeDto employeeDto) {
        return mapperFacade.map(employeeDto, EmployeeEntity.class);
    }

    public static class Configurer {

        public static void configure(final MapperFactory mapperFactory) {
            mapperFactory.getConverterFactory().registerConverter("professionToSectorConverter",new ProfessionToSectorConverter());
            mapperFactory.classMap(EmployeeEntity.class, EmployeeDto.class)

                    .customize(new CustomMapper<EmployeeEntity, EmployeeDto>() {
                        @Override
                        public void mapAtoB(EmployeeEntity employeeEntity, EmployeeDto employeeDto, MappingContext context) {
                            Calendar calendar = Calendar.getInstance();
                            int currentYear = calendar.get(Calendar.YEAR);
                            employeeDto.setFirstName(employeeEntity.getFirstName());
                            employeeDto.setLastName(employeeEntity.getLastName());
                            employeeDto.setAge(currentYear - employeeEntity.getBirthYear());
                            //super.mapAtoB(employeeEntity, employeeDto, context);
                        }

                        @Override
                        public void mapBtoA(EmployeeDto employeeDto, EmployeeEntity employeeEntity, MappingContext context) {
                            Calendar calendar = Calendar.getInstance();
                            int currentYear = calendar.get(Calendar.YEAR);
                            employeeEntity.setBirthYear(currentYear - employeeDto.getAge());
                            employeeEntity.setFirstName(employeeDto.getFirstName());
                            employeeEntity.setLastName(employeeDto.getLastName());

                            //  super.mapBtoA(employeeDto, employeeEntity, context);
                        }
                    }).fieldMap("profession","sector").converter("professionToSectorConverter").add()
                    .register();

        }
    }
}

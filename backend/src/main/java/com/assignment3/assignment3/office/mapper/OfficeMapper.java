package com.assignment3.assignment3.office.mapper;

import com.assignment3.assignment3.office.dto.OfficeDisplayDto;
import com.assignment3.assignment3.office.dto.OfficeRequestDto;
import com.assignment3.assignment3.office.model.Office;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OfficeMapper {
    OfficeDisplayDto officeDisplayFromOffice(Office office);
    Office officeFromRequest(OfficeRequestDto requestDto);
}

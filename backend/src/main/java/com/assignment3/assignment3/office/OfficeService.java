package com.assignment3.assignment3.office;

import com.assignment3.assignment3.office.dto.OfficeDisplayDto;
import com.assignment3.assignment3.office.dto.OfficeRequestDto;
import com.assignment3.assignment3.office.mapper.OfficeMapper;
import com.assignment3.assignment3.office.model.Office;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfficeService {

    private final OfficeMapper officeMapper;
    private final OfficeRepository officeRepository;

    public OfficeDisplayDto save(OfficeRequestDto requestDto) {
        var office = officeRepository.save(
                officeMapper.officeFromRequest(requestDto)
        );
        return officeMapper.officeDisplayFromOffice(office);
    }

    public List<OfficeDisplayDto> findAll() {
        return officeRepository.findAll().stream()
                .map(officeMapper::officeDisplayFromOffice)
                .collect(Collectors.toList());
    }

    public OfficeDisplayDto update(Long id, OfficeRequestDto requestDto) {
        var office = findById(id);
        office.setName(requestDto.getName());
        return officeMapper.officeDisplayFromOffice(
                officeRepository.save(office)
        );
    }

    public void delete(Long id) {
        officeRepository.deleteById(id);
    }

    public Office findById(Long id) {
        return officeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Couldn't find office with given id"
                ));
    }

    public OfficeDisplayDto findByIdDto(Long id) {
        return officeMapper.officeDisplayFromOffice(findById(id));
    }
}

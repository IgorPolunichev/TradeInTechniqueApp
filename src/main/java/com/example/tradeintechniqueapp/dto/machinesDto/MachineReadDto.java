package com.example.tradeintechniqueapp.dto.machinesDto;

import com.example.tradeintechniqueapp.database.entity.Company;
import com.example.tradeintechniqueapp.dto.companiesDto.CompanyReadDto;
import lombok.Value;

import java.time.Year;

@Value
public class MachineReadDto {
    Long id;
    String type;
    String serialNumber;
    String subtype;
    int operatingTime;
    Year yearOfRelease;
//    String companyName;
    CompanyReadDto company;

//    @Override
//    public String toString() {
//        return "MachineReadDto{" +
//               "id=" + id +
//               ", type='" + type + '\'' +
//               ", serialNumber='" + serialNumber + '\'' +
//               ", subtype='" + subtype + '\'' +
//               ", operatingTime=" + operatingTime +
//               ", yearOfRelease=" + yearOfRelease +
//               ", companyName='" + companyName + '\'' +
//               ", companyId=" + companyId +
//               '}';
//    }
}

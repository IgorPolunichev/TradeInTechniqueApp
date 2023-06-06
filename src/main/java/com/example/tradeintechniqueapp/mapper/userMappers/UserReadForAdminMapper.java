package com.example.tradeintechniqueapp.mapper.userMappers;

import com.example.tradeintechniqueapp.database.entity.BaseEntity;
import com.example.tradeintechniqueapp.database.entity.User;
import com.example.tradeintechniqueapp.database.entity.audit.AuditingEntity;
import com.example.tradeintechniqueapp.dto.usersDto.UserReadDtoForAdmin;
import com.example.tradeintechniqueapp.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReadForAdminMapper implements Mapper<User, UserReadDtoForAdmin> {

    @Override
    public UserReadDtoForAdmin map(User object) {
        return new UserReadDtoForAdmin(
                object.getId(),
                object.getUsername(),
                object.getFirstname(),
                object.getLastname(),
                object.getSurname(),
                object.getBirthDate(),
                object.getRole(),
                object.getPosition()
                , object.getCreatedAt()
                , object.getModifiedAt()
                , object.getCreatedBy()
                , object.getLastModifiedBy());
    }

}

package org.gigsoft.secondoblog.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoleTypeListToStringConverter implements AttributeConverter<Set<RoleType>, String> {
    @Override
    public String convertToDatabaseColumn(Set<RoleType> attribute) {
    	return String.join(", ", attribute.stream().map(RoleType::name).toList());        
    }

    @Override
    public Set<RoleType> convertToEntityAttribute(String dbData) {
    	Set<RoleType> roles = new HashSet<>();
    	if (dbData == null)
    		return null;
    	Stream.of(dbData.split(", ")).map(RoleType::valueOf).forEach(roles::add);
    	return roles;
    }	
}

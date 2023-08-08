package org.gigsoft.secondoblog.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RoleType {
	GUEST, USER, MANAGER, ADMIN
}

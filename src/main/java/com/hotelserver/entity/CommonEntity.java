/**
 * @author SUDEEP
 */
package com.hotelserver.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public abstract class CommonEntity implements Serializable {

	private static final long serialVersionUID = 6451599191411832862L;

	@Transient

	@Column(name = "status")
	@JsonProperty("status")
	private Integer status;

	@Column(name = "created_date")
	@JsonProperty("createdDate")
	private String createdDate;

	@Column(name = "created_by")
	@JsonProperty("createdBy")
	private Long createdBy;

	@Column(name = "modified_date")
	@JsonProperty("modifiedDate")
	private String modifiedDate;

	@Column(name = "modified_by")
	@JsonProperty("modifiedBy")
	private Long modifiedBy;

}
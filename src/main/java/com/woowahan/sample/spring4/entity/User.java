package com.woowahan.sample.spring4.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by 서오석 on 2016. 4. 22..
 * 테스트 용
 */
@Entity
public class User {

	@Id
	@GeneratedValue
	@Getter
	private int id;

	@Getter @Setter
	private String name;

	@Getter @Setter
	private int count;

}

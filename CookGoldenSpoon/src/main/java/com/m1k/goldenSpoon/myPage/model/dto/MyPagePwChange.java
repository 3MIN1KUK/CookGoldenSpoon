package com.m1k.goldenSpoon.myPage.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MyPagePwChange {

	private String curPassword;
	private String newPassword;
	
	private int memberNo;
}

package com.cos.dbapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
// Dto는 통신할때는 데이터용어
// DB 데이터는 모델 model은 데이터베이스로 통신할때만 만든다. 레퍼지토리에서만 만들어진다.
// 서버쪽으로 들어오는 데이터는 request데이터
public class CMRespDto<T> { 
	private int code;
	private String msg;
	private T data;
}

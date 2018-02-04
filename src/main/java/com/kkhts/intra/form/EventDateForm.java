package com.kkhts.intra.form;

import java.util.Date;

import lombok.Data;

/**
 * イベント画面のFormModel
 */
@Data
public class EventDateForm {

	/** ID */
	private String id;

	/** 日付 */
	private Date date;
}

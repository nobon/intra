package com.kkhts.intra.form;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * イベント画面のFormModel
 */
@Data
public class EventForm {

	/** イベントID */
	private Long id;

	/** イベント名 */
	private String name;

	/** 回答期限 */
	private Date answerLimit;

	/** イベント日付 */
	private List<EventDateForm> eventDates;
}
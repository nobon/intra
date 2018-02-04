package com.kkhts.intra.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.kkhts.intra.form.EventDateForm;

import lombok.Data;

@Data
@Entity
@Table(name="event_dates")
public class EventDate {

	public EventDate(){};
	public EventDate(EventDateForm eventDateForm) {
		BeanUtils.copyProperties(eventDateForm, this);
	}

    @Id
    @GeneratedValue
    /** ID */
    private Long id;

    /** イベント日付 */
	private Date date;

	/** イベント */
	@ManyToOne
	private Event event;
}
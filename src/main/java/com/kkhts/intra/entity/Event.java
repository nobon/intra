package com.kkhts.intra.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.kkhts.intra.form.EventDateForm;
import com.kkhts.intra.form.EventForm;

import lombok.Data;

@Data
@Entity
@Table(name="events")
public class Event {
	
	public Event(){};

	public Event(EventForm eventForm) {
		BeanUtils.copyProperties(eventForm, this, "eventDates");
		this.eventDates = new ArrayList<EventDate>();
		for (EventDateForm eventDate : eventForm.getEventDates()) {
			eventDates.add(new EventDate(eventDate));
		}
	}

    @Id
    @GeneratedValue
    /** ID */
    private Long id;

	/** イベント名 */
	private String name;

	/** 回答期限 */
	private Date answerLimit;

	/** イベント日付 */
	@OneToMany(mappedBy = "event")
	private List<EventDate> eventDates;

	/** イベント日付 */
	@OneToOne
	EventDate commitEventDate;

}
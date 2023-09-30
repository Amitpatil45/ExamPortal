package com.examportal.model.exam;

import java.util.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Quiz {
	   @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private  long qid;
	    private  String title;
	    private  String description;
	    private  boolean active=true;
	    
	    @ManyToOne
	    private  Category category; 
	    
	  
	    @OneToMany
	    
	    private List<Question>questions =new ArrayList();

		public List<Question> getQuestions() {
			return questions;
		}

		public void setQuestions(List<Question> questions) {
			this.questions = questions;
		}

		public Quiz() {
			super();
			// TODO Auto-generated constructor stub
		}

	

		public long getQid() {
			return qid;
		}

		public void setQid(long qid) {
			this.qid = qid;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public boolean isActive() {
			return active;
		}

		public void setActive(boolean active) {
			this.active = active;
		}

	

		public Category getCategory() {
			return category;
		}

		public void setCategory(Category category) {
			this.category = category;
		}
	    
	
}

package com.SWEProject.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Statistics {
		private String tab ;
		private String col;
		private String operation;
		private boolean view;
		private double value;
		public double getValue() {
			return value;
		}
		public void setValue(double value) {
			this.value = value;
		}
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private int id;
		public boolean isView() {
			return view;
		}
		public void setView(boolean view) {
			this.view = view;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public Statistics() 
		{
			tab="";
			col="";
			operation="";
		}
		public Statistics(String table, String column, String operation ) 
		{
			this.tab = table;
			this.col = column;
			this.operation = operation;
		}
		
		public String getTable() 
		{
			return tab;
		}
		public void setTable(String table) {
			this.tab = table;
		}
		public String getColumn() {
			return col;
		}
		public void setColumn(String column) {
			this.col = column;
		}
		public String getOperation() {
			return operation;
		}
		public void setOperation(String operation) {
			this.operation = operation;
		}
		
}

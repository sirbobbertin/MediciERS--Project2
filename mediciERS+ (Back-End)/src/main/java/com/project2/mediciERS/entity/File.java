package com.project2.mediciERS.entity;

import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "file_details")
public class File {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "file_id")
	private String id;

	@Column(name = "file_name")
	private String name;

	@Column(name = "file_type")
	private String type;
	
	@Column(name = "rb_id") 
	private int rbId;

	@Lob
	@Column(name = "file_data")
	private byte[] data;

	

	public File() {
		super();
		// TODO Auto-generated constructor stub
	}

	public File(String id, String name, String type, int rbId, byte[] data) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.rbId = rbId;
		this.data = data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getRbId() {
		return rbId;
	}

	public void setRbId(int rbId) {
		this.rbId = rbId;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "File [id=" + id + ", name=" + name + ", type=" + type + ", rbId=" + rbId + ", data="
				+ Arrays.toString(data) + "]";
	}
	
	

}
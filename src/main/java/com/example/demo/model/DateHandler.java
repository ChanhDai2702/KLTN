package com.example.demo.model;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class DateHandler extends StdDeserializer<Date>{

	protected DateHandler(Class<?> vc) {
		super(vc);
		// TODO Auto-generated constructor stub
	}
	public  DateHandler() {
		this(null);
	}

	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String date=p.getText();
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			return sdf.parse(date);
		} catch (Exception e) {
			return null;
		}
	
		
	}

}

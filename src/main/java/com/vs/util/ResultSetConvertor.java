package com.vs.util;

import java.sql.ResultSet;

import com.vs.bean.Sample;

public class ResultSetConvertor<T> {
  public T convert(ResultSet rs){
	  T t = (T)new Sample();
  	return t;
  }
}
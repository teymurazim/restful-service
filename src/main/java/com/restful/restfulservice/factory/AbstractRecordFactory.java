package com.restful.restfulservice.factory;

import java.util.ArrayList;
import java.util.List;

import com.restful.restfulservice.type.RecordObject;
import com.restful.restfulservice.type.EntityObject;
/*
 * This class implements factory methods. More will be added as needed.
 */
public abstract class AbstractRecordFactory<E extends EntityObject, R extends RecordObject> {
	
	public abstract R buildRecord(E entity);

	public List<R> buildRecordList(List<E> entityList) {
		
		if (entityList == null || entityList.isEmpty())
			return null;
		
		List<R> recordList = new ArrayList<>();
		for (E e : entityList) {
			recordList.add(buildRecord(e));
		}
		
		return recordList;
	}
}


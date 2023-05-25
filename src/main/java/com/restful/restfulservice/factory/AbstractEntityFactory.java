package com.restful.restfulservice.factory;

import com.restful.restfulservice.type.RecordObject;
import com.restful.restfulservice.type.EntityObject;

public abstract class AbstractEntityFactory<R extends RecordObject, E extends EntityObject> {
	
	public abstract E buildEntity(R dto);
	
}

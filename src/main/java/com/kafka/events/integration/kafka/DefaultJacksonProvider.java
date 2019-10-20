package com.kafka.events.integration.kafka;


public class DefaultJacksonProvider<E> extends BaseJacksonSerializer<E>{

	private Class<E> clz;
	
	public DefaultJacksonProvider(Class<E> clz) {
        super();
        this.clz = clz;
    }
	
	@Override
	public Class<E> getClz() {
		return clz;
	}

}

package org.tb.storage.neo.domain;

public interface TagBase {
	
	String getTitle();
	
	void setTitle(String title);
	
	Iterable<TechnologyBase> getTechnologies();
	
}

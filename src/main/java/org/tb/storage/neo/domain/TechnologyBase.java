package org.tb.storage.neo.domain;

public interface TechnologyBase {
	
	String getTitle();
	
	void setTitle(String title);
	
	Iterable<TagBase> getTags();
}

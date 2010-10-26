package org.tb.storage.neo.domain;

import java.util.LinkedList;
import java.util.List;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

public class Tag implements TagBase {

	private static final String TITLE_PROPERTY = "title";
	
	private final Node underlyingNode;
	
	Tag (final Node node) {
		this.underlyingNode = node;
	}
	
	Node getUnderLyingNode() {
		return this.underlyingNode;
	}
	
	@Override
	public String getTitle() {
		return (String) this.underlyingNode.getProperty(TITLE_PROPERTY);
	}

	@Override
	public void setTitle(final String title) { 
		underlyingNode.setProperty(TITLE_PROPERTY, title);
	}

	@Override
	public Iterable<TechnologyBase> getTechnologies() {
		final List<TechnologyBase> technologies = new LinkedList<TechnologyBase>();
		
		for(Relationship rel : underlyingNode.getRelationships(
				RelTypes.TAG_IN, Direction.OUTGOING)) {
			technologies.add(new Technology(rel.getEndNode()));
		}
		
		return technologies;
	}
}

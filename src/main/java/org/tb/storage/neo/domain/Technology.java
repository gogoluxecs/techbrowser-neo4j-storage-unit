package org.tb.storage.neo.domain;

import java.util.LinkedList;
import java.util.List;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

public class Technology implements TechnologyBase {

	private static final String TITLE_PROPERTY = "title";
	
	private final Node underlyingNode;
	
	Technology (final Node node) {
		this.underlyingNode = node;
	}
	
	Node getUnderlyingNode() {
		return this.underlyingNode;
	}
	
	@Override
	public final String getTitle() {
		return (String) underlyingNode.getProperty(TITLE_PROPERTY);
	}
	
	@Override
	public void setTitle(final String title) {
		underlyingNode.setProperty(TITLE_PROPERTY, title);
	}

	@Override
	public Iterable<TagBase> getTags() {
		final List<TagBase> tags = new LinkedList<TagBase>();
        for ( Relationship rel : underlyingNode.getRelationships(
            RelTypes.TAG_IN, Direction.OUTGOING ))
        {
            tags.add(new Tag(rel.getEndNode()));
        }
        
        return tags;
	}
	
	@Override
    public boolean equals(final Object otherTechnology) {
        if(otherTechnology instanceof Technology) {
            return this.underlyingNode.equals( ((Technology) otherTechnology)
                .getUnderlyingNode() );
        }
        
        return false;
    }
	
	@Override
    public int hashCode() {
        return this.underlyingNode.hashCode();
    }
	
	@Override
    public String toString() {
        return "Technology '" + this.getTitle() + "'";
    }
}

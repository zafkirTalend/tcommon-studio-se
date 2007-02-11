package com.quantum.view.subset;

import com.quantum.model.Entity;
import com.quantum.model.EntityHolder;

/**
 * Defines a node for the Subset. It contains an editable ObjectMetaData 
 * where you can erase columns you are not interested in. 
 * @author jparrai
 *
 */
public class ObjectNode implements Comparable, EntityHolder {
	private SubsetNode parent = null;
//	private ObjectMetaData metadata = null;
	private int order = 0;
    private Entity entity;
	
	public ObjectNode(SubsetNode parent, /*ObjectMetaData metadata,*/ String bookmark, String name, String schema) {
		this.parent = parent;
	//	this.metadata = metadata;
        //this.entity = new EntitySubset(name, schema, null, bookmark);
	}
	
	public ObjectNode(){
	}
	public String getType(){
		return Entity.VIEW_TYPE;
	}

	/* (non-Javadoc)
	 * @see com.quantum.view.bookmark.TreeNode#getChildren()
	 * We consider the columns of the metadata to be the children.
	 */
	public Object[] getChildren() {
//		if (metadata != null && ColumnMetaData.getColumnsMetaDataNode(metadata, this) != null) {
//			return ColumnMetaData.getColumnsMetaDataNode(metadata, this).toArray();
//		} else {
			return SubsetRoot.EMPTY_ARRAY;
//		}
	}

	public Object getParent() {
		return parent;
	}

	public boolean hasChildren() {
//		if (metadata == null) return false;
//		return (ColumnMetaData.getColumnsMetaDataNode(metadata, this) != null && 
//				ColumnMetaData.getColumnsMetaDataNode(metadata, this).size() > 0);
        return false;
	}
	
	public String getName() {
		return this.entity.getName();
	}
	
	public String toString() {
		return getName();
	}

	public int compareTo(Object o) {
		if (o instanceof ObjectNode) {
			ObjectNode node = (ObjectNode) o;
			if (node.getOrder() > getOrder()) return -1;
			if (node.getOrder() < getOrder()) return 1;
			// If the order is the same, we use alphabetical order 
			return getEntity().getQualifiedName().compareTo(
                node.getEntity().getQualifiedName());
		} else throw new ClassCastException();
	}
	
//	public void setObjectMetadata(ObjectMetaData metadata) {
//		this.metadata = metadata;
//	}
//	/**
//	 * @return
//	 */
//	public ObjectMetaData getMetaData() {
//		return metadata;
//	}

	/**
	 * @return The order of this ObjectNode inside the SubsetNode
	 */
	public int getOrder() {
		return order;
	}
	/**
	 * Sets an ordering (inside the SubsetNode) to the ObjectNode
	 * @param i
	 */
	public void setOrder(int i) {
		order = i;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof ObjectNode)) return false;
		return (getEntity().getQualifiedName().equals(
            ((ObjectNode) obj).getEntity().getQualifiedName()));
	}
	

    /**
     * @see com.quantum.model.EntityHolder#getEntity()
     */
    public Entity getEntity() {
        return this.entity;
    }

}

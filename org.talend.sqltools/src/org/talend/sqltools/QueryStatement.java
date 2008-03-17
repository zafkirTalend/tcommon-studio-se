// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.sqltools;

import java.util.Vector;

import Zql.ZConstant;
import Zql.ZExp;
import Zql.ZExpression;
import Zql.ZFromItem;
import Zql.ZQuery;
import Zql.ZSelectItem;

/**
 * @author scorreia
 * 
 * This class helps building queries. It is a wrapper for ZQuery.
 */
public class QueryStatement {

    private ZQuery query = new ZQuery();

    /**
     * Method "addFrom".
     * 
     * @param f a list of table names (either fully qualified "schema.table" or just the table name if no ambiguity
     * exists).
     */
    public void addFrom(String... f) {
        query.addFrom(createFromVector(f));
    }

    private Vector<ZSelectItem> createSelectVector(String[] f) {
        Vector<ZSelectItem> v = new Vector<ZSelectItem>();
        if (f == null) {
            return v;
        }
        for (String string : f) {
            if (string != null) {
                v.add(new ZSelectItem(string));
            }
        }
        return v;
    }

    private Vector<ZFromItem> createFromVector(String[] f) {
        Vector<ZFromItem> v = new Vector<ZFromItem>();
        if (f == null) {
            return v;
        }
        for (String string : f) {
            if (string != null) {
                v.add(new ZFromItem(string));
            }
        }
        return v;
    }

    /**
     * Method "addSelect".
     * 
     * @param s a list of column (either fully qualified "schema.table.column or not)
     */
    public void addSelect(String... s) {
        query.addSelect(createSelectVector(s));
    }

    public void addWhereColumnStringRelation(String operator, String column, String value) {
        query.addWhere(new ZExpression(operator, new ZConstant(column, ZConstant.COLUMNNAME), new ZConstant(value,
                ZConstant.STRING)));
    }

    public void addWhereColumnColumnRelation(String operator, String column1, String column2) {
        query.addWhere(new ZExpression(operator, new ZConstant(column1, ZConstant.COLUMNNAME), new ZConstant(column2,
                ZConstant.COLUMNNAME)));
    }

    public void addWhereColumnNumberRelation(String operator, String column, String value) {
        query.addWhere(new ZExpression(operator, new ZConstant(column, ZConstant.COLUMNNAME), new ZConstant(value,
                ZConstant.NUMBER)));
    }

    void addWhere(String operator, String operand1, String operand2) {
        query.addWhere(createZExpression(operator, operand1, operand2));
    }

    /**
     * DOC scorreia Comment method "createZExpression".
     * 
     * @param operator
     * @param operand1
     * @param operand2
     * @return
     */
    private ZExp createZExpression(String operator, String operand1, String operand2) {
        ZExp w = new ZExpression(operator, new ZConstant(operand1, ZConstant.UNKNOWN), new ZConstant(operand2,
                ZConstant.UNKNOWN));
        return w;
    }

    @SuppressWarnings("unchecked")
    public Vector<String> getFrom() {
        return query.getFrom();
    }

    @SuppressWarnings("unchecked")
    public Vector<String> getSelect() {
        return query.getSelect();
    }

    public ZExp getWhere() {
        return query.getWhere();
    }

    public boolean isDistinct() {
        return query.isDistinct();
    }

    public boolean isForUpdate() {
        return query.isForUpdate();
    }

    public String toString() {
        return query.toString();
    }

    public ZQuery getQuery() {
        return this.query;
    }

}

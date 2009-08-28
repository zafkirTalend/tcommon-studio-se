// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.utils.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * DOC amaumont class global comment. Detailled comment
 * 
 * @param <K> key
 * @param <V> value
 */
public class SimpleCache<K, V> {

    /**
     * 
     * DOC amaumont SimpleCache class global comment. Detailled comment
     * 
     * @param <K> key
     */
    class HashKey<K> {

        private K key;

        private long addTime;

        public HashKey(K key) {
            this.key = key;
            this.addTime = System.currentTimeMillis();
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            result = prime * result + ((key == null) ? 0 : key.hashCode());
            return result;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            HashKey other = (HashKey) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (key == null) {
                if (other.key != null)
                    return false;
            } else if (!key.equals(other.key))
                return false;
            return true;
        }

        private SimpleCache getOuterType() {
            return SimpleCache.this;
        }

    }

    List<HashKey<K>> keysOrderedByPutTime = new ArrayList<HashKey<K>>();

    protected Map<HashKey<K>, V> cache = new HashMap<HashKey<K>, V>();

    private int maxItems = Integer.MAX_VALUE;

    private long maxTime = Long.MAX_VALUE;

    public SimpleCache(int maxItems) {
        super();
        this.maxItems = maxItems;
    }

    public SimpleCache(long maxTime) {
        super();
        this.maxTime = maxTime;
    }

    public SimpleCache(long maxTime, int maxItems) {
        super();
        this.maxTime = maxTime;
        this.maxItems = maxItems;
    }

    public V get(K key) {
        HashKey<K> internalKey = new HashKey<K>(key);
        return cache.get(internalKey);
    }

    public V put(K key, V value) {
        int sizeItems = keysOrderedByPutTime.size();
        if (sizeItems >= maxItems) {
            keysOrderedByPutTime.remove(0);
        }
        if (maxTime != Long.MAX_VALUE) {
            long currentTimeMillis = System.currentTimeMillis();
            for (Iterator<HashKey<K>> iterator = keysOrderedByPutTime.iterator(); iterator.hasNext();) {
                HashKey<K> hashKey = iterator.next();
                if (hashKey.addTime - currentTimeMillis > maxTime) {
                    iterator.remove();
                } else {
                    break;
                }
            }
        }
        HashKey<K> internalKey = new HashKey<K>(key);
        keysOrderedByPutTime.add(internalKey);
        return cache.put(internalKey, value);
    }

}

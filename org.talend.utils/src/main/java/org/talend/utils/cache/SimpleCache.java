// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
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
    class HashKeyValue<K, V> {

        private K key;

        private V value;

        private long addTime;

        public HashKeyValue(K key) {
            this.key = key;
            this.addTime = System.currentTimeMillis();
        }

        public HashKeyValue(K key, V value) {
            this(key);
            this.value = value;
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
            HashKeyValue other = (HashKeyValue) obj;
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

        /**
         * Getter for value.
         * 
         * @return the value
         */
        public V getValue() {
            return value;
        }

        /**
         * Getter for key.
         * 
         * @return the key
         */
        public K getKey() {
            return key;
        }

        /**
         * Getter for addTime.
         * 
         * @return the addTime
         */
        public long getAddTime() {
            return addTime;
        }

    }

    List<HashKeyValue<K, V>> keysOrderedByPutTime = new ArrayList<HashKeyValue<K, V>>();

    protected Map<HashKeyValue<K, V>, HashKeyValue<K, V>> cache = new HashMap<HashKeyValue<K, V>, HashKeyValue<K, V>>();

    private int maxItems;

    private long maxTime;

    /**
     * 
     * DOC amaumont SimpleCache constructor comment.
     * 
     * @param maxTime in ms, a value between <code>0</code> and <code>Long.MAX_VALUE</code> included,
     * <code>Long.MAX_VALUE</code> means infinite.
     * @param maxItems max number of items to keep in the cache
     */
    public SimpleCache(long maxTime, int maxItems) {
        super();
        this.maxTime = maxTime;
        this.maxItems = maxItems;
    }

    public V get(K key) {
        HashKeyValue<K, V> internalKey = new HashKeyValue<K, V>(key);
        HashKeyValue<K, V> keyValue = cache.get(internalKey);
        if (keyValue != null) {
            return keyValue.getValue();
        } else {
            return null;
        }
    }

    public Long getAddTime(K key) {
        HashKeyValue<K, V> internalKey = new HashKeyValue<K, V>(key);
        HashKeyValue<K, V> keyValue = cache.get(internalKey);
        if (keyValue != null) {
            return keyValue.getAddTime();
        } else {
            return null;
        }
    }

    /**
     * 
     * DOC amaumont Comment method "put".
     * 
     * @param key
     * @param value
     * @return previous value for the same key
     */
    public V put(K key, V value) {
        int sizeItems = keysOrderedByPutTime.size();
        if (maxItems > 0 && sizeItems >= maxItems) {
            HashKeyValue<K, V> removedFromList = keysOrderedByPutTime.remove(0);
            cache.remove(removedFromList);
        }
        if (maxTime != Long.MAX_VALUE) {
            long currentTimeMillis = System.currentTimeMillis();
            for (Iterator<HashKeyValue<K, V>> iterator = keysOrderedByPutTime.iterator(); iterator.hasNext();) {
                HashKeyValue<K, V> hashKey = iterator.next();
                if (hashKey.addTime - currentTimeMillis > maxTime) {
                    iterator.remove();
                    cache.remove(hashKey);
                } else {
                    break;
                }
            }
        }
        HashKeyValue<K, V> internalKeyValue = new HashKeyValue<K, V>(key, value);
        keysOrderedByPutTime.add(internalKeyValue);
        HashKeyValue<K, V> previousKeyValue = cache.put(internalKeyValue, internalKeyValue);
        if (previousKeyValue != null) {
            return previousKeyValue.getValue();
        } else {
            return null;
        }
    }

}

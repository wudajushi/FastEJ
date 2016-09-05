/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wuda.fastej.util;

import java.util.*;

public abstract class CollectionUtils {

    public static boolean isEmpty(Collection collection) {
        return (collection == null || collection.isEmpty());
    }

    public static boolean isEmpty(Map map) {
        return (map == null || map.isEmpty());
    }

    public static List arrayToList(Object source) {
        return Arrays.asList(ObjectUtils.toObjectArray(source));
    }

    @SuppressWarnings("unchecked")
    public static void mergeArrayIntoCollection(Object array, Collection collection) {
        if(collection == null) {
            throw new IllegalArgumentException("Collection must not be null");
        }
        Object[] arr = ObjectUtils.toObjectArray(array);
        for(Object elem : arr) {
            collection.add(elem);
        }
    }

    @SuppressWarnings("unchecked")
    public static void mergePropertiesIntoMap(Properties props, Map map) {
        if(map == null) {
            throw new IllegalArgumentException("Map must not be null");
        }
        if(props != null) {
            for(Enumeration en = props.propertyNames(); en.hasMoreElements(); ) {
                String key = (String) en.nextElement();
                Object value = props.getProperty(key);
                if(value == null) {
                    // Potentially a non-String value...
                    value = props.get(key);
                }
                map.put(key, value);
            }
        }
    }


    public static boolean contains(Iterator iterator, Object element) {
        if(iterator != null) {
            while(iterator.hasNext()) {
                Object candidate = iterator.next();
                if(ObjectUtils.nullSafeEquals(candidate, element)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean contains(Enumeration enumeration, Object element) {
        if(enumeration != null) {
            while(enumeration.hasMoreElements()) {
                Object candidate = enumeration.nextElement();
                if(ObjectUtils.nullSafeEquals(candidate, element)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean containsInstance(Collection collection, Object element) {
        if(collection != null) {
            for(Object candidate : collection) {
                if(candidate == element) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean containsAny(Collection source, Collection candidates) {
        if(isEmpty(source) || isEmpty(candidates)) {
            return false;
        }
        for(Object candidate : candidates) {
            if(source.contains(candidate)) {
                return true;
            }
        }
        return false;
    }

    public static Object findFirstMatch(Collection source, Collection candidates) {
        if(isEmpty(source) || isEmpty(candidates)) {
            return null;
        }
        for(Object candidate : candidates) {
            if(source.contains(candidate)) {
                return candidate;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T findValueOfType(Collection<?> collection, Class<T> type) {
        if(isEmpty(collection)) {
            return null;
        }
        T value = null;
        for(Object element : collection) {
            if(type == null || type.isInstance(element)) {
                if(value != null) {
                    // More than one value found... no clear single value.
                    return null;
                }
                value = (T) element;
            }
        }
        return value;
    }

    public static Object findValueOfType(Collection<?> collection, Class<?>[] types) {
        if(isEmpty(collection) || ObjectUtils.isEmpty(types)) {
            return null;
        }
        for(Class<?> type : types) {
            Object value = findValueOfType(collection, type);
            if(value != null) {
                return value;
            }
        }
        return null;
    }

    public static boolean hasUniqueObject(Collection collection) {
        if(isEmpty(collection)) {
            return false;
        }
        boolean hasCandidate = false;
        Object candidate = null;
        for(Object elem : collection) {
            if(!hasCandidate) {
                hasCandidate = true;
                candidate = elem;
            } else if(candidate != elem) {
                return false;
            }
        }
        return true;
    }

    public static Class<?> findCommonElementType(Collection collection) {
        if(isEmpty(collection)) {
            return null;
        }
        Class<?> candidate = null;
        for(Object val : collection) {
            if(val != null) {
                if(candidate == null) {
                    candidate = val.getClass();
                } else if(candidate != val.getClass()) {
                    return null;
                }
            }
        }
        return candidate;
    }

    public static <A, E extends A> A[] toArray(Enumeration<E> enumeration, A[] array) {
        ArrayList<A> elements = new ArrayList<A>();
        while(enumeration.hasMoreElements()) {
            elements.add(enumeration.nextElement());
        }
        return elements.toArray(array);
    }

    public static <E> Iterator<E> toIterator(Enumeration<E> enumeration) {
        return new EnumerationIterator<E>(enumeration);
    }

    private static class EnumerationIterator<E> implements Iterator<E> {

        private Enumeration<E> enumeration;

        public EnumerationIterator(Enumeration<E> enumeration) {
            this.enumeration = enumeration;
        }

        public boolean hasNext() {
            return this.enumeration.hasMoreElements();
        }

        public E next() {
            return this.enumeration.nextElement();
        }

        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Not supported");
        }
    }


}
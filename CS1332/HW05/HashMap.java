import java.util.*;

/**
 * Your implementation of a HashMap, using external chaining as your collision
 * policy.  Read the PDF for more instructions on external chaining.
 *
 * @author Hussain Mumtaz
 * @version 1.0
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries.
     */
    public HashMap() {
        // Initialize your hashtable here.
        table = (MapEntry<K,V>[]) new MapEntry[STARTING_SIZE];
    }

    @Override
    public V add(K key, V value) throws IllegalArgumentException {
        double loadfactor = size / table.length;
        if (key == null || value == null) {
            throw new IllegalArgumentException("key or value is null");
        } else if (loadfactor > MAX_LOAD_FACTOR) {
            resize(table.length * 2 + 1);
            return add(key, value);
        } else if (contains(key)) {

            return set(key, value);
        } else {
            int pos = hash(key);
            size++;
            MapEntry<K,V> entry = new MapEntry<>(key, value);
            if (table[pos] == null) {
                table[pos] = entry;
                return null;
            } else {
                MapEntry<K,V> tail = lastNode(table[pos]);
                tail.setNext(entry);
                return null;
            }
        }
    }

    private int hash(K key, int tlength) {
        int hc = key.hashCode();
        return hc % tlength;
    }

    private int hash(K key) {
        int hc = key.hashCode();
        return hc % table.length;
    }

    private void resize(int tlength) {
        MapEntry<K,V>[] tmp = (MapEntry<K,V>[]) new MapEntry[tlength];
        for (MapEntry<K,V> entry: table) {
            if (entry != null) {
                int tmpPos = hash(entry.getKey(), tlength);
                MapEntry<K,V> leaf = new MapEntry<>(entry.getKey(), entry.getValue());
                if (tmp[tmpPos] == null) {
                        tmp[tmpPos] = leaf;
                        MapEntry<K,V> next = entry.getNext();
                        while (next != null) {
                            int inPos = hash(next.getKey(), tlength);
                            MapEntry<K,V> inNode = new MapEntry<>(next.getKey(), next.getValue());
                            if (tmp[inPos] == null) {
                                tmp[inPos] = inNode;
                                next = next.getNext();
                            } else {
                                MapEntry<K,V> tail = lastNode(tmp[inPos]);
                                tail.setNext(inNode);
                                next = next.getNext();
                            }
                        }
                } else {
                    MapEntry<K,V> tail = lastNode(tmp[tmpPos]);
                    tail.setNext(leaf);
                    MapEntry<K,V> next = entry.getNext();
                    while (next != null) {
                        int inPos = hash(next.getKey(), tlength);
                        MapEntry<K,V> inNode = new MapEntry<>(next.getKey(), next.getValue());
                        if (tmp[inPos] == null) {
                            tmp[inPos] = inNode;
                            next = next.getNext();
                        } else {
                            MapEntry<K,V> inTail = lastNode(tmp[inPos]);
                            inTail.setNext(inNode);
                            next = next.getNext();
                        }
                    }
                }
            }
        }
        table = (MapEntry<K,V>[]) new MapEntry[tlength];
        table = tmp;
    }

    private MapEntry<K,V> lastNode(MapEntry<K,V> node) {
        if (node.getNext() == null) {
            return node;
        } else {
            return lastNode(node.getNext());
        }
    }

    @Override
    public boolean contains(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("Null Key");
        }
        int pos = hash(key);
        if (table[pos] != null) {
            if (table[pos].getNext() == null) {
                return table[pos].getKey().equals(key);
            } else {
                MapEntry<K,V> next = table[pos];
                while (next != null) {
                    if (next.getKey().equals(key)) {
                        return true;
                    } else {
                        next = next.getNext();
                    }
                }
            }
        }
        return false;
    }

    private MapEntry<K,V>[] nextValTable(MapEntry<K,V>[] arr, MapEntry<K,V> node, int pos) {
        if (node.getNext() != null) {
            MapEntry<K,V> leaf = new MapEntry<>(node.getNext().getKey(), node.getNext().getValue());
            arr[pos] = leaf;
            pos = pos + 1;
            return nextValTable(arr, node.getNext(), pos);
        } else {
            return arr;
        }
    }

    private MapEntry<K,V> getIthNode(MapEntry<K,V> node, int i) {
        if (i >= 0) {
            i = i - 1;
            return getIthNode(node.getNext(), i);
        } else {
            return node;
        }
    }

    private V set(K key, V value) {
        int pos = hash(key);
        V retVal = value;
        if (table[pos] != null) {
            if (table[pos].getNext() == null) {
                retVal = table[pos].getValue();
                table[pos].setValue(value);
                return retVal;
            } else {
                MapEntry<K,V> next = table[pos];
                while (next != null) {
                    if (next.getKey().equals(key)) {
                        retVal = next.getValue();
                        next.setValue(value);
                        return retVal;
                    } else {
                        next = next.getNext();
                    }
                }
            }
        }
        return retVal;
    }

    @Override
    public V remove(K key) throws IllegalArgumentException, NoSuchElementException {
        if (key == null) {
            throw new IllegalArgumentException("null key");
        } else {
            V retVal;
            int pos = hash(key);
            if (table[pos].getNext() == null) {
                if (table[pos].getKey().equals(key)) {
                    retVal = table[pos].getValue();
                    table[pos] = null;
                    size --;
                    return retVal;
                } else {
                    throw new NoSuchElementException("Key not in HashMap");
                }
            } else {
                MapEntry<K,V> next = table[pos];
                while (next != null) {
                    if (next.getKey().equals(key)) {
                        retVal = next.getValue();
                        next = null;
                        return retVal;
                    } else {
                        next = next.getNext();
                    }
                }
                throw new NoSuchElementException("Key not in HashMap");
            }
        }
    }

    @Override
    public V get(K key) throws IllegalArgumentException, NoSuchElementException {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        } else {
            int pos = hash(key);
            if (table[pos] != null) {
                if (table[pos].getNext() == null) {
                    if (table[pos].getKey().equals(key)) {
                        return table[pos].getValue();
                    } else {
                        throw new NoSuchElementException("Key not in HashMap");
                    }
                } else {
                    MapEntry<K,V> next = table[pos];
                    while (next != null) {
                        if (next.getKey().equals(key)) {
                            return next.getValue();
                        } else {
                            next = next.getNext();
                        }
                    }
                    throw new NoSuchElementException("Key not in HashMap");
                }
            } else {
                throw new NoSuchElementException("Key not in HashMap");
            }
        }
    }

    @Override
    public void clear() {
        table = (MapEntry<K,V>[]) new MapEntry[STARTING_SIZE];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (int i = 0; i < table.length; i ++) {
            if(table[i] != null) {
                set.add(table[i].getKey());
                if (table[i].getNext() != null) {
                    MapEntry<K,V>[] innerArray = (MapEntry<K,V>[]) new MapEntry[table.length];
                    innerArray = nextValTable(innerArray, table[i], 0);
                    while (innerArray[i] != null) {
                        set.add(innerArray[i].getKey());
                        i ++;
                    }
                }
            }
        }
        return set;
    }

    @Override
    public List<V> values() {
        List<V> list = new ArrayList<V>();
        for (int i = 0; i < table.length; i ++) {
            if(table[i] != null) {
                list.add(table[i].getValue());
                if (table[i].getNext() != null) {
                    MapEntry<K,V>[] innerArray = (MapEntry<K,V>[]) new MapEntry[table.length];
                    innerArray = nextValTable(innerArray, table[i], 0);
                    while (innerArray[i] != null) {
                        list.add(innerArray[i].getValue());
                        i ++;
                    }
                }
            }
        }
        return list;
    }

    /**
     * DO NOT USE THIS METHOD IN YOUR CODE.  IT IS FOR TESTING ONLY
     * @return the backing array of the data structure, not a copy.
     */
    public MapEntry<K, V>[] toArray() {
        return table;
    }

}
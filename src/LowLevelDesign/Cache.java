// Online Java Compiler
// Use this editor to write, compile and run your Java code online
/*
End users - Applications - API
Requirements - 
1. key, value store
2. get by key
3. put value
4. evict 
5. if cache full capacity
6. Cache hit or miss
7. eviction strategy - LRU
8. 

Input & Output:
get(key) : value
put(key, value) 

Classes:
<<Cache<Key, Value>>>
    +get(Key key): Value
    +put(Key key, Value value)
    
DefaultCache<Key, Value>
    -store: CacheStore
    -evictStrategy: EvictionStrategy 
    +get(Key key): Value
    +put(Key key, Value value)
    -evict()

<<CacheStore<Key, Value>>>
    +get(Key key): Value
    +add(Key key, Value value)
    +remove(Key key)

HashCacheStore<Key, Value>
    -capacity: int
    -store: HashMap<Key, Value>
    +add(Key key, Value value)
    +remove(Key key)
    +get(Key key): Value

<<EvictionStrategy<Key>>>
    +accessedKey(Key key)
    +evict(): Key

LRUCache
    -storeLRU: DoublyLinkedList<Key>
    -pointerMap: HashMap<Key, DoublyLinkedList<Key>>
    +accessedKey(Key key)
    +evict(): Key

CacheFactory
    +getCache(String storeType, String Strategy): Cache
    
*/

import java.util.*;
    
interface Cache<Key, Value>{
    public Value get(Key key);
    public void put(Key key, Value value);
}

class DefaultCache<Key, Value> implements Cache<Key, Value>{
    private CacheStore<Key, Value> store;
    private EvictionStrategy<Key> evictStrategy;
    
    public DefaultCache(CacheStore<Key, Value> store, EvictionStrategy<Key> evictStrategy){
        this.store = store;
        this.evictStrategy = evictStrategy;
    }
    
    @Override
    public Value get(Key key){
        return store.get(key);
    }
    
    @Override
    public void put(Key key, Value value){
        try{
            store.add(key, value);
            evictStrategy.accessedKey(key);
        } catch(CapacityFullException e){
            evict();
        }
    }
    
    private void evict(){
        Key toBeEvicted = evictStrategy.evict();
        store.remove(toBeEvicted);
    }
}

interface CacheStore<Key, Value>{
    public void add(Key key, Value value);
    public Value get(Key key);
    public void remove(Key key);
}


class HashCacheStore<Key, Value> implements CacheStore<Key, Value>{
    private Map<Key, Value> store;
    private int capacity;
    
    public HashCacheStore(){
        store = new HashMap<Key, Value>();
        capacity = Config.storeCapacity;
    }
    
    @Override
    public Value get(Key key){
        if(store.containsKey(key)){
            return store.get(key);
        }
        else {
            throw new CacheMissException();
        }
    }
    
    @Override
    public void add(Key key, Value value){
        store.put(key, value);
        if(store.size() > capacity){
            throw new CapacityFullException();
        }
    }
    
    @Override
    public void remove(Key key){
        if(store.containsKey(key)){
            store.remove(key);
        }
        else {
            throw new KeyNotFoundException();
        }
    }
    
}

interface EvictionStrategy<Key>{
    public void accessedKey(Key key);
    public Key evict(); 
}

class LRUCache<Key> implements EvictionStrategy<Key>{
    private DoublyLinkedList<Key> storeLRU = new DoublyLinkedList<Key>();
    private Map<Key, Node<Key>> pointerMap = new HashMap<>();
    
    @Override
    public void accessedKey(Key key){
        if(pointerMap.containsKey(key)){
            Node<Key> node = pointerMap.get(key);
            storeLRU.moveToFront(node);
        }
        else {
            Node<Key> node = new Node<Key>(key);
            storeLRU.addInFront(node);
            pointerMap.put(key, node);
        }
    }
    
    @Override
    public Key evict(){
        Key toberemoved = storeLRU.removeTail();
        pointerMap.remove(toberemoved);
        return toberemoved;
    }
}

class CacheFactory<Key, Value>{
    public Cache getCache(String storeType, String strategy){
        if(storeType.equals(Config.HASH) && strategy.equals(Config.LRU)){
            return new DefaultCache<Key, Value>(new HashCacheStore<Key, Value>(), new LRUCache<Key>());
        }
        return null;
    }
}


class Node<Key>{
    private Key key;
    private Node<Key> previousNode;
    private Node<Key> nextNode;
    
    public Node(Key key){
        this.key = key;
        this.previousNode = null;
        this.nextNode = null;
    }
    
    public void setPreviousNode(Node<Key> previousNode){
         this.previousNode = previousNode;
    }

    public void setNextNode(Node<Key> nextNode){
        this.nextNode = nextNode;
    }

    public Key getKey(){
        return this.key;
    }

    public Node<Key> getPreviousNode(){
        return this.previousNode;
    }

    public Node<Key> getNextNode(){
        return this.nextNode;
    }
}

class DoublyLinkedList<Key>{
    private Node<Key> head;
    private Node<Key> tail;

    public DoublyLinkedList(){
        head = new Node<Key>(null);
        tail = new Node<Key>(null);
        head.setNextNode(tail);
        tail.setPreviousNode(head);
    }

    public void addInFront(Node<Key> node){
        node.setPreviousNode(head);
        node.setNextNode(head.getNextNode());
        head.getNextNode().setPreviousNode(node);
        head.setNextNode(node);
    }

    private void remove(Node<Key> node){
        node.getPreviousNode().setNextNode(node.getNextNode());
        node.getNextNode().setPreviousNode(node.getPreviousNode());
    }

    public Key removeTail(){
        Node<Key> lastNode = tail.getPreviousNode();
        remove(lastNode);
        return lastNode.getKey();
    }

    public void moveToFront(Node<Key> node){
        remove(node);
        addInFront(node);
    }
}



//configurations
class Config{
    public static final int storeCapacity = 3;
    public static final String HASH = "HASH";
    public static final String LRU = "LRU";
}


//Exceptions
class CacheMissException extends RuntimeException{ }
class CapacityFullException extends RuntimeException{ }
class KeyNotFoundException extends RuntimeException{ }

class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        CacheFactory<Integer, String> cacheFactory = new CacheFactory<Integer, String>();
        Cache cache = cacheFactory.getCache(Config.HASH, Config.LRU);
        cache.put(0, "a");
        cache.put(1, "b");
        System.out.println(cache.get(0));
        cache.put(2, "c");
    }
}

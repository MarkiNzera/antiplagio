public interface NodeStrategy<K, V>{
    public K getKey();

    public V getValue();
    public void setValue(V value);
}

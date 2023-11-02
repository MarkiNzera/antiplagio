public interface PlagiarismStrategy<K, V> {
    public void put(K key, V value);
    public V get(K key);
}

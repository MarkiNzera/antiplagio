import java.util.ArrayList;

public interface MultiMap<K, V> {
    public void put(K key, V value);
    public V get(K key);
    public ArrayList<V> findAllK(K key);
    public boolean contains(K key);
}

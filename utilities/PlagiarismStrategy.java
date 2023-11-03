import java.util.ArrayList;

public interface PlagiarismStrategy<K, V> {
    public void put(K key, V value);
    public V get(K key);
    public ArrayList<NodeStrategy<K, V>> nodeSet();
    public ArrayList<V> findAllK(K key);
}

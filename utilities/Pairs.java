import java.util.ArrayList;

public class Pairs<K, V>{

    ArrayList<Pair<K, V>> pairs = new ArrayList<>();

    public static class Pair<K, V>{
        private K key;
        private V value;

        public Pair(K key, V value){
            setKey(key);
            setValue(value);
        }

        public void setKey(K key){
            this.key = key;
        }

        public K getKey(){
            return key;
        }

        public void setValue(V value){
            this.value = value;
        }

        public V getValue(){
            return value;
        }
    }

    public void put(K key, V value){
        pairs.add(new Pair(key, value));
    }

}

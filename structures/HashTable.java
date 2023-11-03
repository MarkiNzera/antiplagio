import java.util.ArrayList;
import java.util.LinkedList;

public class HashTable<K, V> implements PlagiarismStrategy<K, V>{
    public static class HashNode<K, V> implements NodeStrategy<K, V>{
        private final K key;
        private V value;
        public HashNode(K key, V value) {
            this.key = key;
            setValue(value);
        }
        public K getKey(){
            return this.key;
        }

        public V getValue(){
            return this.value;
        }
        public void setValue(V value){
            this.value = value;
        }
    }

    private LinkedList<HashNode<K, V>>[] table;
    private int tableMaxSize = 593;
    private int size;

    public HashTable(){
        table = new LinkedList[tableMaxSize];
        for(int i = 0; i < tableMaxSize; i++){
            table[i] = new LinkedList<>();
        }
    }

    private HashTable(int tam){
        table = new LinkedList[tam];
        for(int i = 0; i < tam; i++){
            table[i] = new LinkedList<>();
        }
        tableMaxSize = tam;
    }

    private int hashFunction(K key){
        return Math.abs(key.hashCode()) % tableMaxSize;
    }

    private void resize(){

        HashTable<K, V> newtable = new HashTable<>(tableMaxSize * 2);

        for(int i = 0; i < tableMaxSize; i++){
            if(table[i] != null){
                for(HashNode<K, V> node : table[i]){
                    if(node != null){
                        newtable.put(node.getKey(), node.getValue());
                    }
                }
            }
        }

        this.tableMaxSize *= 2;
        this.table = newtable.table;
    }

    public void put(K key, V value){

        if(tableMaxSize * 0.7 < size){
            resize();
        }

        int index = hashFunction(key);

//        for(Node<K, V> item : table[index]){
//            if(item.getKey().equals(key) && item.getValue().equals(value)){
//                throw new Exception("Impossivel ter 2 nós com o mesmo par chave e valor");
//            }
//        }

        table[index].push(new HashNode<>(key, value));
        this.size++;

    }

    public boolean contains(K key){
        int index = hashFunction(key);
        for(HashNode<K, V> item : table[index]){
            if(item.getKey().equals(key)){
                return true;
            }
        }

        return false;
    }

    public V get(K key){
        int index = hashFunction(key);
        for(HashNode<K, V> item : table[index]){
            if(item.getKey().equals(key)){
                return item.getValue();
            }
        }

        return null;
    }

    public void remove(K key){
        int index = hashFunction(key);
        for(HashNode<K, V> item : table[index]){
            if(item.getKey().equals(key)){
                table[index].remove(item);
                size--;
                return;
            }
        }

    }

    public ArrayList<V> findAll(K key){
        int index = hashFunction(key);

        ArrayList<V> temp = new ArrayList<>();
        for(HashNode<K, V> item : table[index]){
            temp.add(item.getValue());
        }

        return temp;
    }

    public ArrayList<NodeStrategy<K, V>> nodeSet(){
        ArrayList<NodeStrategy<K, V>> set = new ArrayList<>();

        for(LinkedList<HashNode<K, V>> nodes : table){
            if(nodes != null){
                set.addAll(nodes);
            }
        }

        return set;
    }
}

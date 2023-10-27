import java.util.LinkedList;

// External Chaining
public class HashTable<K, V>{
    private static class Node<K, V>{
        private final K key;
        private V value;
        public Node(K key, V value) {
            this.key = key;

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

    public LinkedList<Node<K, V>>[] table;

    public HashTable(){
        table = new LinkedList[tableMaxSize];
        for(int i = 0; i < tableMaxSize; i++){
            table[i] = new LinkedList<>();
        }
    }

    private HashTableEC(int tam){
        table = new LinkedList[tam];
        for(int i = 0; i < tam; i++){
            table[i] = new LinkedList<>();
        }
        tableMaxSize = tam;
    }

    private void resize() {

        HashTableEC<K, V> newtable = new HashTableEC<>(tableMaxSize * 2);

        for(int i = 0; i < tableMaxSize; i++){
            if(table[i] != null){
                for(Node<K, V> node : table[i]){
                    if(node != null){
                        newtable.insert(node.getKey(), node.getValue());
                    }
                }
            }
        }

        this.tableMaxSize *= 2;
        this.table = newtable.table;
    }

    @Override
    public void insert(K key, V value){

        if(tableMaxSize * 0.7 < size){
            resize();
        }

        int index = hashFunction(key);

        for(Node<K, V> item : table[index]){
            if(item.getKey().equals(key)){
                item.setValue(value);
                return;
            }
        }
        table[index].push(new Node<>(key, value));
        this.size++;

    }

    @Override
    public V get(K key){
        int index = hashFunction(key);
        for(Node<K, V> item : table[index]){
            if(item.getKey().equals(key)){
                return item.getValue();
            }
        }

        return null;
    }

    @Override
    public void remove(K key){
        int index = hashFunction(key);
        for(Node<K, V> item: table[index]){
            if(item.getKey().equals(key)){
                table[index].remove(item);
                size--;
                return;
            }
        }
    }
}

import java.util.ArrayList;
import java.util.List;

public class ST<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 4;
    private Key[] keys;
    private Value[] vals;
    private int n = 0;

    public ST() {
        this(INIT_CAPACITY);
    }
    public ST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }
    private void resize(int capacity) {
        Key[]   tempk = (Key[])   new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity]; 
        for (int i = 0; i < n; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        vals = tempv;
        keys = tempk;
    }

    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("llave nula");

        int lo = 0, hi = n-1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if      (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("llave nula");
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) return vals[i];
        return null;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (isEmpty()) return;

        
        int i = rank(key);

        // la llave no esta
        if (i == n || keys[i].compareTo(key) != 0) {
            return;
        }

        for (int j = i; j < n-1; j++)  {
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }

        n--;
        keys[n] = null;  
        vals[n] = null;

        // control tamaño
        if (n > 0 && n == keys.length/4) resize(keys.length/2);

        
    }

    public void put(Key key, Value val)  {
        if (key == null) throw new IllegalArgumentException("llave nula");

        if (val == null) {
            delete(key);
            return;
        }

        int i = rank(key);

        // llave ya en tabla
        if (i < n && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }

        // insertar nuevo par
        if (n == keys.length) resize(2*keys.length);

        for (int j = n; j > i; j--)  {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        n++;

    }


    public int size() {
        return n;
    }
    public boolean isEmpty() {
        return size() == 0;
    }

   public List<Key> keys() {
        List<Key> listaLlaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            listaLlaves.add(keys[i]);
        }
        return listaLlaves;
    }

    public List<Key> keys(Key lo, Key hi) {
        List<Key> listaLlaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (keys[i].compareTo(lo) >= 0 && keys[i].compareTo(hi) <= 0) {
                listaLlaves.add(keys[i]);
            }
        }
        return listaLlaves;
    }
    
}

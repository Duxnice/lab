package labotary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class BatchBuilder {
    private int[] data;
    private int blockSize;
    private int batchSize = 0;

    public BatchBuilder(int[] data, int blockSize) {
        if (data == null || blockSize <= 0 || blockSize >= data.length) {
            throw new IllegalArgumentException("Invalid data or blockSize");
        }
        this.data = data;
        this.blockSize = blockSize;
    }

    public List<Integer> generate() {
        List<Integer> list = IntStream
                .range(0, data.length - blockSize)
                .boxed()
                .collect(java.util.stream.Collectors.toCollection(ArrayList::new));
        Collections.shuffle(list);
        return list;
    }

    public int[] getX(int start) {
        int[] x = new int[blockSize];
        System.arraycopy(data, start, x, 0, blockSize);
        return x;
    }

    public int[] getY(int startX) {
        int[] y = new int[blockSize];
        System.arraycopy(data, startX + 1, y, 0, blockSize);
        return y;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }
}

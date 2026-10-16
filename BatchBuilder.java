package labotary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class BatchBuilder {
    private int[] data;
    private int blockSize;

    public BatchBuilder(int[] data, int blockSize) {
        this.data = data.clone();
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

    public int getBlockSize() {
        return blockSize;
    }

    public int getDataLength() {
        return data.length;
    }
}

package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1927_최소힙 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        MinHeap heap = new MinHeap();

        for (int i = 0; i < n; i++) {
            long command = Integer.parseInt(br.readLine());
            if(command == 0){
                System.out.println(heap.popHeap());
            }
            else{
                heap.insert(command);
            }
        }
    }

    public static class MinHeap {
        private long[] heap; // 힙 저장할 배열
        private int size; // 현재 힙의 크기
        private int capacity; // 힙의 용량

        public MinHeap() {
            this.capacity = 11; //초기용량
            this.size = 0;
            heap = new long[capacity];
        }

        /**
         * 현재 힙의 용량을 초과할 경우 용량을 늘리는 함수
         */
        public void ensureCapacity() {
            if(size >= capacity) {
                capacity *= 2;
                long[] newHeap = new long[capacity]; // 새로운 힙 배열 생성
                System.arraycopy(heap, 0, newHeap, 0, size); // 얕은 복사, srcPos:원본에서 복사시작 인덱스, destPos:대상배열에서 덮어쓰기 시작 인덱스
                heap = newHeap;
            }
        }

        private int parent(int i) { return i / 2; }
        private int leftChild(int i) { return 2 * i; }
        private int rightChild(int i) { return 2 * i + 1; }

        public void swap(int i, int j) {
            long temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }

        /**
         * 힙 삽입
         * @param value
         */
        public void insert(long value) {
            ensureCapacity();
            heap[size++] = value;

            //최소 힙 유지를 위한 상향 조정
            int cur = size - 1; // value가 있는 곳
            while(cur > 0 && heap[cur]  < heap[parent(cur)]) { // 부모가 나보다 크다면 자리 바꿈
                swap(cur, parent(cur));
                cur = parent(cur);
            }

        }

        /**
         * 힙 제거
         * @return
         */
        public long popHeap(){
            if(size == 0) {
                return 0;
            }

            long min = heap[0];
            heap[0] = heap[--size];

            //힙 특성 유지
            repairHeap(0);

            return min;

        }

        /**
         * 힙 재정비
         * @param i
         */
        public void repairHeap(int i) {
            int left = leftChild(i);
            int right = rightChild(i);
            int min = i;

            if(left < size && heap[left] < heap[min]) {
                min = left;
            }
            if(right < size && heap[right] < heap[min]) {
                min = right;
            }

            if(min != i) {
                swap(i, min);
                repairHeap(min);
            }

        }

    }
}

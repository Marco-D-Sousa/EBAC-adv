package MyFila;

import java.util.ArrayList;

public class Fila {
	private int maxSize;
	private int front;
	private int rear;
	private int nItems;
	private ArrayList<Integer> items;

	public Fila() {
		items = new ArrayList<>();
		front = -1;
		rear = -1;
		nItems = 0;
	}

	public void enqueue(int value) {
		items.add(value);
		++front;
		nItems++;

		if (rear == -1) {
			rear++;
		}
	}

	public void dequeue() {
		if (rear == -1) {
			throw new RuntimeException("A lista já está vazia");
		}

		--front;
		--nItems;
		items.remove(rear);
	}

	public boolean isEmpty() {
		return (nItems == 0);
	}

	public int size() {
		return nItems;
	}

	public int rear() {
		return items.get(0);
	}

	public int front() {
		return items.get(front);
	}

	@Override
	public String toString() {
		return "front = " + items.get(front) +
				", rear = " + items.get(rear) +
				", Fila = " + items;
	}

	public static void main(String[] args) {

		Fila myFila = new Fila();
		System.out.println(myFila.isEmpty());
		myFila.enqueue(56);
		myFila.enqueue(23);
		myFila.enqueue(7);
		myFila.enqueue(17);
		System.out.println(myFila);

		myFila.dequeue();
		myFila.dequeue();
		myFila.dequeue();
		myFila.dequeue();
		System.out.println(myFila);
	}
}


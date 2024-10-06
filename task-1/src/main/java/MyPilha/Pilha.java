package MyPilha;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Pilha {

	private int[] stack;
	private int top;
	private int capacity;

	// Constructor
	public Pilha(int capacity) {
		this.capacity = capacity;
		stack = new int[capacity];
		top = -1;
	}

	// Verifica se a pilha está vazia
	public boolean isEmpty() {
		return top == -1;
	}

	// Retorna um valor inteiro com a composição da pilha.
	public int size() {
		return top + 1;
	}

	// Retorna o valor no topo da pilha
	public int top() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return stack[top];
	}

	// Adiciona um elemento no topo da pilha
	public void push(int value) {
		if (isFull()){
			throw new StackOverflowError("A pilha está cheia");
		}
		stack[++top] = value;
	}

	// Retira o elemento do topo da lista
	public int pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return stack[top--];
	}

	// Verifica se a pilha está cheia
	public boolean isFull() {
		return top == capacity - 1;
	}

	@Override
	public String toString() {
		return "IntPilha{" +
				"stack=" + Arrays.toString(stack) +
				", top=" + top() +
				", capacity=" + capacity +
				'}';
	}

	public static void main(String[] args) {

		Pilha pilha = new Pilha(5);

		pilha.push(5);
		pilha.push(7);
		pilha.push(5);
		pilha.push(2);

		System.out.println(pilha);

		Pilha pilha_2 = new Pilha(2);
		pilha_2.push(3);
		pilha_2.push(5);
		System.out.println(pilha_2);
	}
}

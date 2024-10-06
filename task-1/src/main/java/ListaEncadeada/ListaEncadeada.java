package ListaEncadeada;

public class ListaEncadeada {

	Node head;

	public ListaEncadeada() {
		this.head = null;
	}

	public void addFirst(int value) {
		Node newNode = new Node(value);
		newNode.next = head;
		head = newNode;
	}

	public void insert(int index, Node node) {
		if (index == 0) {
			node.next = head;
			head = node;
		}

		Node current = head;
		int currentIndex = 0;

		while (current != null && currentIndex < index - 1) {
			current = current.next;
			currentIndex++;
		}

		if (current == null) {
			System.out.println("Indices fora dos limites da lista!");
		}

		node.next = current.next;
		current.next = node;
	}

	public void push(int value) {
		Node newNode = new Node(value);

		if (head == null) {
			head = newNode;
		} else {
			Node current = head;
			while (current.next != null) {
				current = current.next;
			}
			current.next = newNode;
		}
	}

	public void remove(int index) {
		if (index == 0) {
			if (head == null) {
				System.out.println("A lista está vazia!");
			}
			head = head.next;
		}

		Node current = head;
		int currentIndex = 0;

		while (current != null && currentIndex < index - 1) {
			current = current.next;
			currentIndex++;
		}

		if (current == null || current.next == null) {
			System.out.println("Índice fora dos limites da lista!");
		}

		current.next = current.next.next;

	}

	public Node pop() {
		if (head == null){
			System.out.println("A lista está vazia!");
			return null;
		}

		if (head.next == null) {
			Node temp = head;
			head = null;
			return temp;
		}

		Node current = head;
		while (current.next.next != null) {
			current = current.next;
		}

		Node temp = current.next;
		current.next = null;
		return temp;
	}

	public Node elementAt(int index) {
		Node current = head;
		int currentIndex = 0;

		while (current != null && currentIndex < index) {
			current = current.next;
			currentIndex++;
		}

		if (current == null) {
			System.out.println("Índice fora dos limites da lista!");
			return null;
		}

		return current;
	}

	public int size() {
		Node current = head;
		int size = 0;

		if (current == null) {
			return 0;
		}

		while (current != null) {
			size++;
			current = current.next;
		}
		return size;
	}

	public void printList() {
		Node current = head;

		if (current == null) {
			System.out.println("A lista está vazia!");
		}

		while (current != null) {
			System.out.print(current.value + " -> ");
			current = current.next;
		}
		System.out.println("null");
	}

	static class Node {
		int value;
		Node next;

		public Node(int value) {
			this.value = value;
			this.next = null;
		}

		@Override
		public String toString() {
			return "Node{" +
					"value=" + value +
					'}';
		}
	}

	public static void main(String[] args) {
		ListaEncadeada lista = new ListaEncadeada();

		lista.addFirst(3);
		lista.addFirst(13);
		lista.addFirst(8);
		lista.push(25);
		lista.push(51);

		lista.insert(4, new Node(555));

		lista.printList();
		System.out.println(lista.size());

		lista.pop();
		lista.pop();
		//lista.remove(0);
		lista.printList();
		System.out.println(lista.size());
		System.out.println(lista.elementAt(3));

	}
}

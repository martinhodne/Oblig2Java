import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TwoWayLinkedList<E> extends java.util.AbstractSequentialList<E> {

	private Node<E> head, tail;
	private int size = 0;
	
	public static void main(String[] args) {
		TwoWayLinkedList<String> list = new TwoWayLinkedList<String>();
		list.add(0, "Norge");
		list.add(1, "Tyskland");
		list.add(2, "Italia");
		list.add(3, "Østerrike");
		list.add(4, "Danmark");
		java.util.ListIterator<String> iterator = list.listIterator(0);
		System.out.println(list.toString());
		System.out.println("Result:" + iterator.next());
		System.out.println("Result:" + iterator.next());
		System.out.println("Result:" + iterator.previous());
		System.out.println("Result:" + iterator.next());
	}
	
	public TwoWayLinkedList() {

	}
	
	@Override
	public ListIterator<E> listIterator(int index) {
		return new TwoWayLinkedListIterator(index);
	}

	@Override
	public ListIterator<E> listIterator() {
		return new TwoWayLinkedListIterator();
	}

	@Override
	public int size() {
		return 0;
	}

	public TwoWayLinkedList(E[] elements) {
		for (int i = 0; i < elements.length; i++) {
			add(elements[i]);
		}
	}

	public void addFirst(E e) {
		Node<E> newNode = new Node<>(e);
		newNode.next = head;
		head = newNode;
		size++;

		if (tail == null)
			tail = head;

	}

	public void addLast(E e) {
		Node<E> newNode = new Node<>(e);

		if (tail == null)
			head = tail = newNode;
		else {
			tail.next = newNode;
			newNode.previous = tail;
			tail = newNode;
		}
		size++;
	}

	@Override
	public void add(int index, E e) {
		if (index == 0)
			addFirst(e);
		else if (index >= size)
			addLast(e);
		else
			listIterator(index).add(e);
	}

	public E removeLast() {
		if (size == 0)
			return null;
		else if (size == 1) {
			Node<E> temp = head;
			head = tail = null;
			size = 0;
			return temp.element;
		} else {
			Node<E> current = head;
			for (int i = 0; i < size - 2; i++)
				current = current.next;
			Node<E> temp = tail;
			tail = current;
			tail.next = null;
			size--;
			return temp.element;
		}
	}

	public E removeFirst() {
		if (size == 0)
			return null;
		else {
			Node<E> current = head;
			head = head.next;
			size--;
			if (head == null)
				tail = null;

			return current.element;

		}
	}

	@Override
	public E remove(int index) {
		if (index < 0 || index >= size) {
			return null;
		} else if (index == 0) {
			return removeFirst();
		} else if (index == size - 1) {
			return removeLast();
		} else {
			Node<E> previous = head;
			
			for (int i = 1; i < index; i++) {
				previous = previous.next;
			}
			/*/Node<E> current = previous.next;
			Node<E> next = current.next;
			previous.next = current.next;
			next.previous = current.previous;
			size--;/*/
			return previous.element;

		}
	}

	private class TwoWayLinkedListIterator implements java.util.ListIterator<E> {
		private Node<E> current = head, lastAccessed;
		int index = 0;
		boolean canSet = true;
		
		public TwoWayLinkedListIterator(){	
		}
		
		public TwoWayLinkedListIterator(int index){
			for(int i = 0; i < index; i++)
				current = current.next;
			this.index =index;
		}
		
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public E next() {
			if(!hasNext())
				throw new NoSuchElementException();
			E e = current.element;
			lastAccessed = current;
			current = current.next;
			canSet = true;
			index++;
			return e;
		}

		@Override
		public boolean hasPrevious() {
			return current != head;
		}
		
		@Override
		public E previous() {
			if(!hasPrevious())
				throw new NoSuchElementException();
			current = current.previous;
			index--;
			lastAccessed = current;
			canSet = true;
			return current.element;
		}

		@Override
		public int nextIndex() {
			return index;
		}

		@Override
		public int previousIndex() {
			return index - 1;
		}

		@Override
		public void remove() {
			if(lastAccessed == null)
				throw new IllegalStateException();
			Node<E> next = lastAccessed.next;
			Node<E> previous = lastAccessed.previous;
			next.previous = previous;
			previous.next = next;
			if(current == lastAccessed)
				current = previous;
			else
				index--;
			lastAccessed = null;
			canSet = false;
		}

		@Override
		public void set(E e) {
			if(!canSet)
				throw new IllegalStateException();
			lastAccessed.element = e;
		}

		@Override
		public void add(E e) {
			Node<E> previous = current.previous;
			Node<E> newNode = new Node<>(e);
			Node<E> next = current;
			newNode.next = next; 
			next.previous = newNode;
			newNode.previous = previous;
			size++;
			index++;
			canSet = false;
			lastAccessed = null;
		}
	}

	private static class Node<E> {
		E element;
		Node<E> next;
		Node<E> previous;

		public Node(E element) {
			this.element = element;
		}
	}
}

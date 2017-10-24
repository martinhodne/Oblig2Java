import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TwoWayLinkedList<E> extends java.util.AbstractSequentialList<E> {

	private Node<E> head, tail;
	private int size = 0;

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
		return size;
	}

	public TwoWayLinkedList(E[] elements) {
		for (int i = 0; i < elements.length; i++) {
			add(elements[i]);
		}
	}

	public void addFirst(E e) {

		Node<E> newNode = new Node<>(e);
		if (head == null)
			head = newNode;
		newNode.next = head;
		head.previous = newNode;
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
		else {
			Node<E> current = head;
			for (int i = 1; i < index; i++) {
				current = current.next;
			}
			Node<E> previous = current.previous;
			Node<E> newNode = new Node<>(e);
			Node<E> next = current;
			newNode.next = next;
			next.previous = newNode;
			newNode.previous = previous;
			size++;
		}
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
			Node<E> current = tail;
			Node<E> temp = tail.previous;
			temp.next = null;
			tail = temp;
			size--;
			return current.element;
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

			Node<E> current = previous.next;
			previous.next = current.next;
			(current.next).previous = current.previous;
			size--;

			return current.element;

		}
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("[");

		Node<E> current = head;
		for (int i = 0; i < size; i++) {
			result.append(current.element);
			current = current.next;
			if (current != null) {
				result.append(", ");
			} else {
				result.append("]");
			}
		}
		return result.toString();
	}

	@Override
	public void clear() {
		size = 0;
		head = tail = null;
	}

	@Override
	public E get(int index) {
		if (index < 0 || index >= size)
			return null;
		else if (index == 0)
			return getFirst();
		else if (index == size - 1)
			return getLast();
		else {
			Node<E> current = head.next;
			for (int i = 1; i < index; i++)
				current = current.next;
			return current.element;

		}
	}

	public E getFirst() {
		if (size == 0) {
			return null;
		} else {
			return head.element;
		}
	}

	public E getLast() {
		if (size == 0) {
			return null;
		} else
			return tail.element;
	}

	@Override
	public E set(int index, E e) {
		if (index < 0 || index > size - 1)
			return null;
		else {
			Node<E> current = head;
			for (int i = 0; i < index; i++)
				current = current.next;

			current.element = e;
			return current.element;
		}
	}

	private class TwoWayLinkedListIterator implements java.util.ListIterator<E> {
		private Node<E> current = null;
		private Node<E> lastAccessed = null;
		int index = 0;
		boolean canSet = true;

		public TwoWayLinkedListIterator() {
		}

		public TwoWayLinkedListIterator(int index) {
			for (int i = 0; i < index; i++){
				if(current == null)
					current = head;
				else
					current = current.next;
			}		
			this.index = index;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public E next() {
			if (!hasNext())
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
			if (!hasPrevious())
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
			if (lastAccessed == null)
				throw new IllegalStateException();
			Node<E> next = lastAccessed.next;
			Node<E> previous = lastAccessed.previous;
			next.previous = previous;
			previous.next = next;
			if (current == lastAccessed)
				current = previous;
			else
				index--;
			lastAccessed = null;
			canSet = false;
		}

		@Override
		public void set(E e) {
			if (!canSet)
				throw new IllegalStateException();
			lastAccessed.element = e;
		}

		@Override
		public void add(E e) {
			Node<E> x = current.previous;
			Node<E> y = new Node<>(e);
			Node<E> z = current;
			x.next = y;
			y.next = z;
			z.previous = z;
			y.previous = x;
			size++;
			index++;
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
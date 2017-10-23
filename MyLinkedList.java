
public class MyLinkedList<E> extends MyAbstractList<E> {
	private Node<E> head, tail;

	public MyLinkedList() {
	}

	public MyLinkedList(E[] objects) {
		super(objects);
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

		if (tail == null) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			tail = tail.next;
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
			for (int i = 1; i < index; i++)
				current.next = new Node<E>(e);
			Node<E> temp = current.next;
			(current.next).next = temp;
			size++;
		}
	}

	public E removeFirst() {
		if (size == 0)
			return null;
		else {
			Node<E> temp = head;
			head = head.next;
			size--;
			if (head == null)
				tail = null;
			return temp.element;
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
			Node<E> current = head;
			for (int i = 0; i < size; i++)
				current = current.next;

			Node<E> temp = tail;
			tail = current;
			tail.next = null;
			size--;
			return temp.element;
		}
	}

	@Override
	public E remove(int index) {
		if (index < 0 || index >= size)
			return null;
		else if (index == 0)
			return removeFirst();
		else if (index == size - 1)
			return removeLast();
		else {
			Node<E> previous = head;

			for (int i = 1; i < index; i++) {
				previous = previous.next;
			}

			Node<E> current = previous.next;
			previous.next = current.next;
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

	/** Return true if this list contains the element o */
	public boolean contains(E e) {
		Node<E> current = head;
		for (int i = 0; i < size; i++) {
			if (current.element.equals(e))
				return true;
			current = current.next;
		}
		return false;
	}

	@Override
	public E get(int index) {
		if (index < 0 || index >= size)
			return null;
		else if (index == 0)
			return getLast();
		else if (index == size - 1)
			return getLast();
		else {
			Node<E> current = head.next;
			for (int i = 1; i < index; i++)
				current = current.next;
			return current.element;

		}
	}

	@Override
	public int indexOf(E e) {
		if (head.element == e)
			return 0;
		else if (tail.element == e)
			return size - 1;
		else {
			Node<E> current = head.next;
			int index = 1;
			while (current != null) {
				if (current.element == e)
				return index;
				current = current.next;
				index++;
			}
		}
		return -1;
	}

	@Override /**
				 * Return the index of the last matching element in this list.
				 * Rreturn -1 if on match.
				 */
	public int lastIndexOf(E e) {
		int index = -1;
		Node<E> current = head;
		for (int i = 0; i < size; i++) {
			if (current.element == e)
				index = i;
			current = current.next;
		}

		return index;
	}

	@Override /**
				 * Replace the element at the specified position in this list
				 * with the specified element.
				 */
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

	@Override /** Override iterator() defined in Iterable */
	public java.util.Iterator<E> iterator() {
		return new LinkedListIterator();
	}

	public class LinkedListIterator implements java.util.Iterator<E> {
		private Node<E> current = head; // Current index

		@Override
		public boolean hasNext() {
			return (current != null);
		}

		@Override
		public E next() {
			E e = current.element;
			current = current.next;
			return e;
		}

		@Override
		public void remove() {
			System.out.println("Implementation left as an exercise");
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
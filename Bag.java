package HW_4;

import java.util.Iterator;

import HW_4.Bag.LinkedIterator;
import HW_4.Bag.Node;

public class Bag<Item> implements Iterable<Item> {

	private Node<Item> first;
	private int n;

	public class Node<Item> {
		private Item item;
		private Node<Item> next;
	}

	public Bag() {
		first = null;
		n = 0;
	}

	// Returns true if this bag is empty.

	public boolean isEmpty() {
		return first == null;
	}

//Returns the number of items in this bag.
	public int size() {
		return n;
	}

	// Adds the item to this bag.
	public void add(Item item) {
		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldfirst;
		n++;
	}

	public Iterator<Item> iterator() {
		return new LinkedIterator(first);
	}

	// an iterator, doesn't implement remove() since it's optional
	public class LinkedIterator implements Iterator<Item> {
		private Node<Item> current;

		public LinkedIterator(Node<Item> first) {
			current = first;
		}

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				return null;
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

}

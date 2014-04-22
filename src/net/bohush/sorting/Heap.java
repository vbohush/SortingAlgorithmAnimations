package net.bohush.sorting;

class Heap<E extends Comparable<E>> {
	private java.util.ArrayList<E> heapList = new java.util.ArrayList<E>();

	/** Create a default heap */
	public Heap() {
	}

	/** Create a heap from an array of objects */
	public Heap(E[] objects) {
		for (int i = 0; i < objects.length; i++)
			add(objects[i]);
	}

	/** Add a new object into the heap */
	public void add(E newObject) {
		heapList.add(newObject); // Append to the heap
		int currentIndex = heapList.size() - 1; // The index of the last node

		while (currentIndex > 0) {
			int parentIndex = (currentIndex - 1) / 2;
			// Swap if the current object is greater than its parent
			if (heapList.get(currentIndex).compareTo(heapList.get(parentIndex)) > 0) {
				E temp = heapList.get(currentIndex);
				heapList.set(currentIndex, heapList.get(parentIndex));
				heapList.set(parentIndex, temp);
			} else
				break; // the tree is a heap now

			currentIndex = parentIndex;
		}
	}

	/** Remove the root from the heap */
	public E remove() {
		if (heapList.size() == 0)
			return null;

		E removedObject = heapList.get(0);
		heapList.set(0, heapList.get(heapList.size() - 1));
		heapList.remove(heapList.size() - 1);

		int currentIndex = 0;
		while (currentIndex < heapList.size()) {
			int leftChildIndex = 2 * currentIndex + 1;
			int rightChildIndex = 2 * currentIndex + 2;

			// Find the maximum between two children
			if (leftChildIndex >= heapList.size())
				break; // The tree is a heap
			int maxIndex = leftChildIndex;
			if (rightChildIndex < heapList.size()) {
				if (heapList.get(maxIndex).compareTo(heapList.get(rightChildIndex)) < 0) {
					maxIndex = rightChildIndex;
				}
			}

			// Swap if the current node is less than the maximum
			if (heapList.get(currentIndex).compareTo(heapList.get(maxIndex)) < 0) {
				E temp = heapList.get(maxIndex);
				heapList.set(maxIndex, heapList.get(currentIndex));
				heapList.set(currentIndex, temp);
				currentIndex = maxIndex;
			} else
				break; // The tree is a heap
		}

		return removedObject;
	}

	/** Get the number of nodes in the tree */
	public int getSize() {
		return heapList.size();
	}
}
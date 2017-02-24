package net.bohush.sorting;

import java.awt.*;

public class HeapSortPanel extends SortPanel {
	private static final long serialVersionUID = 1L;
	private int redColumn = -1;
	private int greenColumn = -1;
	private java.util.ArrayList<Integer> heapList = new java.util.ArrayList<Integer>();
	
	public HeapSortPanel(String name, int sleepTime, int width, int height) {
		super(name, sleepTime, width, height);
	}

	@Override
	public void reset() {
		redColumn = -1;
		greenColumn = -1;
		heapList.clear();
	}

	@Override
	public void run() {
		try {

			// Add elements to the heap
			for (int i = 0; i < list.length; i++) {
				add(list[i]);
				repaint();
				Thread.sleep(2 * sleepTime);				
			}

			// Remove elements from the heap
			greenColumn = size;
			for (int i = list.length - 1; i >= 0; i--) {
				removeAndShow(i);
				repaint();
				Thread.sleep(sleepTime);
			}
		} catch (InterruptedException e) {
		}
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		int columnWidth = (getWidth() - 4 * BORDER_WIDTH) / size;
		int columnHeight = (getHeight() - 4 * BORDER_WIDTH) / size;
		int y = getHeight() - size * columnHeight - 3 * BORDER_WIDTH;
		int height = size * columnHeight;
		for (int i = 0; i < list.length; i++) {
			g2d.fillRect(2 * BORDER_WIDTH + columnWidth * i, y, columnWidth, height);
		}



		for (int i = heapList.size(); i < list.length; i++) {
			g2d.setPaint(getGradientPaint(i, list[i], columnWidth));
			g.fillRect(2 * BORDER_WIDTH + columnWidth * i, y, columnWidth, height);
		}
		for (int i = 0; i < heapList.size(); i++) {
			g2d.setPaint(getGradientPaint(i, heapList.get(i), columnWidth));
			g.fillRect(2 * BORDER_WIDTH + columnWidth * i, y, columnWidth, height);
		}

	}

	/** Add a new object into the heap 
	 * @throws InterruptedException */
	public void add(Integer newObject) throws InterruptedException {
		heapList.add(newObject); // Append to the heap
		repaint();
		Thread.sleep(3 * sleepTime);
		int currentIndex = heapList.size() - 1; // The index of the last node
		redColumn = currentIndex;
		while (currentIndex > 0) {
			repaint();
			Thread.sleep(3 * sleepTime);
			int parentIndex = (currentIndex - 1) / 2;
			// Swap if the current object is greater than its parent
			if (heapList.get(currentIndex).compareTo(heapList.get(parentIndex)) > 0) {
				repaint();
				Thread.sleep(6 * sleepTime);
				Integer temp = heapList.get(currentIndex);
				heapList.set(currentIndex, heapList.get(parentIndex));
				heapList.set(parentIndex, temp);
			} else {
				break; // the tree is a heap now
			}
			currentIndex = parentIndex;
			redColumn = currentIndex;
		}
		redColumn = -1;
	}

	/** Remove the root from the heap 
	 * @throws InterruptedException */
	public void removeAndShow(int showIndex) throws InterruptedException {
		if (heapList.size() == 0)
			return;
		repaint();
		Thread.sleep(4 * sleepTime);
		Integer removedObject = heapList.get(0);
		heapList.set(0, heapList.get(heapList.size() - 1));
		heapList.remove(heapList.size() - 1);
		greenColumn--;
		list[showIndex] = removedObject;
		
		int currentIndex = 0;
		while (currentIndex < heapList.size()) {
			int leftChildIndex = 2 * currentIndex + 1;
			int rightChildIndex = 2 * currentIndex + 2;

			// Find the maximum between two children
			if (leftChildIndex >= heapList.size())
				break; // The tree is a heap
			int maxIndex = leftChildIndex;
			if (rightChildIndex < heapList.size()) {
				repaint();
				Thread.sleep(4 * sleepTime);
				if (heapList.get(maxIndex).compareTo(heapList.get(rightChildIndex)) < 0) {
					maxIndex = rightChildIndex;
				}
			}

			// Swap if the current node is less than the maximum
			if (heapList.get(currentIndex).compareTo(heapList.get(maxIndex)) < 0) {
				Integer temp = heapList.get(maxIndex);
				heapList.set(maxIndex, heapList.get(currentIndex));
				heapList.set(currentIndex, temp);
				currentIndex = maxIndex;
				repaint();
				Thread.sleep(4 * sleepTime);
			} else {
				break;
			}
		}
	}

}

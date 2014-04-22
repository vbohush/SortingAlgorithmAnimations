package net.bohush.sorting;

import java.awt.Color;
import java.awt.Graphics;

public class HeapSortPanel extends SortPanel {
	private static final long serialVersionUID = 1L;
	private int redColumn = -1;
	private int greenColumn = -1;
	
	public HeapSortPanel(String name, int[] list, int sleepTime) {
		super(name, list, sleepTime);
	}

	@Override
	public void run() {
		try {

			Heap<Integer> heap = new Heap<Integer>();

			// Add elements to the heap
			for (int i = 0; i < list.length; i++) {
				heap.add(list[i]);
				Thread.sleep(10 * sleepTime);
				repaint();
			}

			// Remove elements from the heap
			for (int i = list.length - 1; i >= 0; i--) {
				list[i] = heap.remove();
				Thread.sleep(10 * sleepTime);
				repaint();
			}
			
			
		} catch (InterruptedException e) {
		}
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int columnWidth = (getWidth() - 4 * BORDER_WIDTH) / size;
		int columnHeight = (getHeight() - 4 * BORDER_WIDTH) / size;
		for (int i = (greenColumn == -1 ? 0 : greenColumn); i < list.length; i++) {
			g.setColor(Color.WHITE);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
			g.setColor(Color.BLACK);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);			
		}
		for (int i = 0; i <= greenColumn; i++) {
			g.setColor(Color.GREEN);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
			g.setColor(Color.BLACK);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);			
		}
		if(redColumn != -1) {
			g.setColor(Color.RED);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * redColumn, getHeight() - list[redColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[redColumn] * columnHeight);
			g.setColor(Color.BLACK);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * redColumn, getHeight() - list[redColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[redColumn] * columnHeight);
		}
	}
}

package net.bohush.sorting;

import java.awt.Color;
import java.awt.Graphics;

public class MergeSortPanel extends SortPanel {
	private static final long serialVersionUID = 1L;
	private int redColumn = -1;
	private int blueColumn = -1;
	private int greenColumnStart = -1;
	private int greenColumnFinish = -1;
	
	public MergeSortPanel(String name, int sleepTime, int width, int height) {
		super(name, sleepTime, width, height);
	}

	@Override
	public void reset() {
		redColumn = -1;
		blueColumn = -1;
		greenColumnStart = -1;
		greenColumnFinish = -1;
	}

	@Override
	public void run() {
		try {
			mergeSort(0, list.length - 1);
			greenColumnStart = 0;
			greenColumnFinish = size - 1;
		} catch (InterruptedException e) {
		}
		repaint();
	}

	public void mergeSort(int start, int fin) throws InterruptedException {
		if ((fin - start) > 0) {
			mergeSort(start, start + (fin - start) / 2);
			mergeSort(start + (fin - start) / 2 + 1, fin);
			merge(start, start + (fin - start) / 2, start + (fin - start) / 2 + 1, fin);
		}
	}

	public void merge(int start1, int fin1, int start2, int fin2) throws InterruptedException {
		int[] list1 = new int[fin1 - start1 + 1];
		int[] list2 = new int[fin2 - start2 + 1];
		int[] tmp = new int[list1.length + list2.length];
		System.arraycopy(list, start1, list1, 0, list1.length);
		System.arraycopy(list, start2, list2, 0, list2.length);
		Thread.sleep(2 * sleepTime);
		repaint();
	    int current1 = 0;
	    redColumn = start1 + current1;
	    int current2 = 0;
	    blueColumn = start2 + current2;
	    int current3 = 0;

		while (current1 < list1.length && current2 < list2.length) {
			Thread.sleep(2 * sleepTime);
			repaint();
			if (list1[current1] < list2[current2]) {
				tmp[current3++] = list1[current1++];
				redColumn = start1 + current1;
			} else {
				tmp[current3++] = list2[current2++];
				blueColumn = start2 + current2 - 1;
			}
			Thread.sleep(2 * sleepTime);
			repaint();
		}

		while (current1 < list1.length) {
			tmp[current3++] = list1[current1++];
			redColumn = start1 + current1;
			Thread.sleep(2 * sleepTime);
			repaint();
		}

		while (current2 < list2.length) {
			tmp[current3++] = list2[current2++];
			blueColumn = start2 + current2 - 1;
			Thread.sleep(2 * sleepTime);
			repaint();
		}

		redColumn = -1;
		blueColumn = -1;
		greenColumnStart = start1;
		for (int i = 0; i < tmp.length; i++) {
			greenColumnFinish = start1 + i;
			list[start1 + i] =  tmp[i];
			Thread.sleep(2 * sleepTime);
			repaint();
		}
		greenColumnStart = -1;
		greenColumnFinish = -1;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int columnWidth = (getWidth() - 4 * BORDER_WIDTH) / size;
		int columnHeight = (getHeight() - 4 * BORDER_WIDTH) / size;
		for (int i = 0; i < list.length; i++) {
			g.setColor(Color.WHITE);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
			g.setColor(Color.BLACK);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);			
		}
		if((greenColumnStart != -1)&&(greenColumnFinish != -1)) {
			for (int i = greenColumnStart; i <= greenColumnFinish; i++) {
				g.setColor(Color.GREEN);
				g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
				g.setColor(Color.BLACK);
				g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);			
			}
		}
		if(redColumn != -1) {
			g.setColor(Color.RED);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * redColumn, getHeight() - list[redColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[redColumn] * columnHeight);
			g.setColor(Color.BLACK);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * redColumn, getHeight() - list[redColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[redColumn] * columnHeight);
		}
		if(blueColumn != -1) {
			g.setColor(Color.BLUE);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * blueColumn, getHeight() - list[blueColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[blueColumn] * columnHeight);
			g.setColor(Color.BLACK);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * blueColumn, getHeight() - list[blueColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[blueColumn] * columnHeight);
		}

	}

}

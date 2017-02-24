package net.bohush.sorting;

import java.awt.Color;
import java.awt.Graphics;

public class BubbleSortPanel extends SortPanel {
	private static final long serialVersionUID = 1L;
	private int redColumn = -1;
	private int greenColumn = -1;
	
	public BubbleSortPanel(String name, int sleepTime, int width, int height) {
		super(name, sleepTime, width, height);
	}

	@Override
	public void reset() {
		redColumn = -1;
		greenColumn = -1;		
	}

	@Override
	public void run() {
		try {
			boolean needNextPass = true;
			for (int k = 1; k < list.length && needNextPass; k++) {
				needNextPass = false;
				for (int i = 0; i < list.length - k; i++) {
					redColumn = i;
					repaint();
					Thread.sleep(4 * sleepTime);
					if (list[i] > list[i + 1]) {
						redColumn = i + 1;
						int temp = list[i];
						list[i] = list[i + 1];
						list[i + 1] = temp;
						repaint();
						Thread.sleep(4 * sleepTime);
						needNextPass = true;
					}
				}
				greenColumn = size - k;
			}
			greenColumn = 0;
			redColumn = -1;
		} catch (InterruptedException e) {
		}
		repaint();
	}

}

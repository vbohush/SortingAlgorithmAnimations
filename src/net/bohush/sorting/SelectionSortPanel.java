package net.bohush.sorting;

import java.awt.*;

public class SelectionSortPanel extends SortPanel {
	private static final long serialVersionUID = 1L;
	private int redColumn = -1;
	private int blueColumn = -1;
	private int greenColumn = -1;
	
	public SelectionSortPanel(String name, int sleepTime, int width, int height) {
		super(name, sleepTime, width, height);
	}

	@Override
	public void reset() {
		redColumn = -1;
		blueColumn = -1;
		greenColumn = -1;		
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < list.length - 1; i++) {
				int currentMinIndex = i;
				redColumn = currentMinIndex;
				for (int j = i + 1; j < list.length; j++) {
					blueColumn = j;
					repaint();
					Thread.sleep(4 * sleepTime);
					if (list[currentMinIndex] > list[j]) {
						currentMinIndex = j;
						redColumn = currentMinIndex;
						repaint();
					}
				}

				if (currentMinIndex != i) {
					int tmp = list[currentMinIndex];
					list[currentMinIndex] = list[i];
					list[i] = tmp;
					repaint();
					Thread.sleep(4 * sleepTime);
				}
				greenColumn++;
				repaint();
			}
			greenColumn++;
			redColumn = -1;
			blueColumn = -1;
		} catch (InterruptedException e) {
		}
		repaint();
	}

}

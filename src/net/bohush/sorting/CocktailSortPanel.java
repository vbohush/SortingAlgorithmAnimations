package net.bohush.sorting;

import java.awt.Color;
import java.awt.Graphics;

public class CocktailSortPanel extends SortPanel {
	private static final long serialVersionUID = 1L;
	private int redColumn = -1;
	private int greenColumn1 = -1;
	private int greenColumn2 = -1;
	
	public CocktailSortPanel(String name, int sleepTime, int width, int height) {
		super(name, sleepTime, width, height);
	}

	@Override
	public void reset() {
		redColumn = -1;
		greenColumn1 = -1;
		greenColumn2 = -1;		
	}

	@Override
	public void run() {
		try {
			boolean swapped = true;
			int i = 0;
			int j = list.length - 1;
			while (i < j && swapped) {
				swapped = false;
				for (int k = i; k < j; k++) {
					redColumn = k;
					repaint();
					Thread.sleep(4 * sleepTime);
					if (list[k] > list[k + 1]) {
						repaint();
						Thread.sleep(4 * sleepTime);
						int temp = list[k];
						list[k] = list[k + 1];
						list[k + 1] = temp;
						swapped = true;
					}
				}
				greenColumn2 = j;
				j--;
				if (swapped) {
					swapped = false;
					for (int k = j; k > i; k--) {
						redColumn = k;
						repaint();
						Thread.sleep(4 * sleepTime);
						if (list[k] < list[k - 1]) {
							repaint();
							Thread.sleep(4 * sleepTime);
							int temp = list[k];
							list[k] = list[k - 1];
							list[k - 1] = temp;
							swapped = true;
						}
					}
				}
				i++;
				greenColumn1 = i;
			}
			redColumn = -1;
			greenColumn1 = greenColumn2;
		} catch (InterruptedException e) {
		}
		repaint();
	}
}

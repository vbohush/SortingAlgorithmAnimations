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
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int columnWidth = (getWidth() - 4 * BORDER_WIDTH) / size;
		int columnHeight = (getHeight() - 4 * BORDER_WIDTH) / size;
		for (int i = (greenColumn1 == -1 ? 0 : greenColumn1); i < (greenColumn2 == -1 ? list.length : greenColumn2); i++) {
			g.setColor(Color.WHITE);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
			g.setColor(Color.BLACK);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);			
		}
		if(greenColumn2 != -1) {
			for (int i = greenColumn2; i < list.length; i++) {
				g.setColor(Color.GREEN);
				g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
				g.setColor(Color.BLACK);
				g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);			
			}
		}
		if(greenColumn1 != -1) {
			for (int i = 0; i < greenColumn1; i++) {
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
	}

}

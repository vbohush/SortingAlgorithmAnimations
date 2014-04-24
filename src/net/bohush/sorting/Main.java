package net.bohush.sorting;

import javax.swing.*;

import java.awt.*;

public class Main extends JApplet {

	private static final long serialVersionUID = 1L;
	private SortPanel[] sortPanels = new SortPanel[9];
	private int size = 100;
	private int sleepTime = 2;
	private String animationName = "";

	public Main() {
		setLayout(new GridLayout(1, 1, 0, 0));
		SortPanelsHolder sortPanelHolder = new SortPanelsHolder();
		sortPanelHolder.setLayout(new  GridLayout(0, 3, 0, 0));
		sortPanelHolder.setBackground(Color.BLACK);
		sortPanelHolder.setForeground(Color.BLACK);
		int[] list = new int[size];
		for (int i = 0; i < list.length; i++) {
			list[i] = i + 1;
		}
		//shuffle
		for (int i = 0; i < list.length; i++) {
			int index = (int) (Math.random() * list.length);
			int temp = list[i];
			list[i] = list[index];
			list[index] = temp;
		}
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width / 3;
		int height = screenSize.height / 3;
		sortPanels[0] = new SelectionSortPanel(" Selection Sort ", list, sleepTime, width, height);
		sortPanels[1] = new InsertionSortPanel(" Insertion Sort ", list, sleepTime, width, height);
		sortPanels[2] = new BubbleSortPanel(" Bubble Sort ", list, sleepTime, width, height);
		sortPanels[3] = new QuickSortPanel(" Quick Sort ", list, sleepTime, width, height);
		sortPanels[4] = new MergeSortPanel(" Merge Sort ", list, sleepTime, width, height);
		sortPanels[5] = new HeapSortPanel(" Heap Sort ", list, sleepTime, width, height);
		sortPanels[6] = new CocktailSortPanel(" Cocktail Sort ", list, sleepTime, width, height);
		sortPanels[7] = new CombSortPanel(" Comb Sort ", list, sleepTime, width, height);
		sortPanels[8] = new ShellSortPanel(" Shell Sort ", list, sleepTime, width, height);
		
		for (int i = 0; i < sortPanels.length; i++) {
			sortPanels[i].setVisible(false);
			sortPanelHolder.add(sortPanels[i]);				
		}
		add(sortPanelHolder);
	}
	
	class SortPanelsHolder extends JPanel {
		private static final long serialVersionUID = 1L;
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.WHITE);
			Font animationNameFont = new Font("Monospaced", Font.BOLD, 150);
			FontMetrics animationNameFontFontMetrix = getFontMetrics(animationNameFont);
			g.setFont(animationNameFont);
			int x = (getWidth() - animationNameFontFontMetrix.stringWidth(animationName)) / 2;
			int y = (getHeight() - animationNameFontFontMetrix.getLeading()) / 2;
			g.drawString(animationName, x, y);
		}
	}
	
	public void beginAnimation(String animationName) {
		this.animationName = animationName;
		repaint();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.animationName = "";
		repaint();
		for (int i = 0; i < sortPanels.length; i++) {
			sortPanels[i].setVisible(true);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < sortPanels.length; i++) {
			sortPanels[i].beginAnimation();
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Sorting Algorithm Animations");
		Main main = new Main();
		frame.add(main);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		main.beginAnimation("Random");
	}
}
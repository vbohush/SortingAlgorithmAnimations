package net.bohush.sorting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

public class SortPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int SIZE = 100;
	private static final int BORDER_WIDTH = 10;
	private static final Dimension PREFFERED_DIMENSION = new Dimension(540, 440);
	private int[] list = new int[SIZE];
	private String name;
	
	public SortPanel(String name) {
		this.name = name;
		setBackground(Color.BLACK);
		for (int i = 0; i < list.length; i++) {
			list[i] = 1 + (int)(Math.random() * SIZE);
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return PREFFERED_DIMENSION;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//draw border
		g.setColor(Color.WHITE);
		g.drawRect(BORDER_WIDTH, BORDER_WIDTH, getWidth() - 2 * BORDER_WIDTH, getHeight() - 2 * BORDER_WIDTH);
		
		//draw title
		Font nameFont = new Font("Monospaced", Font.BOLD, 16);
		FontMetrics nameFontMetrix = getFontMetrics(nameFont);		
		g.setColor(Color.BLACK);
		g.fillRect((getWidth() - nameFontMetrix.stringWidth(name)) / 2, 0, nameFontMetrix.stringWidth(name), BORDER_WIDTH + nameFontMetrix.getAscent() / 3);
		g.setColor(Color.WHITE);
		g.setFont(nameFont);
		g.drawString(name, (getWidth() - nameFontMetrix.stringWidth(name)) / 2, BORDER_WIDTH + nameFontMetrix.getAscent() / 3);
		
		//draw list
		int columnWidth = (getWidth() - 4 * BORDER_WIDTH) / SIZE;
		int columnHeight = (getHeight() - 4 * BORDER_WIDTH) / SIZE;
		System.out.println(columnWidth + " " + columnHeight);
		for (int i = 0; i < list.length; i++) {
			g.setColor(Color.WHITE);
			g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
			g.setColor(Color.BLACK);
			g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);			
		}
	}

}

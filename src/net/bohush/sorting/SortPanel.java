package net.bohush.sorting;

import javafx.util.Pair;

import java.awt.*;

import javax.swing.JPanel;

public abstract class SortPanel extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	protected static final int BORDER_WIDTH = 10;
	private Dimension prefferedDimension;
	protected int size;
	protected int[] list;
	protected int sleepTime;
	private String name;
	
	public SortPanel(String name, int sleepTime, int width, int height) {
		prefferedDimension = new Dimension(width, height);
		this.name = name;
		this.sleepTime = sleepTime;
		setBackground(Color.BLACK);
	}
	
	public void setList(int[] list) {
		reset();
		this.size = list.length;
		this.list = java.util.Arrays.copyOf(list, size);
		setBackground(Color.BLACK);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return prefferedDimension;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//draw border
		g.setColor(Color.WHITE);
		g.drawRect(BORDER_WIDTH, BORDER_WIDTH, getWidth() - 2 * BORDER_WIDTH, getHeight() - 2 * BORDER_WIDTH);
		
		//draw title
		Font nameFont = new Font(Font.MONOSPACED, Font.BOLD, 18);
		FontMetrics nameFontMetrix = getFontMetrics(nameFont);		
		g.setColor(Color.BLACK);
		g.fillRect((getWidth() - nameFontMetrix.stringWidth(name)) / 2, 0, nameFontMetrix.stringWidth(name), BORDER_WIDTH + nameFontMetrix.getAscent() / 3);
		g.setColor(Color.WHITE);
		g.setFont(nameFont);
		g.drawString(name, (getWidth() - nameFontMetrix.stringWidth(name)) / 2, BORDER_WIDTH + nameFontMetrix.getAscent() / 3);

		Graphics2D g2d = (Graphics2D) g;
		int columnWidth = (getWidth() - 4 * BORDER_WIDTH) / size;
		int columnHeight = (getHeight() - 4 * BORDER_WIDTH) / size;
		int y = getHeight() - size * columnHeight - 3 * BORDER_WIDTH;
		int height = size * columnHeight;
		for (int i = 0; i < list.length; i++) {
			g2d.setPaint(getGradientPaint(i, list[i], columnWidth));
			g2d.fillRect(2 * BORDER_WIDTH + columnWidth * i, y, columnWidth, height);
		}
	}

	@Override
	public abstract void run();

	public abstract void reset();

	protected GradientPaint getGradientPaint(int position, int value, int columnWidth) {
		float startH = value / (size * 1f);
		float finishH = (value + 1) / (size * 1f);
		float S = 1; // Saturation
		float B = 1; // Brightness
		Color startColor = Color.getHSBColor(startH, S, B);
		Color finishColor = Color.getHSBColor(finishH, S, B);
		int x = 2 * BORDER_WIDTH + columnWidth * position;
		return new GradientPaint(x, 0, startColor, x + columnWidth, 0,finishColor);
	}

}

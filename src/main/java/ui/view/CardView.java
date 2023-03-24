package ui.view;

import domain.card.Card;
import ui.common.StyleUtil;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

public class CardView extends JPanel {
    private final Card card;
    private final String value;

    private static final int CARD_WIDTH = 100;
    private static final int CARD_HEIGHT = 150;
    private static final int MARGIN = 5;

    private final Dimension dimension = new Dimension(CARD_WIDTH, CARD_HEIGHT);

    private final transient Border defaultBorder = BorderFactory.createEtchedBorder(WHEN_FOCUSED, Color.white, Color.gray);
    private final transient Border focusedBorder = BorderFactory.createEtchedBorder(WHEN_FOCUSED, Color.black, Color.gray);

    private final transient Consumer<Card> handleCardClick;

    public CardView(Card card) {
        this(card, null);
    }

    public CardView(Card card, Consumer<Card> onCardClick){
        this.card = card;
        this.handleCardClick = onCardClick;
        this.value = StyleUtil.getValueToDisplay(card);

        initView();
    }

    public Dimension getDimension() {
        return dimension;
    }

    private void initView() {
        setPreferredSize(dimension);
        setBorder(defaultBorder);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                showHoverEffect();
            }

            @Override
            public void mouseExited(MouseEvent e) {
               super.mouseExited(e);
               removeHoverEffect();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(handleCardClick != null) {
                    handleCardClick.accept(card);
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        var cardColor = StyleUtil.convertCardColor(card.getColor());

        fillBackground(g2, cardColor);
        drawWhiteOvalInCenter(g2);
        drawValueInCenter(g2, cardColor);
        drawValueInCorner(g2);
    }

    private void fillBackground(Graphics2D g2, Color cardColor) {
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, CARD_WIDTH, CARD_HEIGHT);

        g2.setColor(cardColor);
        g2.fillRect(MARGIN, MARGIN, CARD_WIDTH - 2 * MARGIN, CARD_HEIGHT - 2 * MARGIN);
    }

    private void drawWhiteOvalInCenter(Graphics2D g2) {
        var transformer = g2.getTransform();
        g2.setColor(Color.white);
        g2.rotate(45, (double) CARD_WIDTH * 3 / 4, (double) CARD_HEIGHT * 3 / 4);
        g2.fillOval(0, CARD_HEIGHT / 4, CARD_WIDTH * 3 / 5, CARD_HEIGHT);

        g2.setTransform(transformer);
    }

    private void drawValueInCenter(Graphics2D g2, Color cardColor) {
        var defaultFont = new Font(StyleUtil.DEFAULT_FONT, Font.BOLD, CARD_WIDTH / 2 + 5);
        var fontMetrics = this.getFontMetrics(defaultFont);
        int stringWidth = fontMetrics.stringWidth(value) / 2;
        int fontHeight = defaultFont.getSize() / 3;

        g2.setColor(cardColor);
        g2.setFont(defaultFont);
        g2.drawString(value, CARD_WIDTH / 2 - stringWidth, CARD_HEIGHT / 2 + fontHeight);
    }

    private void drawValueInCorner(Graphics2D g2) {
        var defaultFont = new Font(StyleUtil.DEFAULT_FONT, Font.ITALIC, CARD_WIDTH / 5);

        g2.setColor(Color.white);
        g2.setFont(defaultFont);
        g2.drawString(value, MARGIN, 5 * MARGIN);
    }

    private void showHoverEffect(){
        setBorder(focusedBorder);

        Point p = getLocation();
        p.y -= 20;
        setLocation(p);
    }

    private void removeHoverEffect() {
        setBorder(defaultBorder);

        Point p = getLocation();
        p.y += 20;
        setLocation(p);
    }
}

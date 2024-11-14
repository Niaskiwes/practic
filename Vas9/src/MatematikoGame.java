import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
public class MatematikoGame extends JFrame {
    private static final int GRID_SIZE = 5;
    private static final int NUM_CARDS = 52;
    private static final int MAX_NUMBER = 13;
    private static final int CARDS_PER_NUMBER = 4;
    private int[][] grid;
    private List<Integer> deck;
    private Random random;
    private JButton[][] gridButtons;
    private JLabel playerScoreLabel;
    private JLabel computerScoreLabel;
    private JButton drawButton;
    private JButton computerMoveButton;
    private int currentCard;
    private int playerScore;
    private int computerScore;
    private boolean isPlayerTurn;
    public MatematikoGame() {
        grid = new int[GRID_SIZE][GRID_SIZE];
        deck = new ArrayList<>();
        random = new Random();
        currentCard = -1;
        playerScore = 0;
        computerScore = 0;
        isPlayerTurn = true;
        initializeDeck();
        initializeGUI();
    }
    private void initializeDeck() {
        for (int i = 1; i <= MAX_NUMBER; i++) {
            for (int j = 0; j < CARDS_PER_NUMBER; j++) {
                deck.add(i);
            }
        }
        Collections.shuffle(deck);
    }

    private void initializeGUI() {
        setTitle("Математико");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        gridButtons = new JButton[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                gridButtons[i][j] = new JButton();
                gridButtons[i][j].setPreferredSize(new Dimension(60, 60));
                final int row = i;
                final int col = j;
                gridButtons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (isPlayerTurn && currentCard != -1 && grid[row][col] == 0) {
                            placeCard(row, col, currentCard);
                            currentCard = -1;
                            drawButton.setEnabled(true);
                            isPlayerTurn = false;
                            updateTurnLabel();
                        }
                    }
                });
                gridPanel.add(gridButtons[i][j]);
            }
        }
        add(gridPanel, BorderLayout.CENTER);
        JPanel controlPanel = new JPanel(new GridLayout(3, 1));
        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        playerScoreLabel = new JLabel("Игрок: 0");
        computerScoreLabel = new JLabel("Компьютер: 0");
        scorePanel.add(playerScoreLabel);
        scorePanel.add(computerScoreLabel);
        controlPanel.add(scorePanel);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        drawButton = new JButton("Взять карту");
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!deck.isEmpty() && isPlayerTurn) {
                    currentCard = drawCard();
                    JOptionPane.showMessageDialog(MatematikoGame.this, "Карта: " + currentCard);
                    drawButton.setEnabled(false);
                } else if (!isPlayerTurn) {
                    JOptionPane.showMessageDialog(MatematikoGame.this, "Передайте ход");
                } else {
                    JOptionPane.showMessageDialog(MatematikoGame.this, "No more cards in the deck!");
                }
            }
        });
        buttonPanel.add(drawButton);
        computerMoveButton = new JButton("Компьютер");
        computerMoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPlayerTurn) {
                    makeComputerMove();
                    isPlayerTurn = true;
                    updateTurnLabel();
                } else {
                    JOptionPane.showMessageDialog(MatematikoGame.this, "Примите ход");
                }
            }
        });
        buttonPanel.add(computerMoveButton);
        controlPanel.add(buttonPanel);

        JPanel turnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel turnLabel = new JLabel("Ходит: Игрок");
        turnPanel.add(turnLabel);
        controlPanel.add(turnPanel);
        add(controlPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
    }
    private void updateTurnLabel() {
        JLabel turnLabel = (JLabel) ((JPanel)((JPanel)getContentPane().getComponent(1)).getComponent(2)).getComponent(0);
        turnLabel.setText("Ходит: " + (isPlayerTurn ? "Игрок" : "Компьютер"));
    }
    private int drawCard() {
        if (deck.isEmpty()) {
            return -1;
        }
        return deck.remove(0);
    }
    private void placeCard(int row, int col, int card) {
        grid[row][col] = card;
        gridButtons[row][col].setText(String.valueOf(card));
        updateScore();
        if (isGridFull()) {
            endGame();
        }
    }
    private boolean isGridFull() {
        for (int[] row : grid) {
            for (int cell : row) {
                if (cell == 0) return false;
            }
        }
        return true;
    }
    private void updateScore() {
        int newScore = calculateScore();
        if (isPlayerTurn) {
            playerScore += newScore;
            playerScoreLabel.setText("Счет игрока: " + playerScore);
        } else {
            computerScore += newScore;
            computerScoreLabel.setText("Счет компьютера: " + computerScore);
        }
    }
    private int calculateScore() {
        int score = 0;
        score += calculateRowColumnScore();
        score += calculateDiagonalScore();
        return score;
    }
    private int calculateRowColumnScore() {
        int score = 0;
        for (int i = 0; i < GRID_SIZE; i++) {
            score += getScoreForLine(grid[i]);
            int[] column = new int[GRID_SIZE];
            for (int j = 0; j < GRID_SIZE; j++) {
                column[j] = grid[j][i];
            }
            score += getScoreForLine(column);
        }
        return score;
    }
    private int calculateDiagonalScore() {
        int[] mainDiagonal = new int[GRID_SIZE];
        int[] antiDiagonal = new int[GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            mainDiagonal[i] = grid[i][i];
            antiDiagonal[i] = grid[i][GRID_SIZE - 1 - i];
        }
        return getScoreForLine(mainDiagonal) + getScoreForLine(antiDiagonal);
    }
    private int getScoreForLine(int[] line) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : line) {
            if (num != 0) {
                counts.put(num, counts.getOrDefault(num, 0) + 1);
            }
        }
        if (counts.isEmpty()) {
            return 0;
        }
        int maxCount = Collections.max(counts.values());
        int score = 0;

        switch (maxCount) {
            case 2: score = counts.size() == 2 ? 20 : 10; break;
            case 3: score = counts.size() == 2 ? 80 : 40; break;
            case 4: score = 160; break;
            case 5: score = 200; break;
        }
        if (isConsecutive(line)) {
            score = Math.max(score, 50);
        }
        return score;
    }

    private boolean isConsecutive(int[] line) {
        List<Integer> sorted = new ArrayList<>();
        for (int num : line) {
            if (num != 0) {
                sorted.add(num);
            }
        }
        Collections.sort(sorted);
        if (sorted.size() < 5) return false;
        for (int i = 1; i < sorted.size(); i++) {
            if (sorted.get(i) != sorted.get(i-1) + 1) return false;
        }
        return true;
    }
    private void makeComputerMove() {
        if (currentCard == -1) {
            currentCard = drawCard();
            if (currentCard == -1) {
                JOptionPane.showMessageDialog(this, "");
                return;
            }
        }
        int bestRow = -1;
        int bestCol = -1;
        int bestScore = Integer.MIN_VALUE;

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (grid[i][j] == 0) {
                    grid[i][j] = currentCard;
                    int score = calculateScore();
                    if (score > bestScore) {
                        bestScore = score;
                        bestRow = i;
                        bestCol = j;
                    }
                    grid[i][j] = 0;
                }
            }
        }
        if (bestRow != -1 && bestCol != -1) {
            placeCard(bestRow, bestCol, currentCard);
            currentCard = -1;
            drawButton.setEnabled(true);
        }
    }
    private void endGame() {
        String winner = playerScore > computerScore ? "Игрок" : (playerScore < computerScore ? "Компьютер" : "It's a tie");
        JOptionPane.showMessageDialog(this,
                "Игрв Окончена!\n" +
                        "Счет игрока: " + playerScore + "\n" +
                        "Счет компьютера: " + computerScore + "\n" +
                        "Победитель: " + winner
        );
        drawButton.setEnabled(false);
        computerMoveButton.setEnabled(false);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MatematikoGame().setVisible(true);
            }
        });
    }
}

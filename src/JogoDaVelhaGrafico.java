import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JogoDaVelhaGrafico extends JFrame implements ActionListener {
	public JButton[][] botoes;
	public char[][] tabuleiro;
	public char jogadorAtual;

	public JogoDaVelhaGrafico() {
		super("Jogo da Velha");
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Define o layout como uma matriz 3x3
		setLayout(new GridLayout(3, 3));

		// Cria os botões
		botoes = new JButton[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				botoes[i][j] = new JButton("");
				botoes[i][j].setFont(new Font("Arial", Font.PLAIN, 50));
				botoes[i][j].addActionListener(this);
				add(botoes[i][j]);
			}
		}

		// Inicia o jogo
		iniciarJogo();
	}

	public void iniciarJogo() {
		// Inicializa o tabuleiro
		tabuleiro = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				tabuleiro[i][j] = '-';
			}
		}

		// Define o jogador atual
		jogadorAtual = 'X';
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton botao = (JButton) e.getSource();
		int linha = -1, coluna = -1;

		// Procura o botão clicado no array de botões
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (botao == botoes[i][j]) {
					linha = i;
					coluna = j;
					break;
				}
			}
		}

		// Verifica se a posição clicada é válida
		if (tabuleiro[linha][coluna] != '-') {
			JOptionPane.showMessageDialog(this, "Posição inválida!");
			return;
		}

		// Marca a posição clicada no tabuleiro
		tabuleiro[linha][coluna] = jogadorAtual;
		botao.setText("" + jogadorAtual);

		// Verifica se houve vencedor ou empate
		if (verificaVencedor() || verificaEmpate()) {
			int resposta = JOptionPane.showConfirmDialog(this, "Deseja jogar novamente?", "Jogo da Velha",
					JOptionPane.YES_NO_OPTION);
			if (resposta == JOptionPane.YES_OPTION) {
				iniciarJogo();
				limparBotoes();
			} else {
				dispose();
			}
		} else {
			alterarJogador();
		}
	}

	private boolean verificaVencedor() {
		// Verifica as linhas
		for (int i = 0; i < 3; i++) {
			if (tabuleiro[i][0] == jogadorAtual && tabuleiro[i][1] == jogadorAtual && tabuleiro[i][2] == jogadorAtual) {
				return true;
			}
		}
		// Verifica as colunas
		for (int j = 0; j < 3; j++) {
			if (tabuleiro[0][j] == jogadorAtual && tabuleiro[1][j] == jogadorAtual && tabuleiro[2][j] == jogadorAtual) {
				return true;
			}
		}
		// Verifica a diagonal principal
		if (tabuleiro[0][0] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][2] == jogadorAtual) {
			return true;
		}
		// Verifica a diagonal secundária
		if (tabuleiro[0][2] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][0] == jogadorAtual) {
			return true;
		}
		return false;
	}

	public boolean verificaEmpate() {
		// Verifica se todos as posições foram preenchidas
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (tabuleiro[i][j] == '-') {
					return false;
				}
			}
		}

		JOptionPane.showMessageDialog(this, "Empate!");
		return true;
	}

	public void alterarJogador() {
		if (jogadorAtual == 'X') {
			jogadorAtual = 'O';
		} else {
			jogadorAtual = 'X';
		}
	}

	public void limparBotoes() {
		// Limpa o texto dos botões
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				botoes[i][j].setText("");
			}
		}
	}

}

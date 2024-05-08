import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Criar uma instância da classe TelaTeste
                TelaTeste tela = new TelaTeste();

                // Configurar a janela
                JFrame frame = new JFrame("Teste da Tela");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(tela.painelPrincipal);
                frame.pack();
                frame.setLocationRelativeTo(null); // Centralizar na tela
                frame.setVisible(true);
            }
        });

    }
}

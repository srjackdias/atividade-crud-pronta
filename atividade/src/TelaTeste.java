import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaTeste extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel txtNome;
    private JLabel txtAltura;
    private JLabel txtPeso;
    private JLabel txtPerfil;
    private JButton bntCadastrar;
    private JButton bntLocalizar;
    private JButton bntEditar;
    private JButton bntApagar;
    private JButton bntMostrar;
    private JButton bntFake;
    private JTextArea textArea;
    private JPanel painelBotoes;
    public JPanel painelPrincipal;
    private ArrayList<Object[]> pessoas = new ArrayList<>();

    public TelaTeste() {
        setTitle("Cadastro de Pessoas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        painelPrincipal = new JPanel(new BorderLayout()); // Usando BorderLayout
        JPanel painelCampos = new JPanel(new GridLayout(10, 2, 10, 10)); // GridLayout com 3 linhas e 2 colunas

        txtNome = new JLabel("Nome:");
        textField1 = new JTextField();
        txtAltura = new JLabel("Altura:");
        textField2 = new JTextField();
        txtPeso = new JLabel("Peso:");
        textField3 = new JTextField();

        painelCampos.add(txtNome);
        painelCampos.add(textField1);
        painelCampos.add(txtAltura);
        painelCampos.add(textField2);
        painelCampos.add(txtPeso);
        painelCampos.add(textField3);

        painelPrincipal.add(painelCampos, BorderLayout.NORTH); // Adicionando os campos ao painel principal na parte superior

        bntCadastrar = new JButton("Cadastrar");
        bntLocalizar = new JButton("Localizar");
        bntEditar = new JButton("Editar");
        bntApagar = new JButton("Apagar");
        bntMostrar = new JButton("Mostrar");
        bntFake = new JButton("Fake");

        painelBotoes = new JPanel(new GridBagLayout()); // Usando GridBagLayout para flexibilidade
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os botões

        painelBotoes.add(bntCadastrar, gbc);

        gbc.gridx = 1;
        painelBotoes.add(bntLocalizar, gbc);

        gbc.gridx = 2;
        painelBotoes.add(bntEditar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        painelBotoes.add(bntApagar, gbc);

        gbc.gridx = 1;
        painelBotoes.add(bntMostrar, gbc);

        gbc.gridx = 2;
        painelBotoes.add(bntFake, gbc);

        painelPrincipal.add(painelBotoes, BorderLayout.CENTER); // Adicionando os botões ao painel principal no centro

        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(380, 100)); // Definindo tamanho preferido da JTextArea
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        painelPrincipal.add(scrollPane, BorderLayout.SOUTH); // Adicionando a área de texto ao painel principal na parte inferior

        add(painelPrincipal);

        // ActionListener para o botão Cadastrar
        bntCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = textField1.getText();
                double altura = Double.parseDouble(textField2.getText());
                double peso = Double.parseDouble(textField3.getText());
                Object[] novaPessoa = {nome, altura, peso};
                pessoas.add(novaPessoa);
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso");
            }
        });

        // ActionListener para o botão Localizar
        bntLocalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomePesquisa = textField1.getText();
                double alturaPesquisa = Double.parseDouble(textField2.getText());
                double pesoPesquisa = Double.parseDouble(textField3.getText());
                boolean encontrado = false;

                for (Object[] pessoa : pessoas) {
                    String nome = (String) pessoa[0];
                    double altura = (double) pessoa[1];
                    double peso = (double) pessoa[2];

                    if (nome.equalsIgnoreCase(nomePesquisa) && altura == alturaPesquisa && peso == pesoPesquisa) {
                        textField1.setText(nome);
                        textField2.setText(String.valueOf(altura));
                        textField3.setText(String.valueOf(peso));
                        encontrado = true;
                        JOptionPane.showMessageDialog(null, "Registro encontrado");
                        return;
                    }
                }

                if (!encontrado) {
                    JOptionPane.showMessageDialog(null, "Registro não encontrado");
                }
            }
        });

        // ActionListener para o botão Editar
        bntEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeEdicao = JOptionPane.showInputDialog("Qual registro deseja editar?");
                String novoNome = JOptionPane.showInputDialog("Qual é o novo nome?");
                String novaAltura = JOptionPane.showInputDialog("Qual é a nova altura?");
                String novoPeso = JOptionPane.showInputDialog("Qual é o novo peso?");

                if (nomeEdicao != null && novoNome != null && novaAltura != null && novoPeso != null) {
                    for (Object[] pessoa : pessoas) {
                        String nome = (String) pessoa[0];

                        if (nome.equals(nomeEdicao)) {
                            double alturaOriginal = (Double) pessoa[1];
                            double pesoOriginal = (Double) pessoa[2];
                            double novaAlturaDouble = Double.parseDouble(novaAltura);
                            double novoPesoDouble = Double.parseDouble(novoPeso);

                            if (alturaOriginal != novaAlturaDouble || pesoOriginal != novoPesoDouble) {
                                pessoa[0] = novoNome;
                                pessoa[1] = novaAlturaDouble;
                                pessoa[2] = novoPesoDouble;

                                JOptionPane.showMessageDialog(null, "Edição realizada com sucesso");
                                return;
                            } else {
                                JOptionPane.showMessageDialog(null, "Os valores informados são iguais aos originais. Nenhuma alteração realizada.");
                                return;
                            }
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Registro não encontrado");
                }
            }
        });

        // ActionListener para o botão Apagar
        bntApagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeApagar = JOptionPane.showInputDialog("Qual registro deseja apagar?");

                if (nomeApagar != null) {
                    for (int i = 0; i < pessoas.size(); i++) {
                        String nome = (String) pessoas.get(i)[0];

                        if (nome.equals(nomeApagar)) {
                            pessoas.remove(i);
                            JOptionPane.showMessageDialog(null, "Registro apagado com sucesso");
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Registro não encontrado");
                }
            }
        });

        // ActionListener para o botão Mostrar
        bntMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                for (Object[] pessoa : pessoas) {
                    String nome = (String) pessoa[0];
                    double altura = (double) pessoa[1];
                    double peso = (double) pessoa[2];
                    textArea.append("Nome: " + nome + ", Altura: " + altura + ", Peso: " + peso + "\n");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TelaTeste tela = new TelaTeste();
                tela.setVisible(true);
            }
        });
    }
}

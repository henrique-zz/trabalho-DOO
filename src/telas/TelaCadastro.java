package telas;

import exceptions.CampoNuloException;
import metodos.LimitadorCaractere;
import modelos.Usuario;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class TelaCadastro extends JFrame implements ActionListener, TelasInterface {
    Usuario usuario = new Usuario();
    JButton botaoSim, botaoContinuar;
    JTextField nomeField, cpfField, emailField;

    public TelaCadastro(){
        tela();
    }

    @Override
    public void tela() {
        this.setTitle("Cadastro");
        this.setSize(540, 260);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(criaPanel(), BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public JPanel criaPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);

        nomeField = new JTextField();

        cpfField = new JTextField();
        cpfField.setDocument(new LimitadorCaractere(11)); //limita a digitação de caracteres do cpf para 11, além de tirar caracteres não númericos
        emailField = new JTextField();

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(0, 2));
        panel2.setBackground(Color.decode("#F4A100"));

        botaoSim = new JButton("Sim - Continuar");
        botaoSim.addActionListener(this);
        botaoContinuar = new JButton("Não - Continuar");
        botaoContinuar.addActionListener(this);

        panel2.add(new JLabel("Digite o seu nome: "));
        panel2.add(nomeField);
        panel2.add(new JLabel("Digite o seu CPF: "));
        panel2.add(cpfField);
        panel2.add(new JLabel("Você deseja inserir seu e-mail e/ou telefone?"));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.decode("#F4A100"));

        buttonPanel.add(botaoSim);
        buttonPanel.add(botaoContinuar);

        panel2.add(buttonPanel);

        panel.add(panel2);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoSim) {
            verificaCamposNulos();

            this.dispose();

            usuario.setNome(nomeField.getText());
            usuario.setCpf(cpfField.getText());

            String[] opcoes = {"e-mail", "telefone", "ambos"};
            JComboBox<String> opcoesCadastro = new JComboBox<>(opcoes);
            int verificacao = telaCadastroSeSim(opcoesCadastro);

            if (verificacao == JOptionPane.OK_OPTION) {
                int opcao = opcoesCadastro.getSelectedIndex();

                switch (opcao) {
                    case 0: //somente email
                        usuario.setEmail(JOptionPane.showInputDialog("Digite o seu email: "));
                        break;

                    case 1: //somente telefone
                        usuario.setTelefone(textoTelefoneFormatado());
                        break;

                    case 2: //ambos
                        usuario.setEmail(JOptionPane.showInputDialog("Digite o seu email: "));
                        usuario.setTelefone(textoTelefoneFormatado());
                        break;
                }

                new TelaInicialPrograma(usuario).tela();
                //cria o TelaInicialPrograma com os dados e chama o método tela

            }

        }


        if (e.getSource() == botaoContinuar) {
            verificaCamposNulos();

            this.dispose();

            usuario.setNome(nomeField.getText());
            usuario.setCpf(cpfField.getText());

            new TelaInicialPrograma(usuario).tela();
            //cria o TelaInicialPrograma com os dados e chama o método tela

        }

    }

    private void verificaCamposNulos(){
        if (nomeField.getText().isEmpty() || cpfField.getText().isEmpty()) { //verifica se o usuário digitou os dois campos requisitados antes de apertar os botões
            try {
                this.dispose();

                JLabel labelException = new JLabel("Erro: o nome e/ou o CPF não foram inseridos");
                labelException.setFont(new Font("Arial", Font.BOLD, 18));
                labelException.setForeground(Color.red);

                JPanel panelException = new JPanel();
                panelException.setSize(450,100);
                panelException.setBackground(Color.BLACK);
                panelException.add(labelException);
                
                JFrame frameException = new JFrame("Erro");
                frameException.add(panelException);
                frameException.setSize(450,100);
                frameException.setDefaultCloseOperation(EXIT_ON_CLOSE);
                frameException.setLocationRelativeTo(null);
                frameException.setVisible(true);

                throw new CampoNuloException("ERRO");
            } catch (CampoNuloException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private String textoTelefoneFormatado() { //é um método que além de limitar o número de caracteres do telefone, cria uma formatação para digitarmos
        try {
            MaskFormatter telefoneFormatter = new MaskFormatter("(##) #####-####"); //aqui temos a formatação de (##) #####-#### para o usuário digitar, separando o DDD nos parentêses e o número dividido pelo hífen após o parentêses
            telefoneFormatter.setPlaceholderCharacter('_');
            JFormattedTextField telefoneField = new JFormattedTextField(telefoneFormatter);
            int opcao = JOptionPane.showConfirmDialog(null, telefoneField, "Digite o seu telefone", JOptionPane.OK_CANCEL_OPTION);

            if(opcao == JOptionPane.CANCEL_OPTION || opcao == JOptionPane.CLOSED_OPTION){ //caso o usuário clique em "cancelar" ou fechar a janela o telefone virará nulo
                return null;
            } else { //caso o usuário clique em "OK"
                return telefoneField.getText();
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int telaCadastroSeSim(JComboBox<String> opcoesCadastro) {
        JPanel panelCadastro = new JPanel();
        panelCadastro.setBackground(Color.LIGHT_GRAY);
        panelCadastro.add(new JLabel("Qual das opções você deseja?"));
        panelCadastro.add(opcoesCadastro);
        return JOptionPane.showOptionDialog(null, panelCadastro, "Identificação do Usuário"
                , JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
    }

}

package telas;

import exceptions.CampoNuloException;
import modelos.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicialPrograma extends JFrame implements TelasInterface, ActionListener {
    private Usuario usuario;
    private String nome, cpf, email,telefone;
    private JButton botaoPerfil, botaoTelaMotoristas, botaoSair, botaoTrabalheConosco, botaoDeslogar, botaoVoltar;
    private JFrame frameTrabalheConosco;

    public TelaInicialPrograma(Usuario usuario){
        this.usuario = usuario;

        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
        this.email = usuario.getEmail();
        this.telefone = usuario.getTelefone();
    }

    public void tela() {
        this.setTitle("Tela Inicial");
        this.setSize(240, 330);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10); //cria espaçamento entre os botões

        botaoPerfil = criaBotao("Exibir Perfil");
        botaoTelaMotoristas = criaBotao("Ver Motoristas");
        botaoTrabalheConosco = criaBotao("Trabalhe Conosco");
        botaoDeslogar = criaBotao("Encerrar sessão");
        botaoSair = criaBotao("Sair do Programa");

        gbc.gridy = 0; //linha 0
        this.add(criaImagem(), gbc);

        gbc.gridy = 1; //linha 1
        this.add(botaoPerfil,gbc);

        gbc.gridy = 2; //linha 2
        this.add(botaoTelaMotoristas, gbc);

        gbc.gridy = 3; //linha 3
        this.add(botaoTrabalheConosco, gbc);

        gbc.gridy = 4; //linha 4
        this.add(botaoDeslogar, gbc);

        gbc.gridy = 5; //linha 5
        this.add(botaoSair, gbc);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private JButton criaBotao(String tituloBotao){
        JButton botao = new JButton(tituloBotao);
        botao.setSize(100,100);
        botao.addActionListener(this);
        return botao;
    }

    private JLabel criaImagem(){
        ImageIcon icon = new ImageIcon("src/icone.png");
        Image imagemMenor = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); //faz a imagem ter 40x40 de tamanho
        icon = new ImageIcon(imagemMenor);
        return new JLabel(icon);
    }

    private void telaTrabalheConosco(){
        frameTrabalheConosco= new JFrame("Trabalhe Conosco");
        frameTrabalheConosco.setSize(620,120);
        frameTrabalheConosco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel mensagem = new JLabel("Desculpe, esta versão do programa é apenas para clientes.");
        mensagem.setFont(new Font("Arial", Font.BOLD, 21));

        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#F4A100"));
        panel.add(mensagem);

        botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(this);
        panel.add(botaoVoltar);

        frameTrabalheConosco.add(panel);
        frameTrabalheConosco.setLocationRelativeTo(null);
        frameTrabalheConosco.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botaoPerfil){
            this.dispose();
            new TelaPerfilUsuario(usuario).tela();
        }

        if(e.getSource() == botaoTelaMotoristas){
            this.dispose();
            new TelaMotoristas(usuario).tela();
        }

        if(e.getSource() == botaoTrabalheConosco){
            this.dispose();
            telaTrabalheConosco();
        }

        if(e.getSource() == botaoDeslogar){
            this.dispose();
            new TelaCadastro(); //volta pra tela de cadastro
        }

        if(e.getSource() == botaoSair){
            this.dispose();
            System.exit(0);
        }

        if(e.getSource() == botaoVoltar){
            frameTrabalheConosco.dispose();
            new TelaInicialPrograma(usuario).tela();
        }
    }
}

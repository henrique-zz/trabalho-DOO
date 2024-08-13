package telas;

import exceptions.CampoNuloException;
import modelos.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPerfilUsuario extends JFrame implements TelasInterface, ActionListener {
    private Usuario usuario;
    private String nome, cpf, email,telefone;
    private JButton botaoVoltar;

    public TelaPerfilUsuario(Usuario usuario) {
        this.usuario = usuario;

        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
        this.email = usuario.getEmail();
        this.telefone = usuario.getTelefone();
    }

    @Override
    public void tela() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.decode("#F4A100"));

        JLabel dadosUsuario = new JLabel("Dados do usuário");
        dadosUsuario.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(dadosUsuario);

        panel.add(new JLabel("Nome: " +nome));
        panel.add(new JLabel("CPF: " +cpf));

        if(email != null){ //se o usuário não pediu para colocar o email, não aparece nada pois estará nulo
            panel.add(new JLabel("Email: " +email));
        }

        if(telefone != null){
            panel.add(new JLabel("Telefone: " +telefone));
        }

        botaoVoltar = new JButton("Voltar");
        botaoVoltar.setPreferredSize(new Dimension(45,45));
        botaoVoltar.addActionListener(this);

        this.setTitle("Perfil Usuário");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER); //adiciona o panel no meio
        this.add(botaoVoltar, BorderLayout.SOUTH); //adiciona o botao embaixo
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botaoVoltar){
            this.dispose();
            new TelaInicialPrograma(usuario).tela();
        }
    }
}

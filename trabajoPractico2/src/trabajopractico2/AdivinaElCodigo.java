package trabajopractico2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AdivinaElCodigo extends JFrame {
    private String codigoSecreto;
    private int intentos = 0;
    
    private JTextField jTextField1;
    private JPasswordField jPasswordField1, jPasswordField2, jPasswordField3;
    private JButton jButton1;
    private JLabel jLabel1, jLabel2;

    public AdivinaElCodigo() {
        setTitle("Adivina el Código");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        jPasswordField1 = new JPasswordField("$");
        jPasswordField2 = new JPasswordField("$");
        jPasswordField3 = new JPasswordField("$");
        
        jPasswordField1.setBounds(50, 30, 80, 30);
        jPasswordField2.setBounds(150, 30, 80, 30);
        jPasswordField3.setBounds(250, 30, 80, 30);
        
        jPasswordField1.setEchoChar((char) 0);
        jPasswordField2.setEchoChar((char) 0);
        jPasswordField3.setEchoChar((char) 0);
        jPasswordField1.setEditable(false);
        jPasswordField2.setEditable(false);
        jPasswordField3.setEditable(false);

        add(jPasswordField1);
        add(jPasswordField2);
        add(jPasswordField3);

        jLabel1 = new JLabel("¡Adivina el código! Ingresá 3 números y presioná Enter.");
        jLabel1.setBounds(50, 90, 350, 30);
        add(jLabel1);

        jTextField1 = new JTextField();
        jTextField1.setBounds(50, 140, 150, 30);
        add(jTextField1);

        jButton1 = new JButton("Ayuda");
        jButton1.setBounds(230, 140, 100, 30);
        add(jButton1);

        jLabel2 = new JLabel();
        jLabel2.setBounds(180, 190, 80, 80);
        add(jLabel2);

        jTextField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                validarIntento();
            }
        });

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                JOptionPane.showMessageDialog(AdivinaElCodigo.this, "Pista: El código está entre 100 y 999.");
            }
        });

        iniciarJuego();
    }

    private void iniciarJuego() {
        Random random = new Random();
        int numeroAleatorio = random.nextInt(900) + 100;
        codigoSecreto = String.valueOf(numeroAleatorio);
        
        jPasswordField1.setText("$");
        jPasswordField2.setText("$");
        jPasswordField3.setText("$");
        
        intentos = 0;
        jLabel1.setText("¡Adivina el código! Ingresá 3 números y presioná Enter.");
        jTextField1.setText("");
        jLabel2.setIcon(null);
    }

    private void validarIntento() {
        String intentoTexto = jTextField1.getText().trim();

        if (!intentoTexto.matches("\\d{3}")) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa exactamente 3 números.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        intentos++;
        int numIngresado = Integer.parseInt(intentoTexto);
        int numSecreto = Integer.parseInt(codigoSecreto);

        if (numIngresado == numSecreto) {
            jPasswordField1.setText(String.valueOf(codigoSecreto.charAt(0)));
            jPasswordField2.setText(String.valueOf(codigoSecreto.charAt(1)));
            jPasswordField3.setText(String.valueOf(codigoSecreto.charAt(2)));
            
            jLabel1.setText("¡Felicitaciones! Adivinaste en " + intentos + " intentos.");
            cargarTrofeo();
            
            int opcion = JOptionPane.showConfirmDialog(this, "¿Querés jugar otra vez?", "¡Ganaste!", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                iniciarJuego();
            }
        } else if (numIngresado < numSecreto) {
            jLabel1.setText("Intento #" + intentos + ": El número secreto es MÁS ALTO 📈");
        } else {
            jLabel1.setText("Intento #" + intentos + ": El número secreto es MÁS BAJO 📉");
        }

        jTextField1.setText("");
    }

    public void cargarTrofeo() {
        try {
            java.net.URL url = new java.net.URL("https://cdn-icons-png.flaticon.com/512/3112/3112946.png");
            ImageIcon icon = new ImageIcon(url);
            Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            jLabel2.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdivinaElCodigo().setVisible(true);
            }
        });
    }
}
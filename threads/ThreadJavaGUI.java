/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadjava;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.border.TitledBorder;

public class ThreadJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Cria dois objetos com Threads
        Processing p = new Processing();
        LoadingGUI lg = new LoadingGUI(p);
        
        //Inicia os dois objetos
        p.start();
        lg.start();
    }

}

class Processing extends Thread {

    //Variavel contadora do estado do processamento
    int p;
    
    @Override
    public void run() {
        //Faz o processamento
        for (p = 0; p < 100; p++) {
            try {
                Thread.sleep(1000);
            } catch (Exception ex) {
            }
        }
    }
    
    //Retorna o valor do estado do processamento
    int get_status(){
        return p;
    }
}

class LoadingGUI extends Thread {

    Processing proc_status;

    //Construtor, inicializa a objeto proc_status
    public LoadingGUI(Processing proc_st) {
        proc_status = proc_st;
    }
    
    //Constroi um simples progressBar
    @Override
    public void run() {
        //Cria um Frame que possui uma barra de progresso
        JFrame f = new JFrame("");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container content = f.getContentPane();
        JProgressBar progressBar = new JProgressBar();
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        TitledBorder border = BorderFactory.createTitledBorder("Processing...");
        progressBar.setBorder(border);
        content.add(progressBar, BorderLayout.NORTH);
        f.setSize(300, 100);
        f.setVisible(true);
        
        //Enquanto a thread proc_status esta viva, atualize o valor da barra de progresso
        while(proc_status.isAlive()){
            progressBar.setValue(proc_status.get_status());
            f.repaint();
        }
        
        f.dispose();
        
    }
}

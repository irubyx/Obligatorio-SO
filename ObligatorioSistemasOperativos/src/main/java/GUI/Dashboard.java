/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import com.mycompany.obligatoriosistemasoperativos.*;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nmais
 */
public class Dashboard extends javax.swing.JFrame {

    public static Scheduler scheduler;
    static DefaultTableModel modeloProcesos;
    static DefaultTableModel modeloNucleos;
    private static final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        initComponents();
        setearModeloTablaNucelos();
        setearModeloTablaProcesos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaNucleos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProcesos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnCargarArchivo = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        dashboardMenu = new javax.swing.JMenu();
        estadisticasMenu = new javax.swing.JMenu();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tablaNucleos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaNucleos);

        tablaProcesos.setBackground(new java.awt.Color(215, 247, 253));
        tablaProcesos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tablaProcesos);

        jLabel2.setText("Procesos");

        jLabel3.setText("Nucleos");

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnCargarArchivo.setText("Agregar Desde Archivo");
        btnCargarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarArchivoActionPerformed(evt);
            }
        });

        dashboardMenu.setText("Dashboard");
        dashboardMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardMenuActionPerformed(evt);
            }
        });
        jMenuBar1.add(dashboardMenu);

        estadisticasMenu.setText("Estadisticas");
        estadisticasMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                estadisticasMenuMouseClicked(evt);
            }
        });
        estadisticasMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estadisticasMenuActionPerformed(evt);
            }
        });
        jMenuBar1.add(estadisticasMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnCargarArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnModificar)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(113, 113, 113))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnModificar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCargarArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        new AgregarProceso().setVisible(true);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        scheduler = Scheduler.GetInstance();
        Hardware hardware = Hardware.getInstance();
        scheduler.BindHardware(hardware);
        scheduler.Start();
        inicializarProcesosIniciales();
        cargarTablas();
        final DashboardUpdate dashboardUpdate = new DashboardUpdate(this);
        executor.scheduleAtFixedRate(dashboardUpdate, 1000, 1000, TimeUnit.MILLISECONDS);
    }//GEN-LAST:event_formWindowOpened

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        cargarTablas();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        new ModificarProceso().setVisible(true);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnCargarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarArchivoActionPerformed
        cargarProcesosArchivo();
    }//GEN-LAST:event_btnCargarArchivoActionPerformed

    private void dashboardMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardMenuActionPerformed

    }//GEN-LAST:event_dashboardMenuActionPerformed

    private void estadisticasMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estadisticasMenuActionPerformed
        this.setVisible(false);
        new Estadisticas().setVisible(true);
    }//GEN-LAST:event_estadisticasMenuActionPerformed

    private void estadisticasMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_estadisticasMenuMouseClicked
        this.setVisible(false);
        new Estadisticas().setVisible(true);
    }//GEN-LAST:event_estadisticasMenuMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Dashboard pantalla = new Dashboard();
                pantalla.setVisible(true);

            }
        });
    }

    private static void inicializarProcesosIniciales() {
        Program p1 = new Program("System", 0, 50, 10, 13);
        Program p2 = new Program("Secure System", 0, 100, 20, 20);
        Program p3 = new Program("Registry", 0, 20, 5, 10);
        scheduler.RunProgram(p1, 1, 0);
        scheduler.RunProgram(p2, 1, 0);
        scheduler.RunProgram(p3, 1, 0);
    }

    public void cargarTablas() {
        cargarTablaProcesos();
        cargarTablaNucleos();
    }

    private void cargarTablaNucleos() {
        vaciarTablaNucleos();
        String[] texto = new String[2];
        Core[] nucleos = scheduler.getCores();
        for (Core nucleo : nucleos) {
            texto[0] = String.valueOf(nucleo.getCoreId());
            if (nucleo.getRunningPCB() == null) {
                texto[1] = "vacio";
            } else {
                texto[1] = String.valueOf(nucleo.getRunningPCB().Program.Name);
            }
            modeloNucleos.addRow(texto);
        }
        modeloNucleos.fireTableDataChanged();
    }

    private void setearModeloTablaNucelos() {
        modeloNucleos = new DefaultTableModel();
        modeloNucleos.addColumn("ID Nucleo");
        modeloNucleos.addColumn("Proceso");

        this.tablaNucleos.setModel(modeloNucleos);
    }

    private void setearModeloTablaProcesos() {
        modeloProcesos = new DefaultTableModel();
        modeloProcesos.addColumn("ID");
        modeloProcesos.addColumn("Nombre");
        modeloProcesos.addColumn("Estado");
        modeloProcesos.addColumn("Prioridad");

        //modeloProcesos.addColumn("RAM (MB)");
        this.tablaProcesos.setModel(modeloProcesos);
    }

    private void vaciarTablaNucleos() {
        for (int i = 0; i < tablaNucleos.getRowCount(); i++) {
            modeloNucleos.removeRow(i);
            i -= 1;
        }
    }

    private void cargarTablaProcesos() {
        vaciarTablaProcesos();
        String[] texto = new String[4];
        LinkedList<ProcessDetail> procesos = scheduler.GetAllProcesses();
        //LinkedList<PCB> procesos =scheduler.GetProcesses(); 
        for (ProcessDetail p : procesos) {
            texto[0] = String.valueOf(p.PID);
            texto[1] = String.valueOf(p.Name);
            texto[2] = String.valueOf(p.State);
            texto[3] = String.valueOf(p.Priority);
            //texto[4] = String.valueOf(p.getRAM());
            modeloProcesos.addRow(texto);
        }
        modeloProcesos.fireTableDataChanged();
    }

    private void vaciarTablaProcesos() {
        for (int i = 0; i < tablaProcesos.getRowCount(); i++) {
            modeloProcesos.removeRow(i);
            i -= 1;
        }
    }

    private void cargarProcesosArchivo() {
        String[] lineas = ManejadorArchivosGenerico.leerArchivo("src/procesos.txt");
        for (int i = 1; i < lineas.length; i++) //La linea 0 tiene la estructura a seguir
        {
            String[] partesProceso = lineas[i].split(",");
            Program prog = new Program(partesProceso[0], Integer.valueOf(partesProceso[1]), Integer.valueOf(partesProceso[2]), Integer.valueOf(partesProceso[3]), Integer.valueOf(partesProceso[4]));
            scheduler.RunProgram(prog, Integer.valueOf(partesProceso[5]), Integer.valueOf(partesProceso[6]));
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCargarArchivo;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JMenu dashboardMenu;
    private javax.swing.JMenu estadisticasMenu;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaNucleos;
    private javax.swing.JTable tablaProcesos;
    // End of variables declaration//GEN-END:variables
}

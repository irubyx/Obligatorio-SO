/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import com.mycompany.proyectososegundaentrega.*;
import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JTable;
import javax.swing.UIManager;
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

        /*
        
        tablaProcesos.getTableHeader().setFont(new Font("Lucida Sans",Font.BOLD,14));
        tablaProcesos.getTableHeader().setOpaque(false);
        tablaProcesos.getTableHeader().setBackground(Color.black);
        tablaProcesos.getTableHeader().setForeground(new Color(0,51,51));
         */
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaNucleos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProcesos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnCargarArchivo = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        dashboardMenu = new javax.swing.JMenu();
        estadisticasMenu = new javax.swing.JMenu();

        jButton1.setText("jButton1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(215, 235, 235));

        jPanel2.setBackground(new java.awt.Color(109, 172, 167));

        tablaNucleos.setBackground(new java.awt.Color(0, 102, 102));
        tablaNucleos.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        tablaNucleos.setForeground(new java.awt.Color(255, 255, 255));
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
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaNucleos.setFocusable(false);
        tablaNucleos.setGridColor(new java.awt.Color(84, 147, 147));
        tablaNucleos.setRowHeight(30);
        tablaNucleos.setSelectionBackground(new java.awt.Color(255, 204, 102));
        tablaNucleos.setSelectionForeground(new java.awt.Color(255, 153, 0));
        tablaNucleos.setShowHorizontalLines(true);
        tablaNucleos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaNucleos);

        tablaProcesos.setBackground(new java.awt.Color(0, 102, 102));
        tablaProcesos.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        tablaProcesos.setForeground(new java.awt.Color(255, 255, 255));
        tablaProcesos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProcesos.setFocusable(false);
        tablaProcesos.setGridColor(new java.awt.Color(83, 141, 141));
        tablaProcesos.setRowHeight(30);
        tablaProcesos.setSelectionBackground(new java.awt.Color(255, 204, 102));
        tablaProcesos.setSelectionForeground(new java.awt.Color(255, 153, 0));
        tablaProcesos.setShowHorizontalLines(true);
        tablaProcesos.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tablaProcesos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Lucida Sans", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("Núcleos");

        jLabel3.setFont(new java.awt.Font("Lucida Sans", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setText("Procesos");

        btnCargarArchivo.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        btnCargarArchivo.setForeground(new java.awt.Color(0, 102, 102));
        btnCargarArchivo.setText("Agregar desde archivo");
        btnCargarArchivo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 102), new java.awt.Color(255, 102, 51), new java.awt.Color(250, 165, 108), new java.awt.Color(255, 153, 102)));
        btnCargarArchivo.setFocusTraversalPolicyProvider(true);
        btnCargarArchivo.setMargin(new java.awt.Insets(2, 89, 2, 14));
        btnCargarArchivo.setMinimumSize(new java.awt.Dimension(70, 89));
        btnCargarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarArchivoActionPerformed(evt);
            }
        });

        btnAgregar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(0, 102, 102));
        btnAgregar.setText("Agregar");
        btnAgregar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 102), new java.awt.Color(255, 102, 51), new java.awt.Color(250, 165, 108), new java.awt.Color(255, 153, 102)));
        btnAgregar.setFocusTraversalPolicyProvider(true);
        btnAgregar.setMargin(new java.awt.Insets(2, 89, 2, 14));
        btnAgregar.setMinimumSize(new java.awt.Dimension(70, 89));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(0, 102, 102));
        btnModificar.setText("Modificar");
        btnModificar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 102), new java.awt.Color(255, 102, 51), new java.awt.Color(250, 165, 108), new java.awt.Color(255, 153, 102)));
        btnModificar.setFocusTraversalPolicyProvider(true);
        btnModificar.setMargin(new java.awt.Insets(2, 89, 2, 14));
        btnModificar.setMinimumSize(new java.awt.Dimension(70, 89));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(240, 240, 240)
                .addComponent(jLabel1))
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(btnCargarArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(btnCargarArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenuBar1.setBackground(new java.awt.Color(0, 51, 51));
        jMenuBar1.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N

        dashboardMenu.setText("Dashboard");
        dashboardMenu.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        dashboardMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardMenuActionPerformed(evt);
            }
        });
        jMenuBar1.add(dashboardMenu);

        estadisticasMenu.setForeground(new java.awt.Color(0, 51, 51));
        estadisticasMenu.setText("Estadisticas");
        estadisticasMenu.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
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
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        new ModificarProceso().setVisible(true);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnCargarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarArchivoActionPerformed
        cargarProcesosArchivo();
    }//GEN-LAST:event_btnCargarArchivoActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        new AgregarProceso().setVisible(true);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void dashboardMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardMenuActionPerformed

    }//GEN-LAST:event_dashboardMenuActionPerformed

    private void estadisticasMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_estadisticasMenuMouseClicked
        this.setVisible(false);
        new Estadisticas().setVisible(true);
    }//GEN-LAST:event_estadisticasMenuMouseClicked

    private void estadisticasMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estadisticasMenuActionPerformed
        this.setVisible(false);
        new Estadisticas().setVisible(true);
    }//GEN-LAST:event_estadisticasMenuActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        scheduler = Scheduler.GetInstance();
        Hardware hardware = Hardware.getInstance();
        if (hardware != scheduler.getHardware()) {
            scheduler.BindHardware(hardware);
            scheduler.Start();
            inicializarProcesosIniciales();
        }
        cargarTablas();
        final DashboardUpdate dashboardUpdate = new DashboardUpdate(this);
        executor.scheduleAtFixedRate(dashboardUpdate, 1000, 1000, TimeUnit.MILLISECONDS);    }//GEN-LAST:event_formWindowOpened

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
                new Dashboard().setVisible(true);

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

    private void setearModeloTablaNucelos() {
        modeloNucleos = new DefaultTableModel();
        setearEstilo(tablaNucleos);
        modeloNucleos.addColumn("ID Nucleo");
        modeloNucleos.addColumn("Proceso");

        this.tablaNucleos.setModel(modeloNucleos);
    }

    private void setearModeloTablaProcesos() {
        modeloProcesos = new DefaultTableModel();
        setearEstilo(tablaProcesos);
        modeloProcesos.addColumn("ID");
        modeloProcesos.addColumn("Nombre");
        modeloProcesos.addColumn("Estado");
        modeloProcesos.addColumn("Prioridad");

        //modeloProcesos.addColumn("RAM (MB)");
        this.tablaProcesos.setModel(modeloProcesos);
    }

    private void setearEstilo(JTable pTalba) {
        pTalba.getTableHeader().setFont(new Font("Lucida Sans", Font.BOLD, 14));
        pTalba.getTableHeader().setOpaque(false);
        pTalba.getTableHeader().setBackground(Color.black);
        pTalba.getTableHeader().setForeground(new Color(0, 51, 51));
        UIManager.put("nimbusBlueGrey", new Color(215, 235, 235));
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCargarArchivo;
    private javax.swing.JButton btnModificar;
    private javax.swing.JMenu dashboardMenu;
    private javax.swing.JMenu estadisticasMenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaNucleos;
    private javax.swing.JTable tablaProcesos;
    // End of variables declaration//GEN-END:variables
}

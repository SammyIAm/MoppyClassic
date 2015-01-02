/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moppydesk.ui;

import moppydesk.inputs.MoppySequencer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.Transmitter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.Timer;
import moppydesk.*;

/**
 *
 * @author Sam
 */
public class SequencerControls extends InputPanel implements MoppyStatusConsumer {

    MoppySequencer seq;
    MoppyControlWindow controlWindow;
    MoppyUI app;
    final JFileChooser sequenceChooser = new JFileChooser();
    Timer progressTimer;
    Timer onStopTimer;
    private boolean isConnected = false;
    private boolean fileLoaded = false;

    /**
     * Creates new form SequencerControls
     */
    public SequencerControls(MoppyUI app, MoppyControlWindow mcw, MoppySequencer newSequencer) {
        this.seq = newSequencer;
        this.app = app;
        this.controlWindow = mcw;

        initComponents();
        autoResetCB.setSelected(app.prefs.getBoolean(Constants.PREF_AUTORESET, false));

        progressTimer = new Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                updateProgressDisplay();
            }
        });
        
        onStopTimer = new Timer(50, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                onSequencerStop();
            }
        });
    }

    private void onSequencerStop() {
        long currentSeconds = seq.getSecondsPosition();
        long totalSeconds = seq.getSecondsLength();
        if ((!seq.isRunning()) && (currentSeconds == totalSeconds)) {
        	currentSeconds = 0;
        	seq.resetSequencer();
            controlWindow.silenceDrives();
            if (autoResetCB.isSelected())
            {
                app.rm.reset();
            }
        }
    }
    
    private void updateProgressDisplay() {
        long currentSeconds = seq.getSecondsPosition();
        long totalSeconds = seq.getSecondsLength();
        sequenceProgressSlider.setValue((int) (currentSeconds));
        String currentPosition = String.format("%d:%02d",
                TimeUnit.SECONDS.toMinutes(currentSeconds),
                currentSeconds % 60);
        String totalPosition = String.format("%d:%02d",
                TimeUnit.SECONDS.toMinutes(totalSeconds),
                seq.getSecondsLength() % 60);
        currentPositionLabel.setText(currentPosition);
        totalPositionLabel.setText(totalPosition);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        sequenceNameLabel = new javax.swing.JLabel();
        bpmLabel = new javax.swing.JLabel();
        jSlider1 = new javax.swing.JSlider();
        startButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        sequenceProgressSlider = new javax.swing.JSlider();
        currentPositionLabel = new javax.swing.JLabel();
        totalPositionLabel = new javax.swing.JLabel();
        autoResetCB = new javax.swing.JCheckBox();

        jLabel1.setText("Current Sequence:");

        sequenceNameLabel.setText("<None loaded>");

        bpmLabel.setText(jSlider1.getValue() + " bpm");

        jSlider1.setMajorTickSpacing(10);
        jSlider1.setMaximum(310);
        jSlider1.setMinimum(20);
        jSlider1.setPaintTicks(true);
        jSlider1.setValue(120);
        jSlider1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jSlider1tempoSliderChanged(evt);
            }
        });

        startButton.setText("Start");
        startButton.setEnabled(false);
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonClicked(evt);
            }
        });

        stopButton.setText("Stop/Reset");
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonstopResetClicked(evt);
            }
        });

        loadButton.setText("Load Sequence");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonloadSequence(evt);
            }
        });

        sequenceProgressSlider.setToolTipText("");
        sequenceProgressSlider.setValue(0);
        sequenceProgressSlider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sequenceProgressDragged(evt);
            }
        });
        sequenceProgressSlider.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                sequenceProgressDragged(evt);
            }
        });

        currentPositionLabel.setText("00:00");

        totalPositionLabel.setText("00:00");

        autoResetCB.setText("Auto-reset drives on stop");
        autoResetCB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        autoResetCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoResetCBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sequenceNameLabel)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(loadButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(bpmLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stopButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(currentPositionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sequenceProgressSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(totalPositionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(autoResetCB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sequenceNameLabel))
                    .addComponent(loadButton))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bpmLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stopButton)
                            .addComponent(startButton))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(autoResetCB, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sequenceProgressSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(currentPositionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalPositionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jSlider1tempoSliderChanged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSlider1tempoSliderChanged
        JSlider s = (JSlider) evt.getSource();
        seq.setTempo(s.getValue());
        bpmLabel.setText(s.getValue() + " bpm");
    }//GEN-LAST:event_jSlider1tempoSliderChanged

    private void startButtonClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonClicked
        if (startButton.getText().equals("Start")) {
            playSequencer();
        } else {
            pauseSequencer();
        }
    }//GEN-LAST:event_startButtonClicked

    private void playSequencer() {
        seq.startSequencer();
        seq.setTempo(jSlider1.getValue());
        controlWindow.setStatus("Playing!");
        startButton.setText("Pause");
    }

    private void pauseSequencer() {
        seq.stopSequencer();
        controlWindow.silenceDrives();
        startButton.setText("Start");
        controlWindow.setStatus("...Paused");
    }

    private void stopResetSequencer() {
        if (seq.isRunning()) {
            controlWindow.setStatus("Stopping...");
            seq.stopSequencer();
            seq.resetSequencer();
            controlWindow.silenceDrives();
            if (autoResetCB.isSelected())
            {
                app.rm.reset();
            }
            startButton.setText("Start");
            controlWindow.setStatus("Stopped.");
        } else {
            seq.resetSequencer();
            app.rm.reset();
            controlWindow.setStatus("Reset.");
        }
    }

    private void stopButtonstopResetClicked(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonstopResetClicked
        stopResetSequencer();
    }//GEN-LAST:event_stopButtonstopResetClicked

    private void loadButtonloadSequence(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonloadSequence
        String previouslyLoaded = app.prefs.get(Constants.PREF_LOADED_SEQ, null);
        if (previouslyLoaded != null) {
            sequenceChooser.setCurrentDirectory(new File(previouslyLoaded));
        }
        int returnVal = sequenceChooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            loadSequenceFile(sequenceChooser.getSelectedFile());
        } else {
            //Cancelled
        }
    }//GEN-LAST:event_loadButtonloadSequence

    private void sequenceProgressDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sequenceProgressDragged
        int seconds = ((JSlider) evt.getSource()).getValue();
        seq.setSecondsPosition(seconds);
        currentPositionLabel.setText(String.format("%d:%02d",
                TimeUnit.SECONDS.toMinutes(seconds),
                seconds % 60));
    }//GEN-LAST:event_sequenceProgressDragged

    private void autoResetCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoResetCBActionPerformed
        app.prefs.putBoolean(Constants.PREF_AUTORESET, autoResetCB.isSelected());
    }//GEN-LAST:event_autoResetCBActionPerformed

    public void tempoChanged(int newTempo) {
        jSlider1.setValue(newTempo);
        bpmLabel.setText(newTempo + " bpm");
    }

    private void loadSequenceFile(File sequenceFile) {
        try {
            controlWindow.setStatus("Loading file...");
            seq.loadFile(sequenceFile.getPath());
            sequenceNameLabel.setText(sequenceFile.getName());
            sequenceProgressSlider.setMaximum((int) (seq.getSecondsLength()));
            app.prefs.put(Constants.PREF_LOADED_SEQ, sequenceFile.getPath());
            fileLoaded = true;
            controlWindow.setStatus("Loaded " + sequenceFile.getName());
            updateProgressDisplay();
            if (isConnected) {
                startButton.setEnabled(true);
            }
        } catch (Exception ex) {
            Logger.getLogger(MoppyControlWindow.class.getName()).log(Level.SEVERE, null, ex);
            controlWindow.setStatus("File loading error!");
            JOptionPane.showMessageDialog(this.getRootPane(), ex);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox autoResetCB;
    private javax.swing.JLabel bpmLabel;
    private javax.swing.JLabel currentPositionLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JButton loadButton;
    private javax.swing.JLabel sequenceNameLabel;
    private javax.swing.JSlider sequenceProgressSlider;
    private javax.swing.JButton startButton;
    private javax.swing.JButton stopButton;
    private javax.swing.JLabel totalPositionLabel;
    // End of variables declaration//GEN-END:variables

    public Transmitter getTransmitter() {
        return seq;
    }

    public void connected() {
        progressTimer.start();
        onStopTimer.start();
        isConnected = true;
        if (fileLoaded) {
            startButton.setEnabled(true);
        }
    }

    public void disconnected() {
        startButton.setEnabled(false);
        pauseSequencer();
        isConnected = false;
        progressTimer.stop();
        onStopTimer.stop();
        seq.setReceiver(null); //Clear receiver so there's no connection here.
    }
}

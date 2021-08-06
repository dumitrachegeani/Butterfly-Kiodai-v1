import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static javax.swing.SwingConstants.CENTER;

public class FereastraScor extends JFrame {

  JLabel scoreLabel = new JLabel("0");
  JTextField name = new JTextField();
  JButton saveButton = new JButton("SAVE");
  Integer score = 0;
  FileCreator file;
  // camp static, private de INSTANCE ce e initial null
  // Design pattern SINGLETONE
  private static FereastraScor INSTANCE = null;

  public FereastraScor() throws HeadlessException {
    setTitle("SCOR");
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    getContentPane().setBackground(new Color(0, 200, 0));
    setLayout(new GridLayout(3, 1));
    // method Factory (ai o metoda care tine loc de constructor cu avantajul ca are un nume mai explicativ)
    // si face creearea mai maleabila
    file = FileCreator.createCSVFile("scores");
    // doar exemplu"
    //FileCreator file2 = FileCreator.createTxtFile("fisier_text");

    scoreLabel.setFont(new Font("serif", Font.BOLD, 20));
    scoreLabel.setHorizontalAlignment(CENTER);
    scoreLabel.setSize(new Dimension(200, 100));

    // salveaza scorul si numele in fisier
    saveButton.addActionListener(
        e -> {
          try {
            saveScore();
          } catch (IOException ioException) {
            ioException.printStackTrace();
          }
        });
    saveButton.setSize(new Dimension(200, 100));

    name.setSize(new Dimension(200, 100));
    name.setHorizontalAlignment(CENTER);
    name.setAlignmentX(CENTER);

    // le adaugam pe toate pe fereastra
    add(scoreLabel);
    add(name);
    add(saveButton);

    // nelipsite din orice fereastra
    pack();
    setSize(new Dimension(300,400));
    setVisible(true);
  }

  // incremeteaza scorul - actualizeaza scorelabel
  public void incrementScore() {
    score++;
    scoreLabel.setText(score.toString());
  }

  // scrie un rand in fisierul respectiv (nume,scor,)
  public void saveScore() throws IOException {
    file.writeRow(name.getText(), score);
  }

  // verifica dac aexista deja o instanta a acestei clase (INSTANCE)
  // Daca nu exista (INSTANCE == NULL) o creeaza
  // daca exista o instanta deja DOAR O RETURNEAZA
  // nu pot exista mai multe instante pentru aceasta clasa, decat UNA!
  public static FereastraScor getINSTANCE() {
    if (INSTANCE == null) {
      INSTANCE = new FereastraScor();
    }
    return INSTANCE;
  }
}

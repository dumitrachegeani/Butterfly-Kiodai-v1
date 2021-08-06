// Design pattern Facade, ascunde implementarea de client
// Aceasta este efectiv interfata grafica (plus FereastraScor)

import javax.swing.*;
import java.awt.*;

public class Fereastra extends JFrame {
  // reprezinta fluturii
  private JLabel[][] labels;
  // variabila, reprezinta LxL patrate
  private int length;

  public Fereastra(String title, int length) throws HeadlessException {
    super(title);
    this.length = length;
    // initializam matricea de labeluri
    this.labels = new JLabel[length][length];

    Cale.setMatrixLength(length);

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new GridLayout(length, length ));

    // aici pentru fiecare grid din fereastra, creez un nou label cu ajutorul
    // design patternului Builder (Label Builder)
    for (int i = 0; i < labels.length; i++) {
      for (int j =0; j < labels[0].length; j++) {
        labels[i][j] =
            new LabelBuilder()
                    .withDimensions(50, 50)
                    .withRandomColor()
                    .withBorder()
                    .withCoord(i, j)
                    .build();
        add(labels[i][j]);
        Cale.adaugaLabel(i, j, labels[i][j]);
      }
    }

    // nelipsite la orice fereastra
    setSize(new Dimension(500, 500));
    setVisible(true);
  }

  public JLabel[][] getLabels() {
    return labels;
  }

  public int getLength() {
    return length;
  }
}

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import static java.awt.Color.WHITE;

// Design pattern label builder

public class LabelBuilder {
  // ne ajuta sa stim ce label cu ce label incercam sa potrivim
  private static int labeluriSelectate = 0;
  private static JLabel primulSelectat = null;

  // campul label ce se modifica cu method chaining (.with.with.with...build)
  // DESIGN PATTERN BUILDER
  private final JLabel label;
  // aici avem culorile ce pot exista (putem adauga mai multe daca vrem)
  private final Color[] colors = {
    new Color(100, 50, 0),
    new Color(172, 243, 41),
    Color.GREEN,
    Color.YELLOW,
    Color.CYAN,
    Color.MAGENTA,
    Color.PINK,
    new Color(117, 123, 234),
    Color.DARK_GRAY,
    Color.ORANGE
  };
  // coordonatele labelului respectiv
  public int i;
  public int j;


  // Constructorul
  public LabelBuilder() {

    this.label = new JLabel();

    // aici definesti functionalitatea ca daca ai dat click pe unul, astepta sa faca match cu urmatorul label (if labeluriSelectate == 1)
    // daca n-ai dat click pe niciunul, cel pe care dai click devine selectat (if labeluriSelectate == 0)
    label.addMouseListener(
        new MouseAdapter() {
          public void mouseClicked(MouseEvent e) {
            // Doar un log sa vedem ca aplicatia ia clickul cum trebuie
            System.out.println("Am dat click!  " + LabelBuilder.labeluriSelectate);

            // niciunul selectat
            if (labeluriSelectate == 0) {
              labeluriSelectate = 1;
              primulSelectat = label;
              primulSelectat.setText("SELECTED");
            } else {
              // verfic daca au aceeasi culoare de background SI! daca aceasta nu este alba SI! nu selectez acelasi label!
              if (primulSelectat.getBackground().equals(label.getBackground())
                  && !primulSelectat.getBackground().equals(WHITE)
                  && primulSelectat != label) {
                // daca am gasit calea, le fac albe, le fac textul null (din "SELECTED), cresc scorul SI! eliberez drumul
                if (Cale.gasita(primulSelectat, label)) {
                  primulSelectat.setText("");
                  primulSelectat.setBackground(WHITE);
                  label.setBackground(WHITE);
                  FereastraScor.getINSTANCE().incrementScore();

                  // aici eliberez drumul
                  Coordonate primul = Cale.searchLabelsCoord(primulSelectat);
                  Coordonate alDoilea = Cale.searchLabelsCoord(label);
                  Cale.amDrum[primul.i][primul.j] = true;
                  Cale.amDrum[alDoilea.i][alDoilea.j] = true;
                }
              }
              // daca nu s-a gasit cale pana acolo / chiar si daca s-a gasit
              primulSelectat.setText("");
              primulSelectat = null;
              labeluriSelectate = 0;
            }
          }
        });
  }

  // aici alege o culoare random
  public LabelBuilder withRandomColor() {
    label.setBackground(colors[new Random().nextInt(colors.length)]);
    label.setOpaque(true);

    return this;
  }

  // aici setez dimensiunea labelului
  public LabelBuilder withDimensions(int width, int height) {
    label.setSize(width, height);
    return this;
  }

  // aici fac un border ca sa nu se incurce culorile
  public LabelBuilder withBorder() {
    Border border = BorderFactory.createLineBorder(Color.BLACK);
    label.setBorder(border);
    return this;
  }

  // aici ii setez coordonatele
  public LabelBuilder withCoord(int i, int j) {
    this.i = i;
    this.j = j;
    return this;
  }

  // metoda de final ce returnewaza campul label
  public JLabel build() {
    return label;
  }
}

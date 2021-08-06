import javax.swing.*;

import java.util.ArrayDeque;
import java.util.Queue;

public class Cale {
  // amDrum - matricea unde caut daca exista drum intre doua noduri
  public static boolean[][] amDrum;
  // matricea unde caut sa vad pe ce pozitie e labelul respectiv
  private static JLabel[][] labels;

  // verific daca exista drum intre cele doua labeluri
  public static boolean gasita(JLabel primulSelectat, JLabel alDoileaSelectat) {
    // caut primul label si ii iau coordonatele
    Coordonate sursa = searchLabelsCoord(primulSelectat);
    // la fel si la al doilea
    Coordonate destinatie = searchLabelsCoord(alDoileaSelectat);

    // adaug sursa in coada
    Queue<Coordonate> coada = new ArrayDeque<>();
    coada.add(sursa);

    // fac un vector de vizitati pentru a nu ne intoarce la o casuta deja verificata
    boolean[][] amMaiFost = new boolean[amDrum.length][amDrum.length];
    // din sursa plecam deci nu mai trb sa o verificam
    amMaiFost[sursa.i][sursa.j] = true;

    // vectori de directie (jos stanga dreapta sus) -NU MERG PE DIAGONALA
    int[] dirX = {-1, 0, 0, 1};
    int[] dirY = {0, -1, 1, 0};

    // cat timp am ceva in coada, scot coordonatele si verific daca am ajuns la destinatie
    while (!coada.isEmpty()) {
      Coordonate coordonate = coada.remove();
      for (int i = 0; i < 4; i++) {
        int x = coordonate.i + dirX[i];
        int y = coordonate.j + dirY[i];
        // daca am ajuns la destinatie ma opresc si returnez true
        if (x == destinatie.i && y == destinatie.j) {
          return true;
        }
        // daca este in matrice, exista drum si n-am mai fost
        if ((x >= 0) && (x < amDrum.length) &&
                (y >= 0) && (y < amDrum.length) && amDrum[x][y] == true && !amMaiFost[x][y]) {

          // o marchez ca am mai fost si o adaug in coada
          amMaiFost[x][y] = true;
          coada.add(new Coordonate(x, y));
        }
      }
    }
    // daca am epuziat toti vecinii si niciunul nu a respectat conditia
    // returnam false (nu se poate gasi drumul)
    return false;
  }

  // functie ce cauta coordonatele unui label
  public static Coordonate searchLabelsCoord(JLabel primulSelectat) {
    // parcurge matricea de labeluir
    for (int i = 0; i < labels.length; i++) {
      for (int j = 0; j < labels.length; j++) {
        // daca am gasit un label ce face match cu al nostru (adica daca e acelasi)
        if (labels[i][j] == primulSelectat) {
          // returnam coordonatele lui (cu +1 pentru ca in matricea noastra avem si o bordura de zerouri)
          return new Coordonate(i+1, j+1);
        }
      }
    }
    return null;
  }

  public static void adaugaLabel(int i, int j, JLabel label) {
    amDrum[i+1][j+1] = false;
    labels[i][j] = label;
  }

  // initializez matricea pe care voi face cautarea (amDrum)
  // initializez matricea de labeluri unde voi cauta labelul sursa si cel destinatie
  public static void setMatrixLength(int length) {
    amDrum = new boolean[length + 2][length + 2];
    for (int i = 0; i < length + 2; i++) {
      for (int j = 0; j < length + 2 ; j++) {
        amDrum[i][j] = true;
      }
    }
    // AICI DACA VREM SA NU TREACA DE COLTURI (SA GASEASCA FLUTURI PE ACEEASI LATURA)
    amDrum[0][0] = false; // stanga sus
    amDrum[0][length+1] = false; // dreapta sus
    amDrum[length+1][0] = false; // stangga jos
    amDrum[length+1][length+1] = false; // dreapta jos

    labels = new JLabel[length][length];
  }
}

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreator {
  private FileCreator filecreator;
  private File myfile;

  private FileCreator(File myfile) {
    this.myfile = myfile;
  }

  // FACTORY MEHTOD - DAU libertatea de a se mai crea factroy methods
  // ce pot creea de exemplu mai multe tipuri de fisiere (nu doar .CSV)

  public static FileCreator createCSVFile(String filename) {
    try {
      File myfile = new File(filename + ".csv");
      if (myfile.createNewFile()) {
        System.out.println("File created: " + myfile.getName());
      } else {
        System.out.println("File already exists.");
      }
      return new FileCreator(myfile);

    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
      return null;
    }
  }

  public void writeRow(String name, Integer score) throws IOException {
    FileWriter myWriter = new FileWriter(myfile, true);
    myWriter.append(name).append(",").append(String.valueOf(score)).append(",\n");
    myWriter.flush();
    System.out.println(
        name + "," + score + ",\n" + " a fost scris in fisierul " + myfile.toString());
  }

  public void writeCell(String cell) throws IOException {
    FileWriter myWriter = new FileWriter(myfile);
    myWriter.append(cell + ",");
  }

  public static FileCreator createTxtFile(String filename) {
    try {
      File myfile = new File(filename + ".txt");
      // plus alte prelucrari
      if (myfile.createNewFile()) {
        System.out.println("File created: " + myfile.getName());
      } else {
        System.out.println("File already exists.");
      }
      return new FileCreator(myfile);

    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
      return null;
    }
  }
  // apoi sa avem metode specifice txt file (putem avea si creeare de subclase)
}

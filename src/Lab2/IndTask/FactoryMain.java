package Lab2.IndTask;

public class FactoryMain {
    public static void main(String[] args) {
        DoctorFileFactory docFact = new DoctorFileFactory();
        DoctorFileInter fileDoc = docFact.getDoctorFile("XML");
        fileDoc.fileRead("src\\Lab2\\IndTask\\Doctor.xml");
        System.out.println(fileDoc);
        fileDoc = docFact.getDoctorFile("TXT");
        fileDoc.fileRead("src\\Lab2\\IndTask\\TextFiles\\indTaskSrc.txt");
        System.out.println(fileDoc);
    }
}

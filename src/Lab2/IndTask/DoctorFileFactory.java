package Lab2.IndTask;

public class DoctorFileFactory {
    public DoctorFileInter getDoctorFile(String docFileType){
        switch (docFileType.toUpperCase()){
            case "TXT": return new TextFileDoctor();
            case "XML": return new XMLDoctor();
            default:    return null;
        }
    }
}

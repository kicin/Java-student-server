package filesdb;

import java.io.File;
import java.nio.file.Path;

import javax.swing.text.Document;

public class FileDB {

	public int fileId;
	public String fileName;
	public String fileAuthor;
	public boolean isAccepted;
	public double fileGrade;
	public int fileGradesCounter;
	public File thisFile;
	
	public FileDB(){
		
	}
	
	
	public int getFileId(){
		return fileId;
	}
	
	public void setFileId(int id){
		this.fileId=id;
	}
	
	public String getFileName(){
		return fileName;
	}
	
	public void setFileName(String name){
		this.fileName=name;
	}
	
	public String getFileAdress(){
		return fileName;
	}
	
	public void  setFileAdress(String adress){
		this.fileName=adress;
	}
	
	public double getFileGrade(){
		return fileGrade;
	}
	
	public void setFileGrade(double grade){
		this.fileGrade=grade;
	}
}

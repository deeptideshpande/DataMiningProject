package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/* 
 The program segregate.java takes the text files in the source path folder, checks for FLD Appln and moves the text file to the corresponding folder. 
 The folders are already created by Deepti's program- TestSplitString.java.
 source_path contains all the input text files. Place all the text files in a folder input.
 destination_path contains all the output folders
 These two should be distinct folders.
 The source_path and destination_path must be changed according to the platform where it is run.
 "/" should be used at the end of source_path only and not at the end of destination_path
 
 */
 


public class segregate
{

	public static void main(String[] args) throws Exception
	{
		String source_path = "C:/Users/Sony/Desktop/java codes/awd_1990_00/input/";
		String destination_path = "C:/Users/Sony/Desktop/java codes/awd_1990_00/output_folders";

		File folder  = new File(source_path);

		File[] listOfFiles = folder.listFiles();


		for(int i=0; i< listOfFiles.length; i++)
		{

			FileReader fr = new FileReader(listOfFiles[i]);

			BufferedReader bf = new BufferedReader(fr);

			String line = bf.readLine();

			while(line != null)
			{
				String[] words = line.split(":");

				if(words[0].equals("Fld Applictn"))
				{

					//Split the text in output.txt into sub-strings based on delimiter space
					String[] word1 = line.split(" +");
					String sFolderName=new String("");

					int j;
					for(j=3;j< word1.length;j++ )
					{
						if(j==((word1.length)-1))
							sFolderName=sFolderName+word1[j];
						else
							sFolderName=sFolderName+word1[j]+" ";
					}

					bf.close();
					fr.close();
					Path original = Paths.get(source_path+listOfFiles[i].getName());
					Path destination = Paths.get(destination_path+sFolderName+"/"+listOfFiles[i].getName());

					try
					{
						Files.move(original, destination, StandardCopyOption.REPLACE_EXISTING);
					}
					catch (IOException x)
					{
						x.printStackTrace(); //catch all for IO problems
					}

					line=null;
					break;

				}//End of if
				else
				{
					words = null;
					line = bf.readLine();
				}
			}//End of while


		}//End of for
	}
}



package src.Smartphone;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;

public class Log 
{
	/**
	 * Contructeur visant à la génération de logs
	 * @param String message 	: Message générique de l'erreur
	 * @param String scase 		: Information personnelle de l'erreur
	 * @param String reperage	: Reperage hierarchique de l'erreur
	 */	
	public Log(String message, String scase, String reperage)
	{
		try
		{
			File fUrl = new File("C:\\Smartphone\\log"); // Dossier de config.
			if(!fUrl.exists())
				fUrl.mkdirs();
			
			File fLinks;
			fLinks = new File(fUrl, LocalDateTime.now().toString().substring(0, 10)+".txt"); // Fichier de config.
			
			int i = 0;
			while(fLinks.exists())
			{
				i++;
				fLinks = new File(fUrl, LocalDateTime.now().toString().substring(0, 10)+i+".txt"); // Fichier de config.
			}
			if(!fLinks.exists() || !fLinks.isFile())
			{
				fLinks.createNewFile();
				// Flux de base (pour voir)
				FileWriter flowfiles 	= new FileWriter(fLinks);
				// Flux Tampon (englobe le flux de base).
				BufferedWriter 	bwrites	= new BufferedWriter(flowfiles);
					bwrites.write(LocalDateTime.now() + " < LOGS (" +  reperage+  ") > " + message + " < " + scase + " > ");
				bwrites.close();
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}

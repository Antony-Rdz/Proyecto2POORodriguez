package Controlador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FechaDeCreacion {

	public String retornaFecha(String direccion) {

		File file = new File(direccion);
		String fecha = "";

		BasicFileAttributes attrs;
		try {
			attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
			FileTime time = attrs.creationTime();

			String pattern = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

			fecha = simpleDateFormat.format(new Date(time.toMillis()));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return fecha; 
	}
}

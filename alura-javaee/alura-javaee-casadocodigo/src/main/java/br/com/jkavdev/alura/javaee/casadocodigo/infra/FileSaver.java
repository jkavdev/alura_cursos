package br.com.jkavdev.alura.javaee.casadocodigo.infra;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;

import javax.servlet.http.Part;

public class FileSaver {

	public static final String SERVER_PATH = "/casadocodigo";

	// metodo que recebera um arquivo e gravara seu conteudo num local definido
	public String write(Part arquivo, String path) {
		// gravando o conteudo do arquivo no disco
		// passando o caminho e o nome do arquivo que sera gravado no disco
		String relativePath = path + "/" + arquivo.getSubmittedFileName();

		try {
			arquivo.write(SERVER_PATH + "/" + relativePath);

			return relativePath;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public static void transfer(Path source, OutputStream outputStream) {
		try {
			//teremos que criar uma entrada com os dados do arquivo
			FileInputStream input = new FileInputStream(source.toFile());
			//Criamos um canal com a entrada de dados e outro canal para a saida de dados
			try (ReadableByteChannel inputChannel = Channels.newChannel(input);
					WritableByteChannel outputChannel = Channels.newChannel(outputStream);) {
				//atribuimos um buffer
				ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 10);
				//e escremos no arquivo de saida os dados do arquivo de entrada
				while (inputChannel.read(buffer) != -1) {
					//seta os bytes para zero
					buffer.flip();
					outputChannel.write(buffer);
					buffer.clear();
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}

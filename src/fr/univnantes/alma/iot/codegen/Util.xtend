package fr.univnantes.alma.iot.codegen

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

public class Util {

	/**
	 * Méthode utilitaire fournie pour écrire le code généré sous la forme de fichiers .java.
	 * Ces fichiers sont enregistrés dans le projet "fr.univnantes.alma.statemachines.codegen.test.output".
	 * 
	 * Vous ne devez normalement pas changer cette méthode.
	 * 
	 * @param javaPackage Le nom du package Java de la classe ou de l'interface générée, utilisé pour créer le répertoire correspondant.
	 * @param javaClassName Le nom de la classe ou de l'interface Java générée, utilisé pour nommer le fichier .java
	 * @param fileContent Le code généré pour cette classe ou cette interface, qui sera écrit dans le fichier créé.
	 */
	def static void writeJavaClassToFile(String javaPackage, String javaClassName, String fileContent) {
		val Path filePath = Paths::get( "output",javaPackage,
			javaClassName + ".js")
		Files::createDirectories(filePath.parent)
		Files::write(filePath, fileContent.getBytes("utf-8"), StandardOpenOption::CREATE,
			StandardOpenOption::TRUNCATE_EXISTING);
	}
}

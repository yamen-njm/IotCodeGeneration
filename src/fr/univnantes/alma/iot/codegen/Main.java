package fr.univnantes.alma.iot.codegen;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import iotModel.IotModelPackage;
import iotModel.Network;

public class Main {
	 public static void main(String[] args) {
		    generateCode("models/light_Traffic.iotmodel");
		  }
		  private static  Network loadModel(String modelPath) {
			  IotModelPackage iotPack=IotModelPackage.eINSTANCE;
			  ResourceSet rs = new ResourceSetImpl();
			  Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("iotmodel", new XMIResourceFactoryImpl());
			  Resource resource = rs.getResource(URI
		                .createURI(modelPath), true);
			  Network result = (Network) resource.getContents().get(0);
				
				return result;
			}
			
			/**
			 * Appelle le générateur de code sur un fichier contenant un modèle de machine à états. 
			 */
			private static void generateCode(String modelPath) {
				 Network loadedModel1 = loadModel(modelPath);
				XtendIotCodeGen codeGenerator = new XtendIotCodeGen();
				codeGenerator.generateCode(loadedModel1);
//			}
		}
}

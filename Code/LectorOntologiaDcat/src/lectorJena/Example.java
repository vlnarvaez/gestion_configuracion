/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lectorJena;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.RDFWriter;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.VCARD;

/**
 *
 * @author luischalan
 */
public class Example {

    // some definitions
    public static void main(String[] args) throws FileNotFoundException {
        String personURI = "http://utpl.edu.ec/Cuestionario";
        String givenName = "John";
        String familyName = "Smith";
        String fullName = givenName + " " + familyName;
        
        File f = new File("/Users/luischalan/Desktop/example.rdf");
            FileOutputStream os = new FileOutputStream(f);
// create an empty Model
        Model model = ModelFactory.createDefaultModel();

// create the resource
//   and add the properties cascading style
        Resource johnSmith
                = model.createResource(personURI)
                .addProperty(VCARD.FN, fullName)
                .addProperty(VCARD.N,
                        model.createResource()
                        .addProperty(VCARD.Given, givenName)
                        .addProperty(VCARD.Family, familyName));
/*
        StmtIterator iter = model.listStatements();
        // print out the predicate, subject and object of each statement
        while (iter.hasNext()) {
            Statement stmt = iter.nextStatement();  // get next statement
            Resource subject = stmt.getSubject();     // get the subject
            Property predicate = stmt.getPredicate();   // get the predicate
            RDFNode object = stmt.getObject();      // get the object

            System.out.print(subject.toString());
            System.out.print(" " + predicate.toString() + " ");
            if (object instanceof Resource) {
                System.out.print(object.toString());
            } else {
                // object is a literal
                System.out.print(" \"" + object.toString() + "\"");
            }

            System.out.println(" .");
        }*/
        RDFWriter writer = model.getWriter("RDF/XML"); //RDF/XML
            writer.write(model, os, personURI);
            
        model.write(System.out);
        System.out.println("**********************");
        model.write(System.out, "RDF/XML-ABBREV");
        System.out.println("**********************");
        model.write(System.out, "JSON-LD");
    }
}

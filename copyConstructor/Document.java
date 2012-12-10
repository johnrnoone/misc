package copyConstructor;

import immutable.Planet;

import java.util.Date;
import java.util.GregorianCalendar;

interface Copyable {
	public Copyable copy();
}

public class Document implements Copyable, Cloneable
// Without the Cloneable marker interface declared this class will compile
// but will generate the runtime exception 'java.lang.CloneNotSupported'
//public class Document implements Copyable



{
	private String name;
	private Date created;

	public Document(String docName, Date creationDate){
		name = docName;
		created = new Date(creationDate.getTime()); }

	public Document(Document original){
		if (original == null){
			System.out.println("Error - null object.");
			System.exit(0); }

		name = original.name;
		created = new Date(original.created.getTime()); }

	public Copyable copy() {
		return new Document(name, created);}

	public String toString(){
		return (name + ", " + created);}
	
	public static void main (String[] args) {
		  System.out.println ("This is Document main");
		  
		  GregorianCalendar invoiceDate = new GregorianCalendar(2012, 00, 02, 13, 35);
		  Document invoice1 = new Document("Invoice1", new Date(invoiceDate.getTimeInMillis()));
		  System.out.println ("Document main creates invoice " + invoice1.name +
				  " hash = " + invoice1.hashCode());

		  
		  Document invoice2 = new Document("Invoice2", new Date(invoiceDate.getTimeInMillis()));
		  System.out.println ("Document main creates invoice " + invoice2.name +
				  " hash = " + invoice2.hashCode());

		  
		  GregorianCalendar receiptDate = new GregorianCalendar(2012, 00, 02, 13, 35);
		  Document receipt = new Document("Receipt", new Date(receiptDate.getTimeInMillis()));
		  System.out.println ("Document main creates receipt " + receipt.name +
				  " hash = " + receipt.hashCode());


		  GregorianCalendar statementDate = new GregorianCalendar(2012, 00, 02, 13, 35);
		  Document statement = new Document("Statement", new Date(statementDate.getTimeInMillis()));
		  System.out.println ("Document main creates statement " + statement.name +
				  " hash = " + statement.hashCode());
		  
		  Document invoiceCopy1 = (Document) invoice1.copy();
		  System.out.println ("Document main creates invoiceCopy1 " + invoiceCopy1.name + 
				  " hash = " + invoiceCopy1.hashCode());
		  
		try {
		  Document invoiceCopy2 = (Document) invoice1.clone();
		  System.out.println ("Document clones to get invoiceCopy2 " + invoiceCopy2.name + 
				  " hash = " + invoiceCopy2.hashCode());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		} 
		  
	}

}

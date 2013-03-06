# centralindex-java

## Introduction

The Centralindex Java module allows developers to start using the [Central Index](http://centralindex.com/) API with minimal code. The Central Index is a global data exchange, with a simple REST/JSON api. 

## What do I need before I start?

* Read up on what Central Index is here [http://centralindex.com/](http://centralindex.com/)
* Read up on the developer API here [http://developer.centralindex.com/](http://developer.centralindex.com/)
* Sign up for a Mashery account, if you don't have one already [http://developer.centralindex.com/member/register](http://developer.centralindex.com/member/register)
* Sign up for an API key here [http://developer.centralindex.com/apps/register](http://developer.centralindex.com/apps/register)

## Hello World

Take the Centralindex.java from this repository and add the following code to the bottom:

```
public static void main(String[] args) throws Exception {
  try {
			  
	  Centralindex ci = new Centralindex("<insert API Key here>",false);			  
	  String retval = "";
			  
	  retval = ci.getEntity("379236608286720");
			  
	  System.out.println("Response content " + retval);
			  
	 } 
	 finally {
			  
   } 
}
```

Compile the code
```
  javac -cp "./lib/*" Centralindex.java
```

and run the compiled Java code:
```
  java -cp '.:lib/*' Centralindex
```

## Function reference

See the [API Docs](http://developer.centralindex.com/docs/read/API_Reference) for more information.


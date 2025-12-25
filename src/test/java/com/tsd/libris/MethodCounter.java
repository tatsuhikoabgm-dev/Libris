package com.tsd.libris;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;


public class MethodCounter {

	 public static void main(String[] args) throws Exception {

	        countLayer("Controller","src/main/java/com/tsd/libris/controller");
//	        countLayer("Controller/auth","src/main/java/com/tsd/libris/controller/auth");
//	        countLayer("Controller/books","src/main/java/com/tsd/libris/controller/books");
//	        countLayer("Controller/shelf","src/main/java/com/tsd/libris/controller/shelf");
//	        countLayer("Controller/user","src/main/java/com/tsd/libris/controller/user");
	        
	        countLayer("Service","src/main/java/com/tsd/libris/service");
//	        countLayer("service/auth","src/main/java/com/tsd/libris/service/auth");
//	        countLayer("service/books","src/main/java/com/tsd/libris/service/books");
//	        countLayer("service/shelf","src/main/java/com/tsd/libris/service/shelf");
//	        countLayer("Service/user","src/main/java/com/tsd/libris/service/user");
	        
	        countLayer("Mapper",     "src/main/java/com/tsd/libris/mapper");
	        countLayer("Domain",     "src/main/java/com/tsd/libris/domain");
	    }

	    private static void countLayer(String label, String pathStr) throws Exception {
	        Path root = Paths.get(pathStr);
	        AtomicInteger count = new AtomicInteger();

	        Files.walk(root)
	                .filter(p -> p.toString().endsWith(".java"))
	                .forEach(p -> {
	                    try {
	                        CompilationUnit cu = StaticJavaParser.parse(p);
	                        cu.findAll(MethodDeclaration.class)
	                                .forEach(m -> count.incrementAndGet());
	                    } catch (Exception e) {
	                        System.err.println("Parse error: " + p);
	                    }
	                });

	        System.out.println(label + " methods = " + count.get());
	    }
	
}

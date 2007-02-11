package com.quantum.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


/**
 * @author BC Holmes
 */
public class JarUtil {

    private static Hashtable classLoaderCache = new Hashtable();
	
	public static Driver loadDriver(String[] driverFiles, String className) {
		Driver result = null;
		try {
			Class driverClass = loadDriverClass(driverFiles, className);
			if (driverClass != null) {
	            try {
	            	result = (Driver) driverClass.newInstance();
	            } catch (ClassCastException e) {
	            }
	        }
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (NoClassDefFoundError e) {
		} catch (RuntimeException e) {
		}
		return result;
	}
	
	public static Class loadDriverClass(String[] driverFiles, String className) {
		Class result = null;
		if (driverFiles != null && driverFiles.length > 0 
				&& driverFiles[0].trim().length() > 0 && className != null) {
			try {
	        	File[] files = getFiles(driverFiles);
        		URLClassLoader loader = getURLClassLoader(files);
		        result = loadDriverClass(className, loader);
			} catch (MalformedURLException e) {
			} catch (ClassNotFoundException e) {
			} catch (NoClassDefFoundError e) {
			} catch (RuntimeException e) {
			}
		} else if (className != null) {
			try {
				result = loadDriverClass(className, JarUtil.class.getClassLoader());
			} catch (ClassNotFoundException e) {
			} catch (NoClassDefFoundError e) {
			} catch (RuntimeException e) {
			}
		}
		return result;
	}
	
	/**
	 * @param driverFiles
	 * @return
	 */
	private static File[] getFiles(String[] driverFiles) {
		List list = new ArrayList();
		
		for (int i = 0, length = driverFiles == null ? 0 : driverFiles.length; i < length; i++) {
			File file = new File(driverFiles[i]);
			if (file.exists() && file.isFile()) {
				list.add(file);
			}
		}
		return (File[]) list.toArray(new File[list.size()]);
	}

	/**
	 * @param className
	 * @param loader
	 * @return
	 * @throws ClassNotFoundException
	 */
	private static Class loadDriverClass(String className, ClassLoader loader) throws ClassNotFoundException {
		Class driverClass = loader.loadClass(className);
		return Driver.class.isAssignableFrom(driverClass) ? driverClass : null;
	}

	public static String[] getAllDriverNames(String[] driverFile) {
		List list = new ArrayList();
		try {
			File[] files = getFiles(driverFile);
			URLClassLoader loader = getURLClassLoader(files);
			for (int i = 0, length = files == null ? 0 : files.length; i < length; i++) {
				JarFile jar = new JarFile(files[i]);
				addCandidateDriversToList(list, loader, jar);
			}
		} catch (IOException e) {
		}
		return (String[]) list.toArray(new String[list.size()]);
	}
	
	/**
	 * @param list
	 * @param loader
	 * @param jar
	 */
	private static void addCandidateDriversToList(List list, URLClassLoader loader, JarFile jar) {
		for (Enumeration e = jar.entries(); e.hasMoreElements(); ) {
			JarEntry entry = (JarEntry) e.nextElement();
			String className = getClassNameFromFileName(entry.getName());
			if (className != null) {
				try {
					Class driverClass = loadDriverClass(className, loader);
					if (driverClass != null) {
						list.add(className);
					}
				} catch (NoClassDefFoundError ex) {
				} catch (ClassNotFoundException ex) {
				} catch (RuntimeException ex) {
				}
			}
		}
	}

	private static String getClassNameFromFileName(String name) {
		String result = null;
		if (name.endsWith(".class")) {
			result = name.substring(0, name.length()-6).replace('/', '.').replace('\\', '.' );
		}
		return result;
	}

	/**
	 * @param file
	 * @return
	 * @throws MalformedURLException
	 */
	private static URLClassLoader getURLClassLoader(File[] files) throws MalformedURLException {
		
		String driverPath = getFilePath(files);
		URLClassLoader loader =
		    (URLClassLoader) classLoaderCache.get(driverPath);
		if (loader == null) {
		    URL urls[] = new URL[files == null ? 0 : files.length];
		    for (int i = 0, length = urls.length; i < length; i++) {
			    urls[i] = files[i].toURL();
			}
		    loader = urls.length > 0 ? new URLClassLoader(urls) : null;
		    if (loader != null) {
		    	classLoaderCache.put(driverPath, loader);
		    }
		}
		return loader;
	}

	/**
	 * @param files
	 * @return
	 */
	private static String getFilePath(File[] files) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0, length = files == null ? 0 : files.length; i < length; i++) {
			buffer.append(files[i].getAbsolutePath());
			if (i < length-1) {
				buffer.append(File.pathSeparator);
			}
		}
		return buffer.toString();
	}
}

/*******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.xml.util.date;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.util.ULocale;

/**
 * Help to create DateFormatter set for all ULocales.
 */
class DateFormatUtil
{
	/**
	 * 
	 * @param uLocales
	 */
	private static void loadULocaleInstanceFromLocale( Set uLocales )
	{
		//Populate ULocale Set from Locale.
		Locale[] ls = Locale.getAvailableLocales();
		for( int i = 0; i < ls.length; i++ )
		{
			uLocales.add( ULocale.forLocale((Locale) ls[i]));
		}
	}

	/**
	 * 
	 * @param uLocales
	 */
	private static void loadULocaleInstanceFromULocale( Set uLocales )
	{
		//Populate ULocale Set from ULocale
		ULocale[] uls = ULocale.getAvailableLocales( );
		for( int i = 0; i < uls.length; i++ )
		{
			uLocales.add( uls[i] );
		}
	}

	/**
	 * 
	 * @param uLocales
	 */
	private static ULocale[] populateULocaleCache( )
	{
		//We use Set to prevent duplicate ULocale instance from being added
		//to ULocale cache.
		Set uLocales = new HashSet();
		
		loadULocaleInstanceFromULocale( uLocales );
		
		loadULocaleInstanceFromLocale( uLocales );
		
		ULocale[]AllULocales = new ULocale[uLocales.size( )];
		
		Object[] localeArray = uLocales.toArray( ); 
		for( int i = 0; i < AllULocales.length; i++ )
		{
			AllULocales[i] = (ULocale)localeArray[i];
		}
		return AllULocales;
	}

	/**
	 * Ensure this class not to be constructed.
	 *
	 */
	private DateFormatUtil(){}
	
	/**
	 * Get DateFormatter map for all ULocales
	 * 
	 * @return
	 */
	static Map getAllDateFormatter( )
	{
		HashMap reMap = new HashMap( 80 );
		ULocale[] AllULocales = populateULocaleCache( );
		for ( int i = 0; i < AllULocales.length; i++ )
		{
			reMap.put( AllULocales[i], new DateFormatter( AllULocales[i] ) );
		}
		return reMap;
	}

	/**
	 * Get DateFormat map for all ULocales
	 * 
	 * @return
	 */
	static Map getAllDateFormat( )
	{
		HashMap reMap = new HashMap( 240 );
		ULocale[] AllULocales = populateULocaleCache( );
		for ( int i = 0; i < AllULocales.length; i++ )
		{
			for ( int dateStyle = DateFormat.LONG; dateStyle <= DateFormat.SHORT; dateStyle++ )
			{
				DateFormat dateFormat = DateFormat.getDateInstance( dateStyle,AllULocales[i] );
				dateFormat.setLenient( false );
				String key = String.valueOf( dateStyle )
						+ ":" + AllULocales[i].getName( );
				reMap.put( key, dateFormat );
			}
		}
		return reMap;
	}

	/**
	 * Get datetime Format map for all ULocales
	 * 
	 * @return
	 */
	static Map getAllDateTimeFormat( )
	{
		HashMap reMap = new HashMap( 720 );
		ULocale[] AllULocales = populateULocaleCache( );
		for ( int i = 0; i < AllULocales.length; i++ )
		{
			for ( int dateStyle = DateFormat.LONG; dateStyle <= DateFormat.SHORT; dateStyle++ )
			{
				for ( int timeStyle = DateFormat.LONG; timeStyle <= DateFormat.SHORT; timeStyle++ )
				{
					DateFormat dateFormat = DateFormat.getDateTimeInstance( dateStyle,timeStyle,AllULocales[i] );
					dateFormat.setLenient( false );
					String key = String.valueOf( dateStyle )
							+ ":" + String.valueOf( timeStyle ) + ":"
							+ AllULocales[i].getName( );
					reMap.put( key, dateFormat );
				}
			}
		}
		return reMap;
	}
}
